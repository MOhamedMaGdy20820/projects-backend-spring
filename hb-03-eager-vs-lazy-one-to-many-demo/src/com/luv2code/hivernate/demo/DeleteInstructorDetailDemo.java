package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Instructor;
import com.luv2code.hivernate.demo.entity.InstructorDetail;


public class DeleteInstructorDetailDemo {

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
		 		
		 		// get the instructor detail object   // »Œ·ÌÂ Ì‘«Ê— ⁄·Ì «·ÃœÊ· «· «‰Ì
		 			int theId = 9;
		 			InstructorDetail temDeInstructorDetail =  
		 					session.get(InstructorDetail.class, theId);
		 		// print the instructor detail
		 			System.out.println("temDeInstructorDetail: "+ temDeInstructorDetail);
		 		
		 		// print the associated instructor
		 		System.out.println("temDeInstructorDetail: "+ temDeInstructorDetail.getInstructor());
		 		
		 		// remove the associated object reference
		 		// break bi-directional link
		 		
		 		temDeInstructorDetail.getInstructor().setInstructorDetail(null); // »Ê’· ··Ì »Ì‘«Ê— ⁄·Ì« Ê «Œ·ÌÂ „Ì‘«Ê—‘ ⁄·Ì«
		 		

		 		
		 		// now let's delete the instructor detail
		 		System.out.println("deleting temDeInstructorDetail: "+temDeInstructorDetail);
		 		session.delete(temDeInstructorDetail);	
		 		
		 	
		 		// commit transaction
	 			session.getTransaction().commit();
	 			System.out.println("Done !");
		 			
		 		
			}catch (Exception e) {
				e.printStackTrace();
			}
		 	finally {
		 		// handle connection leak issue 
		 		session.close();
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
