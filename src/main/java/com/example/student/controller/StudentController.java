package com.example.student.controller;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.student.payload.ApiResponse;
import com.example.student.payload.StudentDto;
import com.example.student.studentservice.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	//POST-CREATE USER
	@PostMapping("/")
	public ResponseEntity<StudentDto> createUser(@Valid @RequestBody StudentDto studentDto)
	{
		StudentDto createStudentDto= this.studentService.createStudent(studentDto);
		return new ResponseEntity<>(createStudentDto,HttpStatus.CREATED);
	} 
	
	
	
	//PUT-UPDATE USER
	@PutMapping("/{studentId}")
	public ResponseEntity<StudentDto>updateStudent(@Valid @RequestBody  StudentDto studentDto,@PathVariable("studentId") Integer StudentId) {
		StudentDto updatedStudent = this.studentService.updateStudent(studentDto, StudentId);
		return ResponseEntity.ok(updatedStudent);
	} 
	
	// DELETE - DELETE USER
	@DeleteMapping("/{studentId}")
	public ResponseEntity<ApiResponse> deleteStudent(@PathVariable("studentId") Integer studentId)
	{
		this.studentService.deleteStudent(studentId);
		return new ResponseEntity(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
	} 
	
	@GetMapping("/")
	public ResponseEntity<List<StudentDto>> getAllStudent()
	{	
	   return ResponseEntity.ok(this.studentService.getAllStudent());
	} 
	
	@GetMapping("/{studentId}")
	public ResponseEntity<StudentDto> getSingleStudent(@PathVariable Integer studentId){
		return ResponseEntity.ok(this.studentService.getStudentById(studentId));
		
	}
	
	
	
	
}

