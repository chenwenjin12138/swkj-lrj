package service.file;

import org.springframework.web.multipart.MultipartFile;
import service.file.vo.UIFile;

/**
 * @author Lxh
 * @date 2020/3/26 9:38
 */
public interface IFileService {

    UIFile fileUpLoad(MultipartFile upLoad);
}
