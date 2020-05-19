package service.impl;

import dto.RequestDTO;
import dto.ReturnData;
import mapper.BannerTypeNameMapper;
import mapper.IBannerMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import pojo.Banner;
import pojo.BannerTypeName;
import service.IBannerService;
import tk.mybatis.mapper.entity.Example;
import util.DateUtils;

import javax.annotation.Resource;
import java.util.List;

import static dto.ReturnData.Fail_CODE;
import static dto.ReturnData.SUCCESS_CODE;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/13 9:30
 */
@Service
public class BannerServiceImpl implements IBannerService {

    @Resource
    private IBannerMapper bannerMapper;

    @Resource
    private BannerTypeNameMapper bannerTypeNameMapper;

    private ReturnData returnData = new ReturnData();

    /**
     * @param: requestDTO
     * @Description: Banner分页查询
     * @Author: LxH
     * @Date: 2020/5/13 9:39
     */
    @Override
    public List<Banner> getBannerPageByParam(RequestDTO requestDTO,Banner banner) {

        Example example = new Example(Banner.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("isShow", 1);
        if (banner.getBannerType()!=null) {
            criteria.andEqualTo("bannerType",banner.getBannerType());
        }
        if (banner.getBannerName()!=null) {
            criteria.andLike("bannerName","%"+banner.getBannerName()+"%");
        }
        int start = requestDTO.getPage() * requestDTO.getPage();
        RowBounds rowBounds = new RowBounds(start, requestDTO.getSize());
        List<Banner> banners = bannerMapper.selectByExampleAndRowBounds(example, rowBounds);
        for (Banner reBanner : banners) {
            BannerTypeName bannerTypeName = bannerTypeNameMapper.selectByPrimaryKey(reBanner.getBannerType());
            reBanner.setBannerTypeName(bannerTypeName.getBannerTypeName());
        }
        return banners;
    }

    /**
     * @param: appBannerId
     * @Description: 获取Banner图片
     * @Author: LxH
     * @Date: 2020/5/13 9:55
     */
    @Override
    public ReturnData findBannerImgById(Integer appBannerId) {
        Banner banner = bannerMapper.selectByPrimaryKey(appBannerId);
        if (banner.getBannerImg()==null) {
            return returnData.setCode(Fail_CODE).setMessage("该商品还未上传图片").setObject(null);
        }
        return returnData.setCode(SUCCESS_CODE).setMessage("图片获取成功").setObject(banner.getBannerImg());
    }

    /**
     * @param: appBannerId
     * @Description: 获取BannerUrl
     * @Author: LxH
     * @Date: 2020/5/13 10:09
     */
    @Override
    public ReturnData findBannerUrlById(Integer appBannerId) {
        Banner banner = bannerMapper.selectByPrimaryKey(appBannerId);
        if (banner.getUrl()==null) {
            return returnData.setCode(Fail_CODE).setMessage("该商品还未上传url").setObject(null);
        }
        return returnData.setCode(SUCCESS_CODE).setMessage("url获取成功").setObject(banner.getUrl());
    }

    /**
     * @param: banner
     * @Description: 添加Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:00
     */
    @Override
    public ReturnData addBanner(Banner banner,Integer bannerTypeId) {
        if (banner!=null) {
            banner.setCreateTime(DateUtils.getNowDateTime());
            bannerMapper.insertSelective(banner);
            return returnData.setCode(SUCCESS_CODE).setMessage("成功").setObject(null);
        }
        return returnData.setCode(Fail_CODE).setMessage("失败").setObject(null);
    }

    /**
     * @param: banner
     * @Description: 修改Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:06
     */
    @Override
    public ReturnData updateBanner(Banner banner) {
        if (banner!=null) {
            banner.setUpdateTime(DateUtils.getNowDateTime());
            int i1 = bannerMapper.updateByPrimaryKeySelective(banner);
            System.out.println(i1);
            return returnData.setCode(SUCCESS_CODE).setMessage("成功").setObject(null);
        }
        return returnData.setCode(Fail_CODE).setMessage("失败").setObject(null);
    }

    /**
     * @param: appBannerIds
     * @Description: 删除Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:10
     */
    @Override
    public ReturnData deleteBanner(Integer[] appBannerIds) {
        for (Integer appBannerId : appBannerIds) {
            bannerMapper.deleteByPrimaryKey(appBannerId);
            return returnData.setCode(SUCCESS_CODE).setMessage("成功").setObject(null);
        }
        return returnData.setCode(Fail_CODE).setMessage("失败").setObject(null);
    }

    /**
     * @param: bannerName
     * @param: bannerType
     * @Description: 条件查询Banner
     * @Author: LxH
     * @Date: 2020/5/13 11:17
     */
    @Override
    public ReturnData findBannerByName(String bannerName, Integer bannerType) {
        Example example = new Example(Banner.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bannerName",bannerName).andEqualTo("bannerType",bannerType);
        List<Banner> banners = bannerMapper.selectByExample(example);
        if (banners==null||banners.size()==0) {
            return returnData.setCode(Fail_CODE).setMessage("该Banner不存在").setObject(null);
        }
        for (Banner banner : banners) {
            BannerTypeName bannerTypeName = bannerTypeNameMapper.selectByPrimaryKey(bannerType);
            banner.setBannerTypeName(bannerTypeName.getBannerTypeName());
            return returnData.setCode(SUCCESS_CODE).setMessage("成功").setObject(banner);
        }
        return null;
    }

    /**
     * @Description: 获取全部BannerTypeName
     * @Author: LxH
     * @Date: 2020/5/14 11:31
     */
    @Override
    public List<BannerTypeName> findAll() {
        return bannerTypeNameMapper.selectAll();
    }
}
