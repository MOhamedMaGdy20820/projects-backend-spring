package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Student;


public class CreateStudentDemo {

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
		 			System.out.println("creating new student object");
		 			
		 			Student tempStudent = new Student("daffy","Duck","DUCK@gmail.com");
		 			
		 			
		 		//2. start a transaction 
		 			session.beginTransaction();
		 			
		 			
		 		//3. save the student object
		 			System.out.println("Saving the sutdent...");
		 			System.err.println(tempStudent);
		 			session.save(tempStudent);
		 			
		 			
		 		//4. commit transaction
		 			session.getTransaction().commit();

		 			
		 		// code for retrieving an object
		 			// find out the student id :primary key
		 			System.out.println("Saved strdent. Generated id: "+tempStudent.getId());
		 			
		 			// now get a new session and start transaction
		 			session = factory.getCurrentSession();
		 			session.beginTransaction();
		 			
		 			//retrieve student based on the id :primary key
		 			System.out.println("\nGetting student with id: "+tempStudent.getId());
		 			
		 			
		 			
		 			Student myStudent = session.get(Student.class,tempStudent.getId() );
		 			
		 			System.out.println("Get complete: " +myStudent );
		 			//commit the transaction
		 			session.getTransaction().commit();
		 			
		 			System.out.println("Done !");
		 			
		 		
			}  finally {
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
