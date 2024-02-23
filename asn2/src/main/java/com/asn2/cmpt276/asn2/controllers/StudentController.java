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
    // TODO: Figure out workflow to show DB data on webpage

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

    @PostMapping("/students/addStudent")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
        System.out.println("ADD student");
        String newName = newStudent.get("name");
        System.out.println("ERROR?");
        double newWeight = Double.parseDouble(newStudent.get("weight"));
        double newHeight = Double.parseDouble(newStudent.get("height"));
        System.out.println("ERROR NOW?"); //Yes
        String newHairColour = newStudent.get("hair_colour");
        double newGpa = Double.parseDouble(newStudent.get("gpa"));
        // int newSize = Integer.parseInt(newStudent.get("size"));
        studentRepo.save(new Student(newName,newWeight, newHeight, newHairColour, newGpa)); // Does the insert command
        response.setStatus(201);
        return "students/addedStudent";
    }

    // Must keep it at PostMapping. If you use DeleteMapping, HTML page will not send method post
    @PostMapping("/students/removeStudent")
    public String removeStudent(@RequestParam Map<String, String> removeStudent, HttpServletResponse response){
        System.out.println("REMOVE student");
        String removeName = removeStudent.get("name");
        List<Student> theStudents = studentRepo.findByName(removeName); // Finds the student by name
        // System.out.println("Show students!");
        // System.out.println(theStudents.get(0).getName());
        //! If there are no matches, then return a 404
        if (!theStudents.isEmpty()){
            System.out.println("Removing student");
            studentRepo.deleteByName(removeName);
            return "students/removedStudent";
        }
        // response.setStatus(201);
        return "students/notFound";
    }

}
