package com.xmu.cms.service.Impl;

import com.xmu.cms.service.FileService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
public class FileServiceImpl implements FileService {

    @Value("${file.folder}")
    private String UPLOADED_FOLDER;

    @Override
    public Map<String, String> uploadClbumFile(Integer clbumId, MultipartFile file) {
        Map<String, String> messages = new HashMap<String, String>(2);
        if (file.isEmpty()) {
            messages.put("message", "Please select a file");
            return messages;
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + clbumId.toString() + "//" + file.getOriginalFilename());
            Files.write(path, bytes);
            messages.put("message", "Success");
        } catch (IOException e) {
            messages.put("message", "Error");
            return messages;
        }
        return messages;
    }
}
