package service.file.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.file.IFileService;
import service.file.vo.UIFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Lxh
 * @date 2020/3/26 9:39
 */
@Service
@PropertySource("classpath:UIFile.properties")
public class FileServiceImpl implements IFileService {

    @Value("${image.localDir}")
    private String localDir;
    @Value("${image.localDirUrl}")
    private String localDirUrl;

    /*
     * 上传图片文件
     * */
    @Override
    public UIFile fileUpLoad(MultipartFile upLoad) {
        UIFile uiFile = new UIFile();
        String fileName = Objects.requireNonNull(upLoad.getOriginalFilename()).toLowerCase();
        if(!fileName.matches("^.\\.(jpg|png|gif)$")) {
            return UIFile.file();
        }
        try {
            BufferedImage buffImage = ImageIO.read(upLoad.getInputStream());
            int width = buffImage.getWidth();
            int height = buffImage.getHeight();
            if(width==0||height==0) {
                return UIFile.file();
            }
            String dateDir = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
            String fileDirPath = localDir + dateDir;
            File dirFile = new File(fileDirPath);
            if(!dirFile.exists()) {
                dirFile.mkdirs();
            }
            int index = fileName.lastIndexOf(".");
            String fileType = fileName.substring(index);
            String uuid = UUID.randomUUID().toString();
            String realFileName = uuid + fileType;
            upLoad.transferTo(new File(fileDirPath+realFileName));
            String url = localDirUrl+dateDir+realFileName;
            uiFile.setHeight(height).setWidth(width).setUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
            //throw new ServiceException("文件保存出错");
            return UIFile.file();
        }
        return uiFile;
    }
}
