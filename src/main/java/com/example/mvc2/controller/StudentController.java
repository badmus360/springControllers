package com.example.mvc2.controller;

import com.example.mvc2.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1, "Ramesh", "Fadatare"
        );
        //return new ResponseEntity<>(student, HttpStatus.OK);
        return ResponseEntity.ok()
                .header("custom-header", "ramesh")
                .body(student);
    }

    @GetMapping("students")
    public List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ramesh", "Fadatare"));
        students.add(new Student(2, "Umesh", "Fadatare"));
        students.add(new Student(3, "Ram", "Jadhav"));
        return students;
    }

    @GetMapping("students/{id}")
    public Student studentPathVariable(@PathVariable("id") int studentId){
        return new Student (studentId, "Ramesh", "Fadatare");
    }

    @GetMapping("students/{id}/{first-name}/{last-name}")
    public Student studentsPathVariable(@PathVariable("id") int studentId,
                                        @PathVariable("first-name") String firstName,
                                        @PathVariable("last-name") String lastName){
        return new Student(studentId, firstName, lastName);
    }


    //http://localhost:8080/
    @GetMapping("students/query")
    public Student studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        return new Student(id, firstName, lastName);
    }

    @PostMapping("students/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Student createStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
       return student;
    }

    // Spring boot REST API that handles HTTP PUT Request - updating existing resources
    @PutMapping("students/{id}/update")
    public Student updateStudent(@RequestBody Student student, @PathVariable("id") int studentId){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return student;
    }

    // Spring boot REST API that handles HTTP DELETE Request - deleting existing resources
    @DeleteMapping("students/{id}/delete")
    public String deleteStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return "Student deleted Successfully!";
    }
}
