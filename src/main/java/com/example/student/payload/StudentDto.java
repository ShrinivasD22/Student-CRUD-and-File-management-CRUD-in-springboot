package com.example.student.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter 
public class StudentDto {

	private int id;
	private String firstname;
	private String lastname;
	private String rollno;
	private String classname;
	
}
