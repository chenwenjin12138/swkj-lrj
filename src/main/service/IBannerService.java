package service;

import dto.RequestDTO;
import dto.ReturnData;
import pojo.Banner;

import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/13 9:29
 */
public interface IBannerService {

    /**
     * @Description: Banner分页查询
     * @Author: LxH
     * @Date: 2020/5/13 9:39
     */
    List<Banner> getBannerPageByParam(RequestDTO requestDTO);

    /**
     * @Description: 获取Banner图片
     * @Author: LxH
     * @Date: 2020/5/13 9:55
     */
    ReturnData findBannerImgById(Integer appBannerId);

    /**
     * @Description: 获取BannerUrl
     * @Author: LxH
     * @Date: 2020/5/13 10:09
     */
    ReturnData findBannerUrlById(Integer appBannerId);

    /**
     * @Description: 添加Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:00
     */
    ReturnData addBanner(Banner banner);

    /**
     * @Description: 修改Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:06
     */
    ReturnData updateBanner(Banner banner);

    /**
     * @Description: 删除Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:10
     */
    ReturnData deleteBanner(Integer[] appBannerIds);

    /**
     * @Description: 条件查询Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:17
     */
    ReturnData findBannerByName(String bannerName, Integer bannerType);
}
