package controller;

import dto.RequestDTO;
import dto.ReturnData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Banner;
import pojo.BannerTypeName;
import service.IBannerService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/12 16:04
 */
@RestController
@RequestMapping("banner")
@AllArgsConstructor
@Api(tags = "Banner管理")
public class BannerController {

    @Resource
    private IBannerService bannerService;

    /**
     * @Description: 获取全部BannerTypeName
     * @Author: LxH
     * @Date: 2020/5/14 11:30
     */
    @ApiOperation(value = "获取全部BannerTypeName")
    @RequestMapping("BannerTypeName")
    public List<BannerTypeName> findAll() {
        return bannerService.findAll();
    }

    /**
     * @Description: Banner分页查询
     * @Author: LxH
     * @Date: 2020/5/13 9:39
     */
    @ApiOperation(value = "Banner分页查询")
    @RequestMapping("getBannerPageByParam")
    public List<Banner> getBannerPageByParam(RequestDTO requestDTO,Banner banner){
        return bannerService.getBannerPageByParam(requestDTO,banner);
    }

    /**
     * @Description: 获取Banner图片
     * @Author: LxH
     * @Date: 2020/5/13 9:53
     */
    @ApiOperation(value = "获取Banner图片")
    @RequestMapping("getBannerImg")
    public ReturnData getBannerImg(Integer appBannerId){
        return bannerService.findBannerImgById(appBannerId);
    }

    /**
     * @Description: 获取BannerUrl
     * @Author: LxH
     * @Date: 2020/5/13 10:09
     */
    @ApiOperation(value = "获取BannerUrl")
    @RequestMapping("getBannerUrl")
    public ReturnData getBannerUrl(Integer appBannerId) {
        return bannerService.findBannerUrlById(appBannerId);
    }

    /**
     * @Description: 添加Banner
     * @Author: LxH
     * @Date: 2020/5/13 10:59
     */
    @ApiOperation(value = "添加Banner")
    @RequestMapping("addBanner")
    public ReturnData addBanner(Banner banner,Integer bannerTypeId){
        return bannerService.addBanner(banner,bannerTypeId);
    }

    /**
     * @Description: 修改Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:05
     */
    @ApiOperation(value = "修改Banner")
    @RequestMapping("updateBanner")
    public ReturnData updateBanner(Banner banner){
        return bannerService.updateBanner(banner);
    }

    /**
     * @Description: 删除Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:10
     */
    @ApiOperation(value = "删除Banner")
    @RequestMapping("deleteBanner")
    public ReturnData deleteBanner(Integer[] appBannerIds) {
        return bannerService.deleteBanner(appBannerIds);
    }

    /*
     * @Description: 条件查询Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:15
     *
    @ApiOperation(value = "条件查询Banner")
    @RequestMapping("/findBannerByName")
    public ReturnData findBannerByName(String bannerName,Integer bannerType){
        return bannerService.findBannerByName(bannerName,bannerType);
    }*/
}
