package mapper;


import pojo.MonthCard;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description: 后台管理系统月卡类
 * @Author Lxh
 * @Date 2020/5/8 20:01
 */
public interface IMonthCardMapper extends Mapper<MonthCard>, MySqlMapper<MonthCard> {
}
