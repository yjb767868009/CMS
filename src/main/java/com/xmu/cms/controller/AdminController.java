package com.xmu.cms.controller;

import com.xmu.cms.entity.Student;
import com.xmu.cms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xmu.cms.entity.Admin;
import com.xmu.cms.entity.Teacher;


import java.util.ArrayList;
import java.util.List;

/**
 * @author JuboYu on 2018/11/23.
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private List<Teacher> teachers = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    @Autowired
    private StudentService studentService;

    @PostMapping(value = "/login")
    public Admin adminLogIn(@ModelAttribute Admin admin){

        return admin;
    }

    @GetMapping(value = "/teachers")
    public List<Teacher> getAllTeachers(){
        return teachers;
    }

    @GetMapping(value = "/students")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @PostMapping(value = "/teacher")
    public Teacher newTeacher(@ModelAttribute Teacher teacher){
        return teacher;
    }

    @PutMapping(value = "/teacher")
    public Teacher modifyTeacher(@ModelAttribute Teacher teacher){
        return  teacher;
    }

    @DeleteMapping(value = "/teacher")
    public Teacher deleteTeacher(@ModelAttribute Teacher teacher){
        return teacher;
    }

    @PutMapping(value = "/student")
    public Student modifyStudent(@ModelAttribute Student student){
        return student;
    }

    @DeleteMapping(value = "/student")
    public Student deleteStudent(@ModelAttribute Student student){
        return student;
    }

}
