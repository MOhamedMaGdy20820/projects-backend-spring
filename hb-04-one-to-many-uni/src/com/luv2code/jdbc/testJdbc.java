package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class testJdbc {

	public static void main(String[] args) {
												// name of schema
	String jdvcUrl ="jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
	String user = "hbstudent";
	String pass = "hbstudent";
	
	try {
		System.out.println("connectiong to database: "+jdvcUrl);
		Connection myConnection =
				DriverManager.getConnection(jdvcUrl,user,pass);
		System.out.println("connection successful !!!");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	

	}

}
