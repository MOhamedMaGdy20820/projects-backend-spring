package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Instructor;
import com.luv2code.hivernate.demo.entity.InstructorDetail;


public class DeleteDemo {

	public static void main(String[] args) {
		// create session factory
		 	SessionFactory factory = new Configuration()
		 								 .configure("hibernate.cfg.xml")
		 								 .addAnnotatedClass(Instructor.class)
		 								 .addAnnotatedClass(InstructorDetail.class)
		 								 .buildSessionFactory();
		 	
	
		// create session
		 	Session session = factory.getCurrentSession();
		 	
		 	try {
		 		
		 
		 		// start a transaction 
		 		session.beginTransaction();
		 			
		 		// get instructor by primary key/id
	 			int theId = 3;
	 			Instructor temInstructor = session.get(Instructor.class, theId);
		 		
		 		System.out.println("Found instructor: " + temInstructor);	
	 			
		 		// delete the instructor
		 		if(temInstructor != null) {
		 			System.out.println("Deleting: "+temInstructor);
		 			
		 			// Note: will ALSO delete associated "details" object
		 			// because of CascadeType.ALL
		 			
		 			session.delete(temInstructor);
		 		}
		 		
		 		
		 
		 		
	 		   // commit transaction
	 			session.getTransaction().commit();
	 			System.out.println("Done !");
		 			
		 		
			}  finally {
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
