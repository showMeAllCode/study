package com.study.sys.utils;

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
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wxl
 * @date 2020/4/8 09:10:15
 */
@Slf4j
public class ExcelExport {

    /**
     * 普通Excel导出
     * @param sheetName 表名
     * @param headers 表头
     * @param dataList 数据，null值请转换为"";
     */
    public void commonExcelExport(String sheetName, String[] headers, List<List<String>> dataList, HttpServletResponse response) throws IllegalAccessException, InstantiationException, IOException {
        try {
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
     * @param tableName 需要忽略的字段
     * @param dataList 数据
     */
    public <T> void entityExcelExport(String sheetName, String tableName, String ignoreNums, List<T> dataList, HttpServletResponse response) throws IllegalAccessException, InstantiationException, IOException, SQLException, ClassNotFoundException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        List<Object> entityFieldsList = new ArrayList<>();
        List<Object> entityCommentList = new ArrayList<>();
        EntityFieldsAndCommentExportUtil.get(entityFieldsList, entityCommentList, tableName);
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet(sheetName);
        XSSFRow row = sheet.createRow((short) 0);
        for (int i = 0; i < entityCommentList.size(); i++) {
            row.createCell(i).setCellValue(entityCommentList.get(i).toString());
        }
        for (int i = 0; i < dataList.size(); i++){
            XSSFRow newRow = sheet.createRow((short) i + 1);
            Class<T> clazz = (Class<T>) dataList.get(i).getClass();
            T entity = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            int m = 0;
            for (int j = 0; j < dataList.size(); j++){
                m = j;
                fields[j].setAccessible(true);
                if(!"serialVersionUID".equals(fields[j].get(entity))) {
                    String value = "";
                    if (fields[j].get(entity) != null) {
                        if (isDate(fields[j])) {
                            value = dateFormat.format(fields[j].get(entity));
                        } else {
                            value = fields[j].get(entity).toString();
                        }
                    }
                    newRow.createCell(j).setCellValue(value);
                }
            }
        }
        setResponse(sheetName, response);
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        wb.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    public Boolean isDate(Field field){
        Boolean flag = false;
        if(field.getType() == LocalDateTime.class || field.getType() == LocalDate.class || field.getType() == Date.class){
            flag = true;
        }
        return flag;
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
