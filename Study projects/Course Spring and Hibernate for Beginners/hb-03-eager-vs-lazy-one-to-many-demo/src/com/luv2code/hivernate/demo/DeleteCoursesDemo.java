package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Course;
import com.luv2code.hivernate.demo.entity.Instructor;
import com.luv2code.hivernate.demo.entity.InstructorDetail;


public class DeleteCoursesDemo {

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
		 			
	 			// get the Course from db
	 			
	 				int theId = 10;
	 				Course temCourse = session.get(Course.class, theId);
	 				
 				//delete Course from db
	 			
	 				System.out.println("Delete course: "+temCourse);
	 				
	 				session.delete(temCourse);
	 				
	 			
	 			
	 			
	 		   // commit transaction
		 			session.getTransaction().commit();
		 			System.out.println("Done !");
		 			
		 		
			}  finally {
				session.close();
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
