package com.xmu.cms.controller;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.junit.Test;

import static org.junit.Assert.*;

public class AdminControllerTest {

    @Test
    public void adminLogIn() {
        String acco="123456";
        String pass="000000";
        AdminController AC =mock(AdminController.class);
        //AC.adminLogIn(acco,pass);
        //verify(AC).adminLogIn(acco,pass);
    }

    @Test
    public void getAllTeachers() {
        AdminController AC =mock(AdminController.class);
        AC.getAllTeachers();
        verify(AC).getAllTeachers();
    }

    @Test
    public void getAllStudents() {
        AdminController AC =mock(AdminController.class);
        AC.getAllStudents();
        verify(AC).getAllStudents();
    }

    @Test
    public void newTeacher() {
        String name="梨花";
        String acc="123456";
        String pas="000000";
        String email="13354687964@qq,com";
        AdminController AC =mock(AdminController.class);
        AC.newTeacher(name,acc,pas,email);
        verify(AC).newTeacher(name,acc,pas,email);
    }

    @Test
    public void modifyTeacherById() {
        int id=1;
        String name="雪梨";
        String acc="1234567";
        String email="88888888@qq,com";
        AdminController AC =mock(AdminController.class);
        AC.modifyTeacherById(id,name,acc,email);
        verify(AC).modifyTeacherById(id,name,acc,email);
    }

    @Test
    public void modifyTeacherPasswordById() {
        int id=1;
        String pass="111111";
        AdminController AC =mock(AdminController.class);
        AC.modifyTeacherPasswordById(id,pass);
        verify(AC).modifyTeacherPasswordById(id,pass);
    }

    @Test
    public void deleteTeacher() {
        int id=1;
        AdminController AC =mock(AdminController.class);
        AC.deleteTeacher(id);
        verify(AC).deleteTeacher(id);
    }

    @Test
    public void modifyStudent() {
        int id=2;
        String name="波";
        String acc="666666";
        String email="66666666@qq,com";
        AdminController AC =mock(AdminController.class);
        AC.modifyStudent(id,name,acc,email);
        verify(AC).modifyStudent(id,name,acc,email);
    }

    @Test
    public void modifyStudentPasswordById() {
        int id=2;
        String pass="333333";
        AdminController AC =mock(AdminController.class);
        AC.modifyStudentPasswordById(id,pass);
        verify(AC).modifyStudentPasswordById(id,pass);
    }

    @Test
    public void deleteStudent() {
        int id=2;
        AdminController AC =mock(AdminController.class);
        AC.deleteStudent(id);
        verify(AC).deleteStudent(id);
    }
}