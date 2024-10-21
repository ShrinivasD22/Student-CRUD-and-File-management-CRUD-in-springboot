package com.example.student.studentservice.studentserviceimpl;


import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.student.entity.Student;
import com.example.student.exception.ResourceNotFoundException;
import com.example.student.payload.StudentDto;
import com.example.student.repository.StudentRepo;
import com.example.student.studentservice.StudentService;


@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
private StudentRepo studentRepo; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	
	
	


	public StudentDto createStudent(StudentDto studentDto) {
		// TODO Auto-generated method stub
		Student student = this.dtoToStudent(studentDto);
		this.studentRepo.save(student); 
		Student savedUser = this.studentRepo.save(student);
		return this.studentToDto(savedUser);
	}


	public StudentDto updateStudent(StudentDto studentDto, Integer studentId) {
		// TODO Auto-generated method stub
		Student student = this.studentRepo.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student","Id",studentId));
		
		student.setFirstname(studentDto.getFirstname());
		student.setLastname(studentDto.getLastname());
		student.setRollno(studentDto.getRollno());
		student.setClassname(studentDto.getClassname()); 
		
		Student updatedStudent=this.studentRepo.save(student);
		StudentDto studentDto1=this.studentToDto(updatedStudent);
		
		return studentDto1; 
		
	}

	
	public StudentDto getStudentById(Integer studentId) {
		// TODO Auto-generated method stub
		Student student = this.studentRepo.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student","Id",studentId));
		return this.studentToDto(student);
	}


	public List<StudentDto> getAllStudent() {
		// TODO Auto-generated method stub
		List<Student> student = this.studentRepo.findAll();
		List<StudentDto> studentDtos = student.stream().map(user->this.studentToDto(user)).collect(Collectors.toList());
		return studentDtos;
	}

	
	public void deleteStudent(Integer studentId) {
		// TODO Auto-generated method stub
		Student student=this.studentRepo.findById(studentId).orElseThrow(()->new ResourceNotFoundException("Student","Id",studentId));	
		this.studentRepo.delete(student);
		
	}


	


	private Student dtoToStudent(StudentDto studentDto) {
		Student student= this.modelMapper.map(studentDto, Student.class);
		return student;
	}
	
	public StudentDto studentToDto(Student student) {
		StudentDto studentDto = this.modelMapper.map(student, StudentDto.class);
		return studentDto;
	}


	
	
      
}
