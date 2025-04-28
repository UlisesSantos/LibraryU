package com.school.library.Java.LibraryU.service;

import com.school.library.Java.LibraryU.model.Students;
import com.school.library.Java.LibraryU.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService{

    @Autowired
    StudentRepository studentRepository;

    /**
     *
     * @return
     */
    public List<Students> getAllStudents(){
        return studentRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Students> getStudentById(Long id){
        return studentRepository.findById(id);
    }

    /**
     *
     * @param name
     * @return
     */
    public List<Students> getStudentsByName(String name){
        return studentRepository.findStudentsByName(name);
    }

    /**
     *
     * @param lastname
     * @return
     */
    public List<Students> getStudentsByLastname(String lastname){
        return studentRepository.findStudentsByLastname(lastname);
    }

    /**
     *
     * @param controlId
     * @return
     */
    public Optional<Students> getStudentByControlId(String controlId){
        return studentRepository.findStudentByControlId(controlId);
    }

    /**
     *
     * @param student
     */
    public void saveStudent(Students student){
        studentRepository.save(student);
    }

    /**
     *
     * @param id
     * @param controlId
     */
    public void updateStudentControlId(Long id, String controlId){
        Optional<Students> newStudent = studentRepository.findById(id);

        if(newStudent.isPresent()){
            Students student = newStudent.get();
            student.setControlId(controlId);
            studentRepository.save(student);
        }
    }

    /**
     *
     * @param id
     * @param name
     */
    public void updateStudentName(Long id, String name){
        Optional<Students> newStudent = studentRepository.findById(id);

        if(newStudent.isPresent()){
            Students student = newStudent.get();
            student.setName(name);
            studentRepository.save(student);
        }
    }

    /**
     *
     * @param id
     * @param lastname
     */
    public void updateStudentLastname(Long id, String lastname){
        Optional<Students> newStudent = studentRepository.findById(id);

        if(newStudent.isPresent()){
            Students student = newStudent.get();
            student.setLastname(lastname);
            studentRepository.save(student);
        }
    }

    /**
     *
     * @param id
     */
    public void deleteStudentById(Long id){
        studentRepository.deleteById(id);
    }

    /**
     *
     * @param controlId
     */
    public void deleteStudentByControlId(String controlId){
        Optional<Students> newStudent = studentRepository.findStudentByControlId(controlId);

        if(newStudent.isPresent()){
            Students student = newStudent.get();
            studentRepository.deleteById(student.getStudentId());
        }
    }
}
