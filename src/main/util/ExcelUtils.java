package util;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @Author Lxh
 * @Date 2020/5/21 14:52
 */
public class ExcelUtils<T> {

    public static <T> void exportExcel2(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern) {

        XSSFWorkbook workbook2007 = new XSSFWorkbook();
        /** 工作目录创建 **/
        XSSFSheet sheet = workbook2007.createSheet(title);
        /** 设置默认宽度 **/
        sheet.setDefaultColumnWidth(15);
        /** 这只表头单元格格式 **/
        CellStyle titleCellStyle = workbook2007.createCellStyle();
        /** 设置样式 **/
        titleCellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        titleCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        titleCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        titleCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        /** 创建表头字体 **/
        Font titleFont = workbook2007.createFont();
        /** 设置字体样式 **/
        titleFont.setColor(HSSFColor.VIOLET.index);
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        /** 字体应用到表头 **/
        titleCellStyle.setFont(titleFont);
        /** 设置内容样式 **/
        CellStyle contentCellStyle = workbook2007.createCellStyle();
        contentCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
        contentCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        contentCellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        contentCellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        contentCellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
        contentCellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
        contentCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        contentCellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        /** 设置内容自动换行 **/
        contentCellStyle.setWrapText(true);
        /** 设置内容字体 **/
        Font contentFont = workbook2007.createFont();
        contentFont.setBoldweight(Font.BOLDWEIGHT_NORMAL);
        /** 设置字体 **/
        contentCellStyle.setFont(contentFont);
        /** 创建标题 **/
        XSSFRow titleRow = sheet.createRow(0);
        /** 设置宽度最后一列宽度大 **/
        sheet.setColumnWidth(headers.length - 1, 100 * 256);
        /** 生成表头 **/
        for (int i = 0; i < headers.length; i++) {
            /** 创建表头单元格 **/
            XSSFCell cell = titleRow.createCell(i);
            /** 样式应用 **/
            cell.setCellStyle(titleCellStyle);
            RichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        /** 内容生成 **/
        Iterator<T> it = dataset.iterator();
        /** 行索引 --从1开始 **/
        int index = 1;
        while (it.hasNext()) {
            /** 创建内容行 **/
            XSSFRow contentRow = sheet.createRow(index);
            /** 获取当前行记录 **/
            T t = (T) it.next();
            /** 获取字段名称 **/
            Field[] fields = t.getClass().getDeclaredFields();
            for (int j = 0; j < fields.length; j++) {
                /** 创建内容列 **/
                XSSFCell contentCell = contentRow.createCell(j);
                /** 设置样式 **/
                contentCell.setCellStyle(contentCellStyle);
                Field field = fields[j];
                String fieldName = field.getName();
                /** 获取get方法的名称 **/
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase()
                        + fieldName.substring(1);
                try {
                    Method method = t.getClass().getMethod(getMethodName);
                    Object value = method.invoke(t);
                    String finalText = "";
                    /** 如果是日期类型格式化输出 **/
                    if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        finalText = sdf.format(date);
                    }
                    /** 其余都是tostring **/
                    else {
                        finalText = value == null ? "" : value.toString();
                    }
                    /** 处理finalText **/
                    Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                    Matcher matcher = p.matcher(finalText);
                    /** 如果是小数单独处理 **/
                    if (matcher.matches()) {
                        contentCell.setCellValue(Double.parseDouble(finalText));
                    }
                    /** 普通字符串 **/
                    else {
                        XSSFRichTextString richString = new XSSFRichTextString(finalText);
                        Font font_ = workbook2007.createFont();
                        font_.setColor(HSSFColor.BLACK.index);
                        richString.applyFont(font_);
                        contentCell.setCellValue(richString);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            index++;
        }
        /** 写到输出流 **/
        try {
            workbook2007.write(out);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                workbook2007.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
