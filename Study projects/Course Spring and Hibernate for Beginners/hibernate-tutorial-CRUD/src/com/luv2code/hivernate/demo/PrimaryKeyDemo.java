package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Student;

public class PrimaryKeyDemo {

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
	 		
	 		//1. create 3 student objects
	 			System.out.println("cteating 3 student objects");
	 			
	 			Student tempStudent1 = new Student("DA","KA","DAKA@gmail.com");
	 			Student tempStudent2 = new Student("ALI","MN","ALIMN@gmail.com");
	 			Student tempStudent3 = new Student("MS","TRK","MSTRK@gmail.com");

	 			
	 		//2. start a transaction 
	 			session.beginTransaction();
	 		//3. save the student object
	 			System.out.println("Saving the sutdents...");
	 			session.save(tempStudent1);
	 			session.save(tempStudent2);
	 			session.save(tempStudent3);

	 		//4. commit transaction
	 			session.getTransaction().commit();
	 			System.out.println("Done!");
	 		
		}  finally {
			factory.close();
		}
	 	
	}

}
