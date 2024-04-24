package com.luv2code.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class testJdbc {

	public static void main(String[] args) {
												// name of schema
	String jdbcUrl ="jdbc:mysql://localhost:3306/hb-05-many-to-many?useSSL=false&serverTimezone=UTC";
	String user = "hbstudent";
	String pass = "hbstudent";
	
	try {
		System.out.println("connectiong to database: "+jdbcUrl);
		Connection myConnection =
				DriverManager.getConnection(jdbcUrl,user,pass);
		System.out.println("connection successful !!!");
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	

	}

}
