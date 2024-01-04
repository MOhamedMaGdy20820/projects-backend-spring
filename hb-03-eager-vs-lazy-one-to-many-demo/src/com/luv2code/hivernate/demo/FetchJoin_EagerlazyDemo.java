package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

import com.luv2code.hivernate.demo.entity.Course;
import com.luv2code.hivernate.demo.entity.Instructor;
import com.luv2code.hivernate.demo.entity.InstructorDetail;



public class FetchJoin_EagerlazyDemo {

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
	
 				// option 2: hivernate query with HQL
	 			// get the instructor from db
	 				
	 				int theId=1;
	 				Query<Instructor> query =
	 						session.createQuery("select i from Instructor i "+
	 											"JOIN FETCH i.courses  "+
	 											"where i.id=:theinstructorId"
	 																			,Instructor.class);
	 				
	 				
	 				
	 				
	 				//------------------------------------------------------------
	 				
	 				 Query<Course> queryCourses = session.createQuery("select c from Course c "
	 						 										+ "where c.instructor.id=:theInstructorIdCourses",    
	 						 																						  Course.class);
	 				 
	 				queryCourses.setParameter("theInstructorIdCourses", 11 );
	 	            
	 	            List<Course> tempCourses = queryCourses.getResultList();
	 	            
	 				
	 				//------------------------------------------------------------
	 				
	 				
	 			// set parameter on query
	 				query.setParameter("theinstructorId", theId);
	 				
	 			// execute query and get instructor
	 				
	 				Instructor temInstructor = query.getSingleResult();
	 				
	 				System.out.println("luv2code: Instructor: "+temInstructor);
	 			
    	
	 			
	 		    // commit transaction
		 			session.getTransaction().commit();
		 			
		 	    // close the session
		 			session.close();
		 			
		 			System.out.println("\n luv2code: the session is new closed\n");
		 			
		 		// get course for the instructor 
	 				System.out.println("Courses: "+ temInstructor.getCourses());
	 	            System.out.println("tempCourses: " + tempCourses);

		 			System.out.println("luv2code: Done !");

			}  finally {
				session.close();
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
