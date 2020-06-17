package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pojo.order.OrderCustomHouse;
import pojo.order.OrderCustomHouseVo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : fl
 * @describe :定制家政月卡管理的映射接口
 * @date : 2020-5-11
 */
public interface OrderCustomHouseMapper extends BaseMapper<OrderCustomHouse> {
    @Select("<script>"+
            "SELECT\n" +
            "\to.order_number as 'orderNumber',\n" +
            "\to.user_id as 'userId',\n" +
            "\ttotal_price as 'totalPrice',\n" +
            "\to.`status` as 'status',\n" +
            "\tpay_status as 'payStatus',\n" +
            "\torder_type as 'orderType',\n" +
            "\tis_share as 'isShare',\n" +
            "\ttrace_status as 'trace_status',\n" +
            "\tuser_phone as 'userPhone',\n" +
            "\tc.base_service_count as baseServiceCount,\n" +
            "\tbase_service_price as baseServicePrice,\n" +
            "\tservice_cycle as serviceCycle,\n" +
            "\twork_time as workTime,\n" +
            "\thouse_area as houseArea,\n" +
            "\tc.open_time as openTime,\n" +
            "\tc.end_time as endTime,\n" +
            "\tc.individual_service_json as individualServiceJson\n" +
            "FROM\n" +
            "\t`order_custom_house_service` c\n" +
            "INNER JOIN `order` o ON o.order_number = c.order_number\n" +
            "where 1 = 1 " +
            "<if test='orderNumber != null'>" +
            "and o.order_number LIKE CONCAT('%', '${orderNumber}', '%')" +
            "</if>"+
            "<if test='userPhone != null'>" +
            "and o.user_phone LIKE CONCAT('%', '${userPhone}', '%')" +
            "</if>"+
            "<if test='start != null'>" +
            "and c.open_time between #{start} and #{end} " +
            "</if>"+
            "order by c.open_time desc"+
            "</script>")
    List<OrderCustomHouseVo> getOrderPageByParam(@Param("orderNumber")String orderNumber, @Param("userPhone")String userPhone, @Param("start") LocalDateTime startTime, @Param("end")LocalDateTime endTime);
}
