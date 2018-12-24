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

    private void sendEmailToAccount(String subject, String text, String email) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(sender);
        mail.setTo(email);
        mail.setSubject(subject);
        mail.setText(text);
        mailSender.send(mail);
    }

    private String getAnswer(Boolean status) throws Exception {
        String answer;
        if (status == null) throw new Exception("错误的组队申请返回");
        else if (status) answer = "成功";
        else answer = "失败";
        return answer;
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
    public void sendShareTeam(ShareTeam shareTeam) throws Exception {
        String subject = "主题：共享组队申请邮件";
        Teacher receiveTeacher = shareTeam.getReceiveTeacher();
        String receiveTeacherName = receiveTeacher.getName();
        String email = receiveTeacher.getEmail();
        if (receiveTeacherName == null || email == null) throw new Exception("找不到请求的老师");
        Course masterCourse = shareTeam.getMasterCourse();
        Course receiveCourse = shareTeam.getReceiveCourse();
        String text = "用户" + receiveTeacherName + ",您好\n"
                + masterCourse.getCourseName() + "-" + masterCourse.getTeacher().getName()
                + "提出与您的" + receiveCourse.getCourseName() + "共享组队。\n"
                + "请登录系统进行查看";
        sendEmailToAccount(subject, text, email);
    }

    @Override
    public void sendShareSeminar(ShareSeminar shareSeminar) throws Exception {
        String subject = "主题：共享讨论课申请邮件";
        Teacher receiveTeacher = shareSeminar.getReceiveTeacher();
        String receiveTeacherName = receiveTeacher.getName();
        String email = receiveTeacher.getEmail();
        if (receiveTeacherName == null || email == null) throw new Exception("找不到请求的老师");
        Course masterCourse = shareSeminar.getMasterCourse();
        Course receiveCourse = shareSeminar.getReceiveCourse();
        String text = "用户" + receiveTeacherName + ",您好\n"
                + masterCourse.getCourseName() + "-" + masterCourse.getTeacher().getName()
                + "提出与你的" + receiveCourse.getCourseName() + "共享讨论课。\n"
                + "请登录系统进行查看";
        sendEmailToAccount(subject, text, email);
    }

    @Override
    public void sendTeamApplication(TeamApplication teamApplication) throws Exception {
        String subject = "主题：组队合法申请邮件";
        Teacher receiveTeacher = teamApplication.getTeacher();
        String receiveTeacherName = receiveTeacher.getName();
        String email = receiveTeacher.getEmail();
        if (receiveTeacherName == null || email == null) throw new Exception("找不到请求的老师");
        Team team = teamApplication.getTeam();
        String text = "用户" + receiveTeacherName + ",您好\n"
                + team.getKlass().getName() + " " + team.getLeader().getName() + "提出队伍合法\n"
                + "理由是：" + teamApplication.getReason() + "\n"
                + "请登录系统进行查看";
        sendEmailToAccount(subject, text, email);
    }

    @Override
    public void sendUpdateShareTeam(ShareTeam shareTeam) throws Exception {
        String subject = "主题：共享组队申请返回邮件";
        Teacher receiveTeacher = shareTeam.getMasterCourse().getTeacher();
        String email = receiveTeacher.getEmail();
        if (email == null) throw new Exception("错误的发送对象");
        String answer = getAnswer(shareTeam.getStatus());
        String text = "用户" + receiveTeacher.getName() + ",您好\n"
                + "你提出的与" + shareTeam.getReceiveCourse().getCourseName() + "-" + shareTeam.getReceiveTeacher().getName() + "共享组队" + answer + "。\n"
                + "请登录系统进行查看";
        sendEmailToAccount(subject, text, email);
    }


    @Override
    public void sendUpdateShareSeminar(ShareSeminar shareSeminar) throws Exception {
        String subject = "主题：共享讨论课申请返回邮件";
        Teacher receiveTeacher = shareSeminar.getMasterCourse().getTeacher();
        String email = receiveTeacher.getEmail();
        if (email == null) throw new Exception("错误的发送对象");
        String answer = getAnswer(shareSeminar.getStatus());
        String text = "用户" + receiveTeacher.getName() + ",您好\n"
                + "你提出的与" + shareSeminar.getReceiveCourse().getCourseName() + "-" + shareSeminar.getReceiveTeacher().getName() + "共享讨论课" + answer + "。\n"
                + "请登录系统进行查看";
        sendEmailToAccount(subject, text, email);
    }

    @Override
    public void sendUpdateTeamApplication(TeamApplication teamApplication) throws Exception {
        String subject = "主题：组队合法申请返回邮件";
        Team team = teamApplication.getTeam();
        Student leader = team.getLeader();
        String email = leader.getEmail();
        String answer = getAnswer(teamApplication.getStatus());
        String text = "用户" + leader.getName() + ",您好\n"
                + "你提出的" + team.getCourse().getCourseName() + "队伍" + team.getTeamName() + "合法申请" + answer + "。\n"
                + "请登录系统进行查看";
        sendEmailToAccount(subject, text, email);
    }
}
