package service.task;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.RequestDTO;
import lombok.AllArgsConstructor;
import mapper.TimeLimitSaleLogMapper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pojo.AppItem;
import pojo.Reservation;
import pojo.TimeLimitSaleLog;
import service.IAppItemService;
import service.IReservationService;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author fl
 * @descrip: 限时特价定时任务
 * @date 2020/5/28 0028下午 2:03
 */
@Component
@EnableScheduling
@AllArgsConstructor
public class TimeLimitSaleTask {
    private IReservationService reservationService;
    private ObjectMapper objectMapper;
    private IAppItemService appItemService;
    private TimeLimitSaleLogMapper timeLimitSaleLogMapper;

    /**
     * 限时特价商品，每6个小时换一次，每次换3个
     */
    @Scheduled(cron = "0 00 09 ? * *")
    @Scheduled(cron = "0 00 18 ? * *")
    public void grantTimeLimitSale() {
        //查询半个月内下单的商品
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setEndLocalDateTime(LocalDateTime.now());
        requestDTO.setStartLocalDateTime(LocalDateTime.now().minusDays(3));
        List<Reservation> reservations = reservationService.getReservationPageByParam(requestDTO);
        List<Integer> items = new ArrayList<>();
        reservations.forEach(reservation -> {
            try {
                if (StringUtils.isNotEmpty(reservation.getReservationJson())) {
                    List<AppItem> oneItem = objectMapper.readValue(reservation.getReservationJson(), new TypeReference<List<AppItem>>() {
                    });
                    for (AppItem appItem : oneItem) {
                        items.add(appItem.getItemId());
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Map<Integer, Integer> itemsMap = new HashMap<>();
        for (Integer id : items) {
            if (itemsMap.get(id) != null) {
                itemsMap.put(id, itemsMap.get(id) + 1);
            } else {
                itemsMap.put(id, 1);
            }
        }

        List<Map.Entry<Integer, Integer>> list_Data = new ArrayList<Map.Entry<Integer, Integer>>(itemsMap.entrySet());
        Collections.sort(list_Data, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                if (o2.getValue() != null && o1.getValue() != null && o2.getValue().compareTo(o1.getValue()) > 0) {
                    return 1;
                } else {
                    return -1;
                }

            }
        });
        int itemCount = 0;
        for (Map.Entry<Integer, Integer> data : list_Data) {
            if (itemCount > 3) {
                break;
            }
            RequestDTO param = new RequestDTO();
            AppItem paramItem = new AppItem();
            paramItem.setBargainType(AppItem.BargainType.TIME_LIMIT_SALE.toString());
            param.setObject(paramItem);
            List<AppItem> timeLimitList = appItemService.getAppItemListByParam(param);
            timeLimitList.forEach(appItem -> {
                appItem.setBargainType(AppItem.BargainType.NORMAL.toString());
                appItem.setPrice(appItem.getPromotionOriginalCost());
                appItem.setPromotionOriginalCost(null);
                appItemService.updateAppItem(appItem);
            });

            paramItem = new AppItem();
            paramItem.setAppItemId(data.getKey());
            param.setObject(paramItem);
            List<AppItem> list = appItemService.getAppItemListByParam(param);
            if (list != null && list.size() > 0) {
                AppItem oldItem = list.get(0);
                oldItem.setPromotionOriginalCost(oldItem.getPrice());
                BigDecimal price= oldItem.getPromotionOriginalCost().multiply(new BigDecimal(0.68));
                oldItem.setPrice(price);
                oldItem.setPromotionBeginDate(LocalDateTime.now());
                oldItem.setPromotionEndDate(oldItem.getPromotionBeginDate().plusHours(9));
                oldItem.setBargainType(AppItem.BargainType.TIME_LIMIT_SALE.toString());
                appItemService.updateAppItem(oldItem);
                TimeLimitSaleLog timeLimitSaleLog = new TimeLimitSaleLog();
                timeLimitSaleLog.setItemId(oldItem.getAppItemId());
                timeLimitSaleLog.setBefore_price(oldItem.getPromotionOriginalCost());
                timeLimitSaleLog.setAfter_price(oldItem.getPrice());
                timeLimitSaleLog.setCreateTime(LocalDateTime.now());
                try {
                    timeLimitSaleLogMapper.insert(timeLimitSaleLog);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                itemCount++;
            }
        }
    }

}

