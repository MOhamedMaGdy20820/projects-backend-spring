package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Course;
import com.luv2code.hivernate.demo.entity.Instructor;
import com.luv2code.hivernate.demo.entity.InstructorDetail;


public class CreateInstructorDemo {

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
				// create the objects		 		
		 		Instructor tempInstructor = 
		 				new Instructor("MOH","HAG","mohhag@gmail.com");
		 		
		 		InstructorDetail tempInstructorDetail = 
		 				new InstructorDetail("http://www.mohhag.com/youtube"," Video games");
		 		
		 		
		 		
		 		
		 		
		 		
		 		// associate the objects
		 		tempInstructor.setInstructorDetail(tempInstructorDetail);
		 				 	
		 		// start a transaction 
		 			session.beginTransaction();
		 			
		 
		 		// save the instructor
		 		
		 		// Note : this will ALSO save the details object
		 		// because of CascadeType.ALL
		 			
		 		System.out.println("Saving instructor: "+tempInstructor);
	 			
	 			session.save(tempInstructor);
	 		   // commit transaction

	 			session.getTransaction().commit();
	 			System.out.println("Done !");
		 			
		 		
			}  finally {
				session.close();
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
