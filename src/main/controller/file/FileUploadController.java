package controller.file;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.Messages;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import common.Constant;
import common.Result;
import net.coobird.thumbnailator.Thumbnails;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import util.RandomUtil;
import util.ZipUtils;


import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import java.util.Iterator;


/**
 * @author Lxh
 * @date 2020/3/26 9:35
 */
@Controller
@RequestMapping("/file-upload")
public class FileUploadController {
    /**
     * <b>apkUpload</b>：(apk包上传)<br>
     * <b>TODO</b>：(需通过ajaxGET请求该接口)<br>
     * <b>url</b>：(basePath../file-upload/apk-upload)<br>
     *
     *
     * @param request
     *            请求对象<br>
     * @return Result<br>
     * @author SAM QZL
     * @throws Exception
     */
    @RequestMapping(value = "/apk-upload", method = RequestMethod.POST)
    @ResponseBody
    public String apkUpload(HttpServletRequest request) throws Exception {

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        /** 上传HTML文件地址URL **/
        String HTMLUrl = "";
        if (multipartResolver.isMultipart(request)) {// 如果是文件上传就执行代码
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            String root_path = Messages.getString("file_path");// 文件系统根路径
            String apk_path = Messages.getString("apk");// apk压缩包路径
            /** 上传先删除原有文件 **/
            File delete = new File(root_path + apk_path);// 上传前覆盖删除目录
            FileUploadController.deleteAllFilesOfDir(delete);
            /** 上传先删除原有文件 **/
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {// 如果文件不为null
                    // 取得当前上传文件的文件名称
                    String originalFilename = file.getOriginalFilename();
                    if (!"".equals(originalFilename.trim())) {
                        // 重命名上传后的文件名file.getOriginalFilename().lastIndexOf(".")
                        String fileName = "com.chongchoukeji.lanrenxiyi.apk";
                        // 定义上传路径
                        String path = root_path + apk_path + "/" + fileName;
                        File localFile = new File(path);
                        if (!localFile.exists()) {
                            localFile.mkdirs();
                        }
                        file.transferTo(localFile);// 文件输出到磁盘
                        HTMLUrl = path;
                        // System.out.println("路径："+HTMLUrl);
                    }
                }
            }
        }
        Result result = new Result();// 返回信息对象
        result.setFlag(Constant.SUCCESS);
        result.setData(HTMLUrl);
        return JSON.toJSONString(result);
    }

    /**
     * <b>deleteImg</b>：(图片删除处理方法)<br>
     * <b>TODO</b>：(需通过ajaxGET请求该接口)<br>
     * <b>url</b>：(basePath../file-upload/img-del)<br>
     * @param filePath
     *            图片路径<br>
     * @param request
     *            请求对象<br>
     * @return Result<br>
     * @Exception<br>
     * @author SAM QZL
     */
    @RequestMapping(value = "/img-del", method = RequestMethod.GET)
    @ResponseBody
    public Result deleteImg(HttpServletRequest request, String filePath) throws Exception {

        Result result = new Result();
        String root_path = Messages.getString("file_path");// 文件系统根路径
        File img = new File(root_path + filePath);
        if (img.delete()) {
            result.setFlag(Constant.SUCCESS);
            result.setMessage("图片删除成功!");
            return result;
        }
        else {
            result.setFlag(Constant.FAIL);
            result.setMessage("图片删除失败!");
            return result;
        }
    }

    /**
     * <b>zipUpload</b>：(压缩包上传,支持zip)<br>
     * <b>TODO</b>：(需通过ajaxGET请求该接口)<br>
     * <b>url</b>：(basePath../file-upload/zip-upload)<br>
     * @attention zip压缩包目录:
     *            ------------------css
     *            ------------------js
     *            ------------------img
     *            ------------------xxx.html
     *            后缀名只能.html且只存在一个.html文件
     * @param request
     *            请求对象<br>
     * @param type
     *            上传类型:ABOUTUS,HELP,LAW<br>
     * @return Result<br>
     * @Exception<br>
     * @author SAM QZL
     * @throws Exception
     */
    @RequestMapping(value = "/zip-upload", method = RequestMethod.POST)
    @ResponseBody
    public String zipUpload(HttpServletRequest request, Integer type) throws Exception {

        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        /** 上传HTML文件地址URL **/
        String HTMLUrl = "";
        if (multipartResolver.isMultipart(request)) {// 如果是文件上传就执行代码
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            String root_path = Messages.getString("file_path");// 文件系统根路径
            String zip_path = "";// 压缩包路径
            switch (type) {
                case Constant.ABOUTUS:
                    zip_path = Messages.getString("zip_about_dir");// 关于我们
                    break;
                case Constant.HELP:
                    zip_path = Messages.getString("zip_help_dir");// 帮助信息
                    break;
                case Constant.LAW:
                    zip_path = Messages.getString("zip_law_dir");// 法律声明
                    break;
                default:
                    break;
            }
            /** 上传先删除原有文件 **/
            File delete = new File(root_path + zip_path);// 上传前覆盖删除目录
            FileUploadController.deleteAllFilesOfDir(delete);
            /** 上传先删除原有文件 **/
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {// 如果文件不为null
                    // 取得当前上传文件的文件名称
                    String originalFilename = file.getOriginalFilename();
                    if (!"".equals(originalFilename.trim())) {
                        // 重命名上传后的文件名file.getOriginalFilename().lastIndexOf(".")
                        String fileName = RandomUtil.getUUID()
                                + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                        // 定义上传路径
                        String path = root_path + zip_path + "/" + fileName;
                        File localFile = new File(path);
                        if (!localFile.exists()) {
                            localFile.mkdirs();
                        }
                        file.transferTo(localFile);// 文件输出到磁盘
                        /** 解压压缩包 **/
                        ZipUtils.decompress(path);// 解压文件 到当前文件夹
                        File del = new File(path);
                        del.delete(); // 解压后删除压缩包
                        File catalog = new File(root_path + zip_path);// 找到解压后的路径
                        // 默认为:解压后第一层就必须有HTML文件
                        // 有且只有一个!
                        File[] htmls = catalog.listFiles(new FilterBysuffix("html"));
                        HTMLUrl = zip_path + "/" + htmls[0].getName();
                    }
                }
            }
        }
        Result result = new Result();// 返回信息对象
        result.setFlag(Constant.SUCCESS);
        result.setData(HTMLUrl);
        return JSON.toJSONString(result);
    }

    /**
     * <b>imageUpload</b>：(上传照片并返回照片URI)<br>
     * <b>TODO</b>：(需通过ajax请求该接口异步提交上传图片)<br>
     * <b>url</b>：(basePath../image-upload/image-upload)<br>
     * @param request
     * <br>
     * @return Result<br>
     * @Exception<br>
     * @author SAM QZL
     */
    @RequestMapping(value = "/image-upload")
    @ResponseBody
    public String imageUpload(HttpServletRequest request, Integer type, HttpServletResponse response) throws IllegalStateException, IOException {

        /* response.setContentType("text/json;charset=UTF-8"); */
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        String image_url = "";
        if (multipartResolver.isMultipart(request)) {// 如果是文件上传
            // 转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            String image_path = "";
            String root_path = Messages.getString("file_path");// 文件系统根路径
            /** 0代表商品图片,1代表头像 图片目录选择 ,2代表banner **/
            switch (type) {
                case Constant.ITEMIMG:
                    image_path = Messages.getString("item_img_dir");// 商品图片路径
                    break;
                case Constant.HEADPHOTO:
                    image_path = Messages.getString("staff_headPhoto_dir");// 企业APP端用户头像
                    break;
                case Constant.BANNERIMG:
                    image_path = Messages.getString("banner_img_dir");// banner
                    break;
                default:
                    break;
            }
            while (iter.hasNext()) {
                // 取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String originalFilename = file.getOriginalFilename();
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (originalFilename.trim() != "") {
                        // 重命名上传后的文件名file.getOriginalFilename().lastIndexOf(".")
                        String fileName = RandomUtil.getUUID()
                                + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
                        // 定义上传路径
                        String path = root_path + image_path + "/" + fileName;
                        File localFile = new File(path);
                        if (!localFile.exists()) {
                            localFile.mkdirs();
                        }
                        file.transferTo(localFile);
                        /** 图片裁剪压缩 **/
                        ImgCompress imgCompress = new ImgCompress(path, path);
                        /** 0代表商品图片,1代表头像 图片压缩比例选择选择 **/
                        switch (type) {
                            case Constant.ITEMIMG:
                                /** 要素尺寸 340X226 **/
                                imgCompress.resize(340, 226);
                                break;
                            case Constant.BANNERIMG:
                                imgCompress.resize(750, 450);
                                break;
                            case Constant.HEADPHOTO:
                                /** 头像60x60 **/
                                /* imgCompress.resize(60, 60); */
                                break;
                            default:
                                break;
                        }
                        image_url = image_path + "/" + fileName;// 生成能够访问的URL
                    }
                }
                // 记录上传该文件后的时间
            }
        }
        Result result = new Result();// 返回信息对象
        result.setFlag(Constant.SUCCESS);
        result.setData(image_url);
        return JSON.toJSONString(result);
    }

    /**
     * 删除文件夹
     * @param<br>
     * @param<br>
     * @return void<br>
     * @Exception<br>
     * @author SAM QZL
     */
    public static void deleteAllFilesOfDir(File path) {

        if (!path.exists()) {
            return;
        }
        if (path.isFile()) {
            path.delete();
            return;
        }
        File[] files = path.listFiles();
        assert files != null;
        for (File file : files) {
            deleteAllFilesOfDir(file);
        }
        path.delete();
    }
}

/**
 *
 * <b>项目名称</b>：lanrenxiyi<br>
 * <b>类名称</b>：FilterBysuffix<br>
 * <b>类描述</b>：根据文件后缀过滤文件<br>
 * <b>创建人</b>：SAM QZL<br>
 * <b>创建时间</b>：2015-11-23 下午5:55:59<br>
 * <b>修改人</b>：SAM QZL<br>
 * <b>修改时间</b>：2015-11-23 下午5:55:59<br>
 * <b>修改备注</b>：<br>
 * @author SAM QZL<br>
 * @version
 *
 */
class FilterBysuffix implements FilenameFilter {

    public FilterBysuffix(String suffix) {

        super();
        this.suffix = suffix;
    }

    private String suffix;

    @Override
    public boolean accept(File file, String name) {

        return name.endsWith(suffix);
    }
}

/**
 * ]
 *
 * <b>项目名称</b>：lanrenxiyi<br>
 * <b>类名称</b>：ImgCompress<br>
 * <b>类描述</b>：图像压缩处理类<br>
 * <b>创建人</b>：SAM QZL<br>
 * <b>创建时间</b>：2015-11-9 上午11:00:20<br>
 * <b>修改人</b>：SAM QZL<br>
 * <b>修改时间</b>：2015-11-9 上午11:00:20<br>
 * <b>修改备注</b>：<br>
 * @author SAM QZL<br>
 * @version
 *
 */
class ImgCompress {

    private Image img;
    private int width;
    private int height;
    private String targetFilePath;

    /**
     *
     * 创建一个新的实例 ImgCompress.
     * 构造函数获取图像原始尺寸
     * @param fileName
     * @throws IOException
     */
    public ImgCompress(String fileName, String targetFilePath) throws IOException {

        File file = new File(fileName);// 读入文件
        this.img = ImageIO.read(file); // 构造Image对象
        this.width = img.getWidth(null); // 得到源图宽
        this.height = img.getHeight(null); // 得到源图长
        this.targetFilePath = targetFilePath;
    }

    /**
     * 按照宽度还是高度进行压缩
     * @param w
     *            int 最大宽度
     * @param h
     *            int 最大高度
     */
    public void resizeFix(int w, int h) throws IOException {

        if (width / height > w / h) {
            resizeByWidth(w);
        }
        else {
            resizeByHeight(h);
        }
    }

    /**
     * 以宽度为基准，等比例放缩图片
     * @param w
     *            int 新宽度
     */
    public void resizeByWidth(int w) throws IOException {

        int h = (int) (height * w / width);
        resize(w, h);
    }

    /**
     * 以高度为基准，等比例缩放图片
     * @param h
     *            int 新高度
     */
    public void resizeByHeight(int h) throws IOException {

        int w = width * h / height;
        resize(w, h);
    }

    /**
     * 强制压缩/放大图片到固定的大小
     * @param w
     *            int 新宽度
     * @param h
     *            int 新高度
     */
    public void resize(int w, int h) throws IOException {

        Thumbnails.of(targetFilePath).size(w, h).toFile(targetFilePath);
        // Thumbnails.of(targetFilePath).scale(0.25f).toFile(targetFilePath);
    }

    @Deprecated
    public static void ZoomTheImage(String fileUrl, String newUrl, int width, int height) {

        java.io.File file = new java.io.File(fileUrl); // 读入刚才上传的文件
        Image src = null;
        try {
            src = javax.imageio.ImageIO.read(file);
            // 构造Image对象
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // tag.getGraphics().drawImage(src, 0, 0, width, height, null); //
            // 绘制缩小后的图
            tag.getGraphics().drawImage(src.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            FileOutputStream newimage = new FileOutputStream(newUrl); // 输出到文件流
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
            JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
            jep.setQuality((float) 1.0, true);
            encoder.encode(tag, jep);
            newimage.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
