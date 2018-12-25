package com.xmu.cms.support;

import com.xmu.cms.entity.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
        File fatherFile = new File(filePath);
        if (!fatherFile.exists()) {
            fatherFile.mkdirs();
        }
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath + File.separator + file.getOriginalFilename());
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

    private static String getKlassFilePath(BigInteger klassId) {
        return UPLOADED_FOLDER + "class" + File.separator + klassId.toString();
    }

    public static List<Student> uploadKlassFile(BigInteger klassId, MultipartFile file) throws Exception {
        String filePath = getKlassFilePath(klassId);
        saveFile(filePath, file);
        return analysisFile(filePath + File.separator + file.getOriginalFilename());
    }

    private static String getAttendancePath(BigInteger attendanceId) {
        return UPLOADED_FOLDER + "attendance" + File.separator + attendanceId.toString();
    }


    public static void downloadAttendanceFile(BigInteger attendanceId, String fileName) throws Exception {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) throw new Exception("请求失败");
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) throw new Exception("请求失败");


        if (fileName == null) throw new Exception("空的文件名");
        String filePath = getAttendancePath(attendanceId);
        File file = new File(filePath, fileName);
        if (file.exists()) {
            byte[] buffer = new byte[1024];
            try (FileInputStream fis = new FileInputStream(file); BufferedInputStream bis = new BufferedInputStream(fis)) {
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            }
        } else throw new Exception("文件不存在");
    }

    public static void uploadAttendanceFile(BigInteger attendanceId, MultipartFile file) throws Exception {
        String filePath = getAttendancePath(attendanceId);
        saveFile(filePath, file);
    }
}
