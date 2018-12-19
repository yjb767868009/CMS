package com.xmu.cms.support;

import com.xmu.cms.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
public class XLSUtils {

    private static Workbook getWorkBook(String filePath) throws Exception {
        InputStream input = new FileInputStream(filePath);
        String fileType = filePath.substring(filePath.lastIndexOf("."));
        Workbook workbook;
        switch (fileType) {
            case ".xls":
                workbook = new HSSFWorkbook(input);
                break;
            case ".xlsx":
                workbook = new XSSFWorkbook(input);
                break;
            default:
                throw new Exception("解析文件格式错误");
        }
        return workbook;
    }

    private static List<Object> getOneList(Row row) {
        List<Object> list = new ArrayList<Object>();
        for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
            Object value = null;
            DecimalFormat df = new DecimalFormat("0");
            Cell cell = row.getCell(y);
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    value = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    value = df.format(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    value = cell.getBooleanCellValue();
                    break;
                case Cell.CELL_TYPE_BLANK:
                    value = "";
                    break;
                default:
                    break;
            }
            list.add(value);
        }
        return list;
    }


    public static List<Student> analysisFile(String filePath) throws Exception {
        Workbook workbook = getWorkBook(filePath);
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                Row row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                list.add(getOneList(row));
            }
        }
        List<Student> students=new ArrayList<Student>();
        for (List<Object> studentMessage : list) {
            String account = (String) studentMessage.get(0);
            String name = (String) studentMessage.get(1);
            Student student = new Student();
            student.setAccount(account);
            student.setName(name);
            student.setEmail("");
            student.setPassword("123456");
            student.setActivation(false);
            students.add(student);
        }
        return students;
    }

}
