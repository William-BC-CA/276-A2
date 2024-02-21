package com.asn2.cmpt276.asn2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.asn2.cmpt276.asn2.models.Student;
import com.asn2.cmpt276.asn2.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;
// import jakarta.servlet.http.HttpSession;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepo;
    @GetMapping("/students/view")
    public String getAllStudents(Model model){
        System.out.println("Getting all students");
        List<Student> students = studentRepo.findAll();
        model.addAttribute("stu", students);
        return "students/viewAll";
    }

    @GetMapping("/")
    public RedirectView process(){
        return new RedirectView("home.html");
    }

    @GetMapping("/home")
    public RedirectView processHome(){
        return new RedirectView("home.html");
    }

    @GetMapping("/students")
    public RedirectView processStudents(){
        return new RedirectView("students.html");
    }

    @GetMapping("/about")
    public RedirectView processAbout(){
        return new RedirectView("about.html");
    }

    // @GetMapping("/login")
    // public String getLogin(Model model, HttpServletResponse request, HttpSession session){
    //     Student students = (Student) session.getAttribute("session_student");
    //     if (students == null){
    //         return "students/login";
    //     }
    //     else {
    //         model.addAttribute("students", students);
    //         return "students/protected"; // Accessible to students that are logged in
    //     }
    // }

    @PostMapping("/users/addStudent")
    public String addUser(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
        System.out.println("ADD student");
        String newName = newStudent.get("name");
        double newWeight = Double.parseDouble(newStudent.get("weight"));
        double newHeight = Double.parseDouble(newStudent.get("height"));
        String newHairColour = newStudent.get("hair_colour");
        double newGpa = Double.parseDouble(newStudent.get("gpa"));
        // int newSize = Integer.parseInt(newStudent.get("size"));
        studentRepo.save(new Student(newName,newWeight, newHeight, newHairColour, newGpa)); // Does the insert command
        response.setStatus(201);
        return "users/addedStudent";
    }

}
