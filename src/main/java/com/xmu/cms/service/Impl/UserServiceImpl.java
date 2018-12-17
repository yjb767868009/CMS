package com.xmu.cms.service.Impl;

import com.xmu.cms.dao.AdminDao;
import com.xmu.cms.dao.StudentDao;
import com.xmu.cms.dao.TeacherDao;
import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Student;
import com.xmu.cms.entity.Teacher;
import com.xmu.cms.service.UserService;
import com.xmu.cms.support.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JuboYu on 2018/11/26.
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private AdminDao adminDao;

    @Override
    public Map<String, String> adminLogIn(Admin admin) {
        Map<String, String> message = new HashMap<String, String>(2);
        Admin findAdmin = adminDao.getAdminByAccount(admin.getAccount());
        if (findAdmin == null) {
            message.put("message", "No this account");
        } else {
            if (findAdmin.getPassword().equals(admin.getPassword())) {
                message.put("message", "Success");
            } else {
                message.put("message", "Account or Password error");
            }
        }
        return message;
    }

    @Override
    public Map<String, String> userLogIn(String account, String password) {
        Map<String, String> message = new HashMap<String, String>(2);
        Teacher teacher = teacherDao.getTeacherByAccount(account);
        if (teacher == null) {
            Student student = studentDao.getStudentByAccount(account);
            if (student == null) {
                message.put("message", "No this account");
            } else {
                if (password.equals(student.getPassword())) {
                    message.put("message", "student");
                    message.put("activation", "false");
                } else {
                    message.put("message", "Account or password error");
                }
            }
        } else {
            if (password.equals(teacher.getPassword())) {
                message.put("message", "teacher");
                message.put("activation", "false");
            } else {
                message.put("message", "Account or password error");
            }
        }
        return message;
    }

    @Override
    public Map<String, String> getMyInfo(Integer userId, String userType) {
        Map<String, String> messages = new HashMap<String, String>(2);
        if (userType.equals("teacher")) {
            Teacher teacher = teacherDao.getTeacherById(userId);
            messages.put("name", teacher.getName());
            messages.put("account", teacher.getAccount());
            messages.put("message", "Success");
            return messages;
        }
        if (userType.equals("student")) {
            Student student = studentDao.getStudentById(userId);
            messages.put("name", student.getName());
            messages.put("account", student.getAccount());
            messages.put("message", "Success");
            return messages;
        }
        messages.put("message", "Error");
        return messages;
    }

    @Override
    public Map<String, String> modifyEmail(Integer userId, String userType, MyUser user) {
        Map<String, String> messages = new HashMap<String, String>(2);
        if (userType.equals("teacher")) {
            Integer count = teacherDao.modifyTeacherEmail(userId, user.toTeacher());
            if (count == 1) {
                messages.put("message", "Success");
                return messages;
            }
        }
        if (userType.equals("student")) {
            Integer count = studentDao.modifyStudentEmail(userId, user.toStudent());
            if (count == 1) {
                messages.put("message", "Success");
                return messages;
            }
        }
        messages.put("message", "Error");
        return messages;
    }

    @Override
    public Map<String, String> modifyPassword(Integer userId, String userType, MyUser user) {
        Map<String, String> messages = new HashMap<String, String>(2);
        if (userType.equals("teacher")) {
            Integer count = teacherDao.modifyTeacherPassword(userId, user.toTeacher());
            if (count == 1) {
                messages.put("message", "Success");
                return messages;
            }
        }
        if (userType.equals("student")) {
            Integer count = studentDao.modifyStudentPassword(userId, user.toStudent());
            if (count == 1) {
                messages.put("message", "Success");
                return messages;
            }
        }
        messages.put("message", "Error");
        return messages;
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    @Override
    public Map<String, String> createTeacher(Teacher teacher) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = teacherDao.createTeacher(teacher);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Insert error");
        }
        return message;
    }

    @Override
    public Map<String, String> deleteTeacher(Integer teacherId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = teacherDao.deleteTeacherByTeacherId(teacherId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Delete error");
        }
        return message;
    }

    @Override
    public Teacher getTeacherByAccount(String account) {
        return teacherDao.getTeacherByAccount(account);
    }

    @Override
    public List<Teacher> getTeacherByName(String name) {
        return teacherDao.getTeacherByName(name);
    }

    @Override
    public Teacher getTeacherById(Integer teacherId) {
        return teacherDao.getTeacherById(teacherId);
    }

    @Override
    public Map<String, String> updateTeacherInfo(Integer teacherId, Teacher teacher) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = teacherDao.updateTeacherInfo(teacherId, teacher);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public Map<String, String> updateTeacherPassword(Integer teacherId, Teacher teacher) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = teacherDao.updateTeacherPassword(teacherId, teacher);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Error");
        }
        return message;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @Override
    public Map<String, String> modifyStudentInfo(Integer studentId, Student student) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = studentDao.modifyStudentInfo(studentId, student);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Update error");
        }
        return message;
    }

    @Override
    public Map<String, String> modifyStudentPassword(Integer studentId, Student student) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = studentDao.modifyStudentPassword(studentId, student);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Update error");
        }
        return message;
    }

    @Override
    public Map<String, String> deleteStudent(Integer studentId) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = studentDao.deleteStudent(studentId);
        if (count == 1) {
            message.put("message", "Success");
        } else {
            message.put("message", "Delete error");
        }
        return message;
    }

    @Override
    public List<Student> getStudentByName(String name) {
        return studentDao.getStudentByName(name);
    }

    @Override
    public Student getStudentByAccount(String account) {
        return studentDao.getStudentByAccount(account);
    }

    @Override
    public Map<String, String> activateStudent(Integer studentId, Student student) {
        Map<String, String> message = new HashMap<String, String>(2);
        Integer count = studentDao.activateStudent(studentId, student);
        if (count == 1) {
            message.put("message", "成功");
        } else {
            message.put("message", "信息不合法");
        }
        return message;
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public List<Student> getNoTeamStudent(Integer courseId) {
        return studentDao.getNoTeamStudentInCourse(courseId);
    }
}
