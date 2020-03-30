package service.file.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Lxh
 * @date 2020/3/26 9:41
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UIFile {
    private Integer error=0;
    private String url;
    private Integer width;
    private Integer height;


    public static UIFile file() {
        return new UIFile(1, null, null, null);
    }

}
