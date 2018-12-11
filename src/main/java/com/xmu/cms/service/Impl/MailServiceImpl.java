package com.xmu.cms.service.Impl;

import com.xmu.cms.mapper.StudentMapper;
import com.xmu.cms.mapper.TeacherMapper;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JuboYu on 2018/12/5.
 * @version 1.0
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TeacherMapper teacherDao;

    @Autowired
    private StudentMapper studentMapper;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public Map<String, String> sendEmailByAccount(String account) {
        Map<String, String> messages = new HashMap<String, String>(2);
        String email = null;
        Teacher teacher = teacherDao.getTeacherByAccount(account);
        if (teacher != null) {
            email = teacher.getEmail();
            return messages;
        }
        Student student = studentMapper.getStudentByAccount(account);
        if (student != null) {
            email = student.getEmail();
            messages.put("message", "Success");
        }
        if (email == null) {
            messages.put("message", "Error");
            return messages;
        }
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(sender);
        mail.setTo(email);
        mail.setSubject("主题：简单邮件");
        mail.setText("测试邮件内容");
        mailSender.send(mail);
        return messages;
    }
}
