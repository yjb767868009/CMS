package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.entity.Student;
import com.xmu.cms.service.FileService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${file.folder}")
    private String UPLOADED_FOLDER;

    @Autowired
    private StudentDao studentDao;

    private Map<String, String> saveFile(String filePath, MultipartFile file) {
        Map<String, String> messages = new HashMap<String, String>(2);
        if (file.isEmpty()) {
            messages.put("message", "Please select a file");
            return messages;
        }
        String fatherPath = filePath.substring(0, filePath.lastIndexOf("\\"));
        File fatherFile = new File(fatherPath);
        if (!fatherFile.exists()) {
            fatherFile.mkdirs();
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);
            messages.put("message", "Success");
        } catch (IOException e) {
            messages.put("message", "Error");
            return messages;
        }
        return messages;
    }

    private void readXLS(String filePath) throws Exception {
        InputStream input = new FileInputStream(filePath);
        Workbook workbook = null;
        String fileType = filePath.substring(filePath.lastIndexOf("."));
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
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        List<List<Object>> list = new ArrayList<List<Object>>();
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            for (int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null || row.getFirstCellNum() == j) {
                    continue;
                }
                List<Object> li = new ArrayList<Object>();
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    Object value = null;
                    DecimalFormat df = new DecimalFormat("0");
                    cell = row.getCell(y);
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
                    li.add(value);
                }
                list.add(li);
            }
        }
        for (List<Object> ob : list) {
            String account = (String) ob.get(0);
            String name = (String) ob.get(1);
            Student student = new Student();
            student.setAccount(account);
            student.setName(name);
            student.setPassword("123456");
            student.setActivation(false);
            studentDao.newStudent(student);
        }
    }

    @Override
    public Map<String, String> uploadKlassFile(Integer klassId, MultipartFile file) {
        String filePath = UPLOADED_FOLDER + "class" + File.separator + klassId.toString() + File.separator + file.getOriginalFilename();
        Map<String, String> messages = new HashMap<String, String>(2);

        messages = saveFile(filePath, file);
        try {
            readXLS(filePath);
        } catch (Exception e) {
            messages.put("message", e.getMessage());
        }
        return messages;
    }

    @Override
    public Map<String, String> uploadAttendanceFile(Integer attendanceId, MultipartFile file) {
        String filePath = UPLOADED_FOLDER + "attendance" + File.separator + attendanceId.toString() + File.separator + file.getOriginalFilename();
        return saveFile(filePath, file);
    }
}
