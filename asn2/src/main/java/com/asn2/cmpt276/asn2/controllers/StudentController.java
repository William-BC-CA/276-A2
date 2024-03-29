package com.asn2.cmpt276.asn2.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.asn2.cmpt276.asn2.models.Student;
import com.asn2.cmpt276.asn2.models.StudentRepository;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class StudentController {

    // Fetches all students from the database
    @Autowired
    private StudentRepository studentRepo;
    private String oldName; // Save the found name for later use
    @GetMapping("/students/viewAll")
    public String getAllStudents(Model model){
        System.out.println("Getting all students");
        List<Student> students = studentRepo.findAll(Sort.by(Sort.Direction.ASC, "uid")); // Find all entries from database but sort them by UID from least to greatest
        model.addAttribute("stu", students);
        return "students/viewAll";
    }

    // Redirects the root to the home page
    @GetMapping("/")
    public RedirectView process(){
        return new RedirectView("home.html");
    }

    // Redirects /home to the home page
    @GetMapping("/home")
    public RedirectView processHome(){
        return new RedirectView("home.html");
    }

    // Redirects /students to the students page
    @GetMapping("/students")
    public RedirectView processStudents(){
        return new RedirectView("students.html");
    }

    // Redirects /about to the about page
    @GetMapping("/about")
    public RedirectView processAbout(){
        return new RedirectView("about.html");
    }

    // Adds the student to the database
    @PostMapping("/students/addStudent")
    public String addStudent(@RequestParam Map<String, String> newStudent, HttpServletResponse response){
        System.out.println("ADD student");
        String newName = newStudent.get("name");
        double newWeight = Double.parseDouble(newStudent.get("weight"));
        double newHeight = Double.parseDouble(newStudent.get("height"));
        String newHairColour = newStudent.get("hair_colour");
        double newGpa = Double.parseDouble(newStudent.get("gpa"));
        studentRepo.save(new Student(newName,newWeight, newHeight, newHairColour, newGpa)); // Does the insert command
        response.setStatus(201);
        return "students/addedStudent";
    }

    // Must keep it at PostMapping. If you use DeleteMapping, HTML page will not send method post
    // Simply removes the student from the database by name
    @PostMapping("/students/removeStudent")
    public String removeStudent(@RequestParam Map<String, String> removeStudent, HttpServletResponse response){
        System.out.println("REMOVE student");
        String removeName = removeStudent.get("name");
        List<Student> theStudents = studentRepo.findByName(removeName); // Finds the student by name
        //! If there are no matches, then return not found
        if (!theStudents.isEmpty()){
            System.out.println("Removing student");
            studentRepo.deleteByName(removeName);
            return "students/removedStudent";
        }
        return "students/notFound";
    }

    // Searches for the student by name and fetch attributes, saves to oldName variable for later use
    @PostMapping("/students/searchEditStudent")
    public String searchEditStudent(Model model, @RequestParam String name){
        System.out.println("SEARCH student");
        List<Student> theStudents = studentRepo.findByName(name); // Finds the student by name
        if (!theStudents.isEmpty()){
            model.addAttribute("searchedStudent", theStudents.get(0)); // This really helped me know more about what model is. It saves for Thymeleaf so I can use it later in HTML.
            oldName = theStudents.get(0).getName();
            return "students/studentEdit";
        } else {
            return "students/notFound";
        }
    }

    // Edits the student. Existing attributes are obtained from previous found student by oldName
    @PostMapping("/students/editStudent")
    public String editStudent(@RequestParam Map<String, String> editsStudent, HttpServletResponse response){
        System.out.println("EDIT student");
        String newName = editsStudent.get("newName");
        String newWeight = editsStudent.get("newWeight");
        String newHeight = editsStudent.get("newHeight");
        String newHairColour = editsStudent.get("new_hair_colour");
        String newGpa = editsStudent.get("newGPA");
        List<Student> theStudents = studentRepo.findByName(oldName); // Since we found the user, now we can spit existing attributes when editing the student
        //! If there are no matches, then return not found
        if (!theStudents.isEmpty()){
            System.out.println("Modifying student");
            theStudents.get(0).setName(newName);
            theStudents.get(0).setWeight(Double.parseDouble(newWeight));
            theStudents.get(0).setHeight(Double.parseDouble(newHeight));
            theStudents.get(0).setHair_colour(newHairColour);
            theStudents.get(0).setGpa(Double.parseDouble(newGpa));
            studentRepo.save(theStudents.get(0)); // Done by CoPilot, saves to specific entry of List
            return "students/editedStudent";
        }
        return "students/notFound";
    }
}
