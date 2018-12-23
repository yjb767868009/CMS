package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.*;
import com.xmu.cms.service.MailService;
import com.xmu.cms.support.MyUser;
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
    private TeacherDao teacherDao;

    @Autowired
    private StudentDao studentDao;

    @Value("${spring.mail.username}")
    private String sender;

    public void sendEmailToAccount(String subject, String text, String email) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(sender);
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setText(text);
        mailSender.send(mail);
    }

    @Override
    public Map<String, String> sendPassword(MyUser user) {
        Map<String, String> messages = new HashMap<String, String>(2);
        String name = null;
        String email = null;
        String password = null;
        String account = user.getAccount();
        Teacher teacher = teacherDao.getTeacherByAccount(account);
        if (teacher != null) {
            name = teacher.getName();
            email = teacher.getEmail();
            password = teacher.getPassword();
            return messages;
        }
        Student student = studentDao.getStudentByAccount(account);
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
        String subject = "主题：忘记密码邮件";
        String text = "用户 " + name + ",您好\n" + "您刚刚申请的忘记密码\n" + "您的密码是：" + password + "\n如果您不需要获取密码，或者您从未点击过“忘记密码”按钮，请忽略本邮件。\n";
        sendEmailToAccount(subject, text, email);
        return messages;
    }

    @Override
    public void sendShareTeam(ShareTeam newShareTeam) {
        String subject = "主题：共享组队邮件";
        // TODO: 2018/12/23
    }

    @Override
    public void sendShareSeminar(ShareTeam newShareSeminar) {
        String subject = "主题：共享讨论课邮件";
        // TODO: 2018/12/23
    }

    @Override
    public void sendUpadteShareTeam(ShareTeam newShareTeam) {
        // TODO: 2018/12/23  
    }

    @Override
    public void sendUpadteShareSeminar(ShareSeminar newShareSeminar) {
// TODO: 2018/12/23  
    }

    @Override
    public void sendUpdateTeamApplication(TeamApplication newTeamApplication) {
// TODO: 2018/12/23  
    }
}
