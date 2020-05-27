package pojo.activity;

import lombok.Data;
import pojo.AppItemCat;

import java.util.List;

/**
 * @author fl
 * @descrip:
 * @date 2020/5/27 0027下午 2:11
 */
@Data
public class ActivityItemVO {
    private ActivityItem activityItem;
    private List<AppItemCat> appItemCatList;
}
