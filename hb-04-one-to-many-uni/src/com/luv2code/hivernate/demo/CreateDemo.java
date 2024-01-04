package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Instructor;
import com.luv2code.hivernate.demo.entity.InstructorDetail;


public class CreateDemo {

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
				// create the objects
//		 		Instructor tempInstructor = 
//		 				new Instructor("MO","MG","MOMG@gmail.com");
//		 		
//		 		InstructorDetail tempInstructorDetail = 
//		 				new InstructorDetail("http://www.MOMG.com/youtube"," MO MG");
		 		
		 		Instructor tempInstructor = 
		 				new Instructor("Mohsen","taha","Mohsentaha@gmail.com");
		 		
		 		InstructorDetail tempInstructorDetail = 
		 				new InstructorDetail("http://www.Mohsentaha.com/youtube"," Mohsen taha");
		 		
		 		
		 		
		 		
		 		
		 		
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
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
