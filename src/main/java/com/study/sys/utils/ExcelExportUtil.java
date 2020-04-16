package com.study.sys.utils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.study.sys.utils.EntityFieldsAndCommentExportUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wxl
 * @date 2020/4/8 09:10:15
 */
@Slf4j
public class ExcelExportUtil {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**
     * 普通Excel导出
     * @param sheetName 表名
     * @param headers 表头
     * @param dataList 数据，null值请转换为"";
     */
    public void commonExcelExport(String sheetName, String[] headers, List<List<String>> dataList, HttpServletResponse response) throws IllegalAccessException, InstantiationException, IOException {
        try {
            Assert.notNull(sheetName, "sheetName不能为空");
            Assert.notEmpty(headers, "headerList不能为空");
            Assert.notEmpty(dataList, "dataList不能为空");
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(sheetName);
            XSSFRow row = sheet.createRow((short) 0);
            for (int i = 0; i < headers.length; i++) {
                row.createCell(i).setCellValue(headers[i]);
            }
            for (int i = 0; i < dataList.size(); i++) {
                List<String> data = dataList.get(i);
                XSSFRow newRow = sheet.createRow((short) i + 1);
                for (int j = 0; j < data.size(); j++) {
                    newRow.createCell(j).setCellValue(data.get(j));
                }
            }
            setResponse(sheetName, response);
            OutputStream outputStream =  new BufferedOutputStream(response.getOutputStream());
            try {
                wb.write(outputStream);
                outputStream.flush();
            }finally {
                outputStream.close();
            }
        }catch (Exception e){
            log.error("excel导出失败，失败原因：{}", e);
            throw new RuntimeException("excel导出失败");
        }
    }

    /**
     * 实体类的Excel导出
     * 本方法将读取数据库中对应表的字段注释信息作为表头
     * @param sheetName xls表名
     * @param tableName 数据库表名
     * @param tableName 需要忽略的字段，以","分隔，例："0,3,4"
     * @param dataList 数据
     * @param map 需要处理的枚举，key为枚举，value为信息，例：现在有枚举：YES("yes","正确");则map.put("YES","正确")
     * @param response
     */
    public <T> void entityExcelExport(String sheetName, String tableName, String ignoreNums, List<T> dataList,
                                      Map<String,String> map, HttpServletResponse response) {
        try {
            Assert.notNull(sheetName,"sheetName不能为空");
            Assert.notNull(tableName,"tableName不能为空");
            Assert.notNull(ignoreNums,"ignoreNums不能为空");
            Assert.notEmpty(dataList,"dataList不能为空");
            ignoreNums = ignoreNums + ",";
            List<Object> entityFieldsList = new ArrayList<>();
            List<Object> entityCommentList = new ArrayList<>();
            EntityFieldsAndCommentExportUtil.get(entityFieldsList, entityCommentList, tableName);
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet(sheetName);
            XSSFRow row = sheet.createRow((short) 0);
            Integer n = 0;
            for (int i = 0; i < entityCommentList.size(); i++) {
                if (!ignoreNums.contains(i + ",")) {
                    row.createCell(n).setCellValue(entityCommentList.get(i).toString());
                    n++;
                }
            }
            for (int i = 0; i < dataList.size(); i++) {
                n = 0;
                Integer m = 0;
                XSSFRow newRow = sheet.createRow(i + 1);
                Class<T> clazz = (Class<T>) dataList.get(i).getClass();
                T entity = dataList.get(i);
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    field.setAccessible(true);
                    if(field.getAnnotation(TableField.class) != null && field.getAnnotation(TableField.class).exist() == false){
                        continue;
                    }
                    if (!"serialVersionUID".equals(field.getName())) {
                        if (!ignoreNums.contains(m + ",")) {
                            String value = "";
                            if (field.get(entity) != null) {
                                value = formattingValue(field, entity, value, map);
                            }
                            newRow.createCell(n).setCellValue(value);
                            n++;
                        }
                        m++;
                    }
                }
            }
            setResponse(sheetName, response);
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            try {
                wb.write(outputStream);
                outputStream.flush();
            } finally {
                outputStream.close();
            }
        } catch (Exception e) {
            log.error("实体类excel导出失败，失败原因：{}", e);
            throw new RuntimeException("实体类excel导出失败");
        }
    }

    private  <T> String formattingValue(Field field, T entity, String value, Map<String,String> map) throws IllegalAccessException {
        if(field.getType() == LocalDateTime.class){
            value = dateTimeFormatter.format((LocalDateTime)field.get(entity));
        }else if (field.getType() == LocalDate.class){
            value = dateTimeFormatter.format((LocalDate)field.get(entity));
        }else if (field.getType() == Date.class){
            value = dateFormat.format((Date)field.get(entity));
        }else if (field.getType().isEnum()){
            value = map.get(field.get(entity).toString());
        } else {
            value = String.valueOf(field.get(entity));
        }
        return value;
    }

    /**
     * 设置xls返回头
     * @param sheetName
     * @param response
     * @throws UnsupportedEncodingException
     */
    private void setResponse(String sheetName, HttpServletResponse response) throws UnsupportedEncodingException {
        String filename = sheetName + ".xls";
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "iso-8859-1"));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }
}
