package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Course;
import com.luv2code.hivernate.demo.entity.Instructor;
import com.luv2code.hivernate.demo.entity.InstructorDetail;
import com.luv2code.hivernate.demo.entity.Review;
import com.luv2code.hivernate.demo.entity.Student;


public class CreateCourseAndStudentsDemo {

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
	 			// save the course
	 				System.out.println("\n Saving the course ...");
	 				session.save(temCourse);
	 				System.out.println("saved th course: "+temCourse);
	 				
	 			// create the students 
	 				Student temStudent1 = new Student("Mohamed","magdy","mohamed@gmail.com");
	 				Student temStudent2 = new Student("Mohsen","Ali","mohsen@gmail.com");
	 			// add student to the course 
	 				
	 				temCourse.addStudent(temStudent1);
	 				temCourse.addStudent(temStudent2);
	 			// save the students
	 				System.out.println("\n Saving the students ...");
	 				session.save(temStudent1);
	 				session.save(temStudent2);
	 				System.out.println("Saved students: "+temCourse.getStudents());

	 				

	 		   // commit transaction
	 			session.getTransaction().commit();
	 			System.out.println("Done !");
		 			
		 		
			}  finally {
				session.close();
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
