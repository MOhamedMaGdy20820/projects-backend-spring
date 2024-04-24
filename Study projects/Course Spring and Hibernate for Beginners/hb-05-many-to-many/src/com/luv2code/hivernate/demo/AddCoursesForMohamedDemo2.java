package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Course;
import com.luv2code.hivernate.demo.entity.Instructor;
import com.luv2code.hivernate.demo.entity.InstructorDetail;
import com.luv2code.hivernate.demo.entity.Review;
import com.luv2code.hivernate.demo.entity.Student;


public class AddCoursesForMohamedDemo2 {

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

	 			// get the student mohsen from database
	 				int theId = 2;
	 			    Student temStudent = session.get(Student.class, theId);
	 			    
	 			    System.out.println("\nLoaded student: "+temStudent);
	 			    System.out.println("courses: " + temStudent.getCourses());
	 			
	 			// create more courses
	 			    
	 			    Course temCourse1 = session.get(Course.class, 12);

	 			// add student to courses
	 			    
	 			    System.out.println("\n"+ temCourse1+"\n");
	 			    
	 			   temStudent.addCourse(temCourse1);
	 			    
	 			// save the courses
	 			    System.out.println("\nSaving the courses");
	 			    session.save(temStudent);
	 			
	 		   // commit transaction
	 			session.getTransaction().commit();
	 			System.out.println("Done !");
		 			
		 		
			}  finally {
				session.close();
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
