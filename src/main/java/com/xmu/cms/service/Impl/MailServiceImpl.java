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
        return null;
    }

    @Override
    public Map<String, String> sendPassword(String account) {
        Map<String, String> messages = new HashMap<String, String>(2);
        String name = null;
        String email = null;
        String password = null;
        Teacher teacher = teacherDao.getTeacherByAccount(account);
        if (teacher != null) {
            name = teacher.getName();
            email = teacher.getEmail();
            password = teacher.getPassword();
            return messages;
        }
        Student student = studentMapper.getStudentByAccount(account);
        if (student != null) {
            name = student.getName();
            email = student.getEmail();
            password = student.getPassword();
            messages.put("message", "Success");
        }
        if (email == null || password == null) {
            messages.put("message", "Error");
            return messages;
        }
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(sender);
        mail.setTo(email);
        mail.setSubject("主题：忘记密码邮件");
        mail.setText("用户" + name+"您好\n"+"您刚刚申请的忘记密码\n"+"您的密码是："+password+"\n如果您不需要获取密码，或者您从未点击过“忘记密码”按钮，请忽略本邮件。\n");
        mailSender.send(mail);
        return messages;
    }
}
