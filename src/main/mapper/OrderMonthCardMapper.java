package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import dto.RequestDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pojo.order.Order;
import pojo.order.OrderMonthCard;
import pojo.order.OrderMonthCardVo;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author : fl
 * @describe :月卡订单管理的映射接口
 * @date : 2020-5-11
 */
public interface OrderMonthCardMapper extends BaseMapper<OrderMonthCard> , Mapper<OrderMonthCard>, MySqlMapper<OrderMonthCard> {
    @Select("<script>"+"SELECT\n" +
            "\to.order_number as 'orderNumber',\n" +
            "\to.user_id as 'userId',\n" +
            "\ttotal_price as 'totalPrice',\n" +
            "\to.`status` as 'status',\n" +
            "\tpay_status as 'payStatus',\n" +
            "\torder_type as 'orderType',\n" +
            "\tis_share as 'isShare',\n" +
            "\ttrace_status as 'trace_status',\n" +
            "\tuser_phone as 'userPhone',\n" +
            "\tc.month_card_id as 'monthCardId',\n" +
            "\tc.user_month_card_count as 'userMonthCardCount',\n" +
            "\tc.create_time as 'createTime',\n" +
            "\tc.end_time as 'endTime',\n" +
            "\tc.active as 'active',\n" +
            "\tw.count as 'count',\n" +
            "\tw.`name` as 'name',\n" +
            "\tw.price as 'price'\n" +
            "FROM\n" +
            "\t`order_month_card` c\n" +
            "INNER JOIN `order` o ON o.order_number = c.order_number\n" +
            "INNER JOIN wx_month_card w ON c.month_card_id = w.card_id\n" +
            "where 1 = 1 " +
            "<if test='orderNumber != null'>" +
            "and o.order_number LIKE CONCAT('%', '${orderNumber}', '%')" +
            "</if>"+
            "<if test='userPhone != null'>" +
            "and o.user_phone LIKE CONCAT('%', '${userPhone}', '%')" +
            "</if>"+
            "<if test='start != null'>" +
            "and c.create_time between #{start} and #{end} " +
            "</if>"+
            "order by c.create_time desc"+
            "</script>")
    //List<HashMap<Object,Object>> getOrderPageByParam(@Param("orderNumber")String orderNumber, @Param("userPhone")String userPhone, @Param("start")LocalDateTime startTime, @Param("end")LocalDateTime endTime);
    List<OrderMonthCardVo> getOrderPageByParam(@Param("orderNumber")String orderNumber, @Param("userPhone")String userPhone, @Param("start")LocalDateTime startTime, @Param("end")LocalDateTime endTime);

}
