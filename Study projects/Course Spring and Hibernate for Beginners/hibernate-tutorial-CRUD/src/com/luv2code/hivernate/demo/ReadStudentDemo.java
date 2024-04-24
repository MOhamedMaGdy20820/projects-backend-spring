package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Student;


public class ReadStudentDemo {

	public static void main(String[] args) {
		// create session factory
		 	SessionFactory factory = new Configuration()
		 								 .configure("hibernate.cfg.xml")
		 								 .addAnnotatedClass(Student.class)
		 								 .buildSessionFactory();
		// create session
		 	Session session = factory.getCurrentSession();
		 	
		 	try {
				// use the session object to save java object
		 		
		 		//1. create a student object
		 			System.out.println("cteating new student object");
		 			
		 			Student tempStudent = new Student("MO","MG","MOMG@gmail.com");
		 			
		 		//2. start a transaction 
		 			session.beginTransaction();
		 		//3. save the student object
		 			System.out.println("Saving the sutdent...");
		 			session.save(tempStudent);
		 		//4. commit transaction
		 			session.getTransaction().commit();
		 			System.out.println("Done!");
		 		
			}  finally {
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
