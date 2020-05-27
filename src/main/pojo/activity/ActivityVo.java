package pojo.activity;

import lombok.Data;
import pojo.AppItem;
import pojo.AppItemCat;

import java.util.List;

/**
 * @author fl
 * @descrip: 活动vo类
 * @date 2020/5/26 0026上午 10:43
 */
@Data
public class ActivityVo {
    private Activity activity;
    private List<ActivityTime> activityTimeList;
    private List<ActivityItemVO> activityItemList;
    private List<AppItemCat> itemCatsList;
    private List<AppItem> itemList;
}
