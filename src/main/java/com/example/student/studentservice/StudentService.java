package com.example.student.studentservice;

import java.util.List;

import com.example.student.payload.StudentDto;



public interface StudentService {
    
	
	StudentDto createStudent(StudentDto student); 
	StudentDto updateStudent(StudentDto student,Integer studentId);
	StudentDto getStudentById(Integer studentId);
	List<StudentDto> getAllStudent(); 
	
	void deleteStudent(Integer studentId);
	
}
