package pojo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author fl
 * @descrip: 增值服务
 * @date 2020/5/13 0013上午 03:40
 */
@Data
public class ValueAddedServices {

    private Integer valueAddedServicesId;
    /**
     * 商品类别
     */
    private Integer categoryId;
    private BigDecimal servicePrice;
    private String serviceName;
    private String serviceDescription;
    /**
     *排序值App端显示
     */
    private Integer sort;
    private String createTime;
    private String updateTime;
    private String createAdminId;
    private String updateAdminId;

    public static final String VALUE_ADDED_SERVICES_ID_COLUMN = "value_added_services_id";

}
