package mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pojo.Reservation;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/19 17:46
 */
public interface ReservationMapper extends Mapper<Reservation>, MySqlMapper<Reservation>, BaseMapper<Reservation> {
}
