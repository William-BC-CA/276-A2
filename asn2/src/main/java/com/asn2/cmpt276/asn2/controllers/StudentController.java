package com.asn2.cmpt276.asn2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.asn2.cmpt276.asn2.models.Student;
import com.asn2.cmpt276.asn2.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;
    @GetMapping("/students/show")
    public String getAllUsers(Model model){
        System.out.println("Getting all students");
        List<Student> students = studentRepo.findAll();
        model.addAttribute("stu", students);
        return "students/viewAll";
    }

    @GetMapping("/")
    public RedirectView process(){
        return new RedirectView("login");
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpServletResponse request, HttpSession session){
        Student students = (Student) session.getAttribute("session_student");
        if (students == null){
            return "students/login";
        }
        else {
            model.addAttribute("students", students);
            return "students/protected"; // Accessible to users that are logged in
        }
    }
}
