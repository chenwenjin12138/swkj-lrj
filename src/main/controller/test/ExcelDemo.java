package controller.test;



import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.xml.internal.xsom.XSSchema;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.util.List;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/21 9:47
 */
public class ExcelDemo {
    public static void write(List<T> manList, String path, String[] title, String sheetName)  {

        HSSFWorkbook sheets = new HSSFWorkbook();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        //cellStyle.setAlignment(HSSFCellStyle);
        XSSFFont font = workbook.createFont();
        font.setFontName("微软雅黑");
        font.setColor(IndexedColors.BLUE.index);
        cellStyle.setFont(font);
        //创建表头
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i <title.length ; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title[i]);
        }
        //创建内容
        for (int i = 0; i <manList.size() ; i++) {
            XSSFRow row1 = sheet.createRow(i + 1);
            /*row1.createCell(0).setCellValue(manList.get(i).getId());
            row1.createCell(1).setCellValue(manList.get(i).getName());
            row1.createCell(2).setCellValue(manList.get(i).getDeptId());*/
        }
        //写入excel表格
        try {
            FileOutputStream out = new FileOutputStream(path);
            workbook.write(out);
            out.flush();
            out.close();
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
