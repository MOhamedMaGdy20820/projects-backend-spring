package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Course;
import com.luv2code.hivernate.demo.entity.Instructor;
import com.luv2code.hivernate.demo.entity.InstructorDetail;


public class EagerlazyDemo {

	public static void main(String[] args) {
		// create session factory
		 	SessionFactory factory = new Configuration()
		 								 .configure("hibernate.cfg.xml")
		 								 .addAnnotatedClass(Course.class)
		 								 .addAnnotatedClass(Instructor.class)
		 								 .addAnnotatedClass(InstructorDetail.class)
		 								 .buildSessionFactory();
		 	
	
		// create session
		 	Session session = factory.getCurrentSession();
		 	
		 	try {
		 		 		
		 		// start a transaction 
	 				session.beginTransaction();
		 			
	 			// get the instructor from db
	 				
	 				int theId = 1;
	 				Instructor temInstructor = session.get(Instructor.class, theId);
	 				
	 				System.out.println("luv2code: Instructor: "+temInstructor);
	 			
    			// get course for the instructor 
    				System.out.println("Courses: "+ temInstructor.getCourses());
	 			
	 		    // commit transaction
		 			session.getTransaction().commit();
		 			
		 	    // close the session
		 			session.close();
		 			
		 			System.out.println("\n luv2code: the session is new closed\n");
		 			
		 		// option (1) call getter method while session is open
		 			
		 		// get course for the instructor 
	 				System.out.println("Courses: "+ temInstructor.getCourses());
		 		
	 				
		 			System.out.println("luv2code: Done !");

			}  finally {
				session.close();
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
