package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Course;
import com.luv2code.hivernate.demo.entity.Instructor;
import com.luv2code.hivernate.demo.entity.InstructorDetail;
import com.luv2code.hivernate.demo.entity.Review;
import com.luv2code.hivernate.demo.entity.Student;


public class CreateCourseAndReviewsDemo {

	public static void main(String[] args) {
		// create session factory
		 	SessionFactory factory = new Configuration()
		 								 .configure("hibernate.cfg.xml")
		 								 .addAnnotatedClass(Course.class)
		 								 .addAnnotatedClass(Student.class)
		 								 .addAnnotatedClass(Review.class)
		 								 .addAnnotatedClass(Instructor.class)
		 								 .addAnnotatedClass(InstructorDetail.class)
		 								 .buildSessionFactory();
		 	
	
		// create session
		 	Session session = factory.getCurrentSession();
		 	
		 	try {
		 		 		
		 		// start a transaction 
	 				session.beginTransaction();
		 			
	 			// create a course
	 				Course temCourse = new Course("Magdy MOMG");
	 			// add some reviews 
	 				temCourse.addReview(new Review("Greate couse ... loved it "));
	 				temCourse.addReview(new Review("cool couse"));
	 				temCourse.addReview(new Review("bad couse "));

	 			// save the course ... and leverage the cascade all :-)
	 				
	 				System.out.println("Saveing the coutse");
	 				System.out.println(temCourse);
	 				System.out.println(temCourse.getReviews());
	 				
	 				session.save(temCourse);
	 				
	 		   // commit transaction
	 			session.getTransaction().commit();
	 			System.out.println("Done !");
		 			
		 		
			}  finally {
				session.close();
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
