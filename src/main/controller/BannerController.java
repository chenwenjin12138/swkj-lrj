package controller;

import dto.RequestDTO;
import dto.ReturnData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Banner;
import service.IBannerService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/12 16:04
 */
@RestController
public class BannerController {

    @Resource
    private IBannerService bannerService;

    /**
     * @Description: Banner分页查询
     * @Author: LxH
     * @Date: 2020/5/13 9:39
     */
    @RequestMapping("getBannerPageByParam")
    public List<Banner> getBannerPageByParam(RequestDTO requestDTO){
        return bannerService.getBannerPageByParam(requestDTO);
    }

    /**
     * @Description: 获取Banner图片
     * @Author: LxH
     * @Date: 2020/5/13 9:53
     */
    @RequestMapping("getBannerImg")
    public ReturnData getBannerImg(Integer appBannerId){
        return bannerService.findBannerImgById(appBannerId);
    }

    /**
     * @Description: 获取BannerUrl
     * @Author: LxH
     * @Date: 2020/5/13 10:09
     */
    @RequestMapping("getBannerUrl")
    public ReturnData getBannerUrl(Integer appBannerId) {
        return bannerService.findBannerUrlById(appBannerId);
    }

    /**
     * @Description: 添加Banner
     * @Author: LxH
     * @Date: 2020/5/13 10:59
     */
    @RequestMapping("addBanner")
    public ReturnData addBanner(Banner banner){
        return bannerService.addBanner(banner);
    }

    /**
     * @Description: 修改Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:05
     */
    @RequestMapping("updateBanner")
    public ReturnData updateBanner(Banner banner){
        return bannerService.updateBanner(banner);
    }

    /**
     * @Description: 删除Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:10
     */
    @RequestMapping("deleteBanner")
    public ReturnData deleteBanner(Integer[] appBannerIds) {
        return bannerService.deleteBanner(appBannerIds);
    }

    /**
     * @Description: 条件查询Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:15
     */
    @RequestMapping("/findBannerByName")
    public ReturnData findBannerByName(String bannerName,Integer bannerType){
        return bannerService.findBannerByName(bannerName,bannerType);
    }
}
