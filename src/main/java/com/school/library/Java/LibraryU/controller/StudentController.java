package com.school.library.Java.LibraryU.controller;

import com.school.library.Java.LibraryU.common.LogConfig;
import com.school.library.Java.LibraryU.model.Students;
import com.school.library.Java.LibraryU.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("libraryu/student")
public class StudentController {

    private static final Logger LOGGER = LogManager.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> saveStudent(@RequestBody Students student){
        LogConfig.initLog4j2();
        LOGGER.info("Registering student in the database");
        ResponseEntity<?> respEnt = null;

        try {
            if (student == null) {
                return respEnt = new ResponseEntity<>("Parameters Error", HttpStatus.BAD_REQUEST);
            }

            studentService.saveStudent(student);
            respEnt = new ResponseEntity<Students>(student, HttpStatus.OK);
        } catch (Exception e) {
            respEnt = new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return respEnt;
    }
}
