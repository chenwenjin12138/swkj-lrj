package src.main.service.task;

import common.Constant;
import dto.RequestDTO;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pojo.order.OrderCustomHouse;
import pojo.order.OrderMonthCard;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fl
 * @descrip: 月卡定时任务
 * @date 2020/5/28 0028下午 2:03
 */
@Component
@EnableScheduling
public class MonthCardTask {
    private service.IOrderMonthCardService orderMonthCardService;
    private service.IOrderCustomHouseService orderCustomHouseService;


    public MonthCardTask(service.IOrderMonthCardService orderMonthCardService, service.IOrderCustomHouseService orderCustomHouseService) {
        this.orderMonthCardService = orderMonthCardService;
        this.orderCustomHouseService = orderCustomHouseService;
    }

    /**
     * 每天凌晨1点更新过期洗衣月卡
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void updateOrderMonthCard() {
        RequestDTO requestDTO = new RequestDTO();
        OrderMonthCard orderMonthCard = new OrderMonthCard();
        orderMonthCard.setActive(Constant.ACTIVE);
        requestDTO.setData(orderMonthCard);
        List<OrderMonthCard> orderList = orderMonthCardService.getOrderListByParam(requestDTO);
        orderList.forEach(order->{
            if (order.getEndTime().length()>19) {
                order.setEndTime(order.getEndTime().substring(0,19));
            }
            LocalDateTime endTime = null;
            try {
                endTime = LocalDateTime.parse(order.getEndTime(), Constant.dateTimeFormatter);
                if (endTime.isBefore(LocalDateTime.now())) {
                    order.setActive(Constant.FORBIDDEN);
                    orderMonthCardService.update(order);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    /**
     * 每天凌晨2点更新过期家政月卡
     */
    @Scheduled(cron = "0 0 2 * * ?")
    public void updateOrderCustomHouse() {
        RequestDTO requestDTO = new RequestDTO();
        OrderCustomHouse orderCustomHouse = new OrderCustomHouse();
        orderCustomHouse.setActive(Constant.ACTIVE);
        requestDTO.setData(orderCustomHouse);
        List<OrderCustomHouse> orderList = orderCustomHouseService.getCustomHouseListByParam(requestDTO);
        orderList.forEach(order->{
            try {
                if (order.getEndTime().isBefore(LocalDateTime.now())) {
                    order.setActive(Constant.FORBIDDEN);
                    orderCustomHouseService.update(order);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

}

