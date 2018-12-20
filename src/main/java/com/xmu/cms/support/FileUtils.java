package com.xmu.cms.support;

import com.xmu.cms.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/12/19.
 * @version 1.0
 */
public class FileUtils {

    @Value("${file.folder}")
    private static String UPLOADED_FOLDER;

    private static void saveFile(String filePath, MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new Exception("请选择一个文件");
        }
        String fatherPath = filePath.substring(0, filePath.lastIndexOf("\\"));
        File fatherFile = new File(fatherPath);
        if (!fatherFile.exists()) {
            fatherFile.mkdirs();
        }
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
        Files.write(path, bytes);
    }

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

    private static List<Student> analysisFile(String filePath) throws Exception {
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
                List<Object> objectList = new ArrayList<Object>();
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
                    objectList.add(value);
                }
                list.add(objectList);
            }
        }
        List<Student> students = new ArrayList<Student>();
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

    private static String getKlassFilePath(BigInteger klassId, MultipartFile file) {
        return UPLOADED_FOLDER + "class" + File.separator + klassId.toString() + File.separator + file.getOriginalFilename();
    }

    public static List<Student> uploadKlassFile(BigInteger klassId, MultipartFile file) throws Exception {
        String filePath = getKlassFilePath(klassId, file);
        saveFile(filePath, file);
        return analysisFile(filePath);
    }
}
