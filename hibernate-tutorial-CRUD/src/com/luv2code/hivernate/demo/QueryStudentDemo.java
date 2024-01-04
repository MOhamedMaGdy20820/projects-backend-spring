package com.luv2code.hivernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Student;



public class QueryStudentDemo {

	public static void main(String[] args) {
		// create session factory
		 	SessionFactory factory = new Configuration()
		 								 .configure("hibernate.cfg.xml")
		 								 .addAnnotatedClass(Student.class)
		 								 .buildSessionFactory();
		 	
		// create session
		 	Session session = factory.getCurrentSession();
		 	
		 	try {
			
		 			
		 		// start a transaction 
		 			session.beginTransaction();
		 			
		 		// query students

		 	        List<Student> theStudents = session.createQuery("from Student").list();
		 		// display the students	
		 			displayStudents(theStudents);
		 			
		 			theStudents = session.createQuery("from Student s where s.lastName ='MG'").list();
		 			System.out.println("\n\nStudents who have last name of MG");
			 		// display Students who have last name = MG
		 			displayStudents(theStudents);
		 			
		 			theStudents = session.createQuery("from Student s where s.lastName ='MG' OR s.firstName='DA'").list();
		 			System.out.println("\n\nStudents who have last name = MG or first name = DA ");
			 		// display Students who have last name of MG
		 			displayStudents(theStudents);
		 			
		 			
		 			//query students where email like '%gmail.com'
		 			theStudents = session.createQuery("from Student s where s.email LIKE '%N@gmail.com'").list();
		 			System.out.println("\n\nStudents who have email LIKE '%N@gmail.com' ");
			 		// display Students who have last name of MG
		 			displayStudents(theStudents);
		 			
	 			
		 			
		 			
		 			
		 			
		 			
		 		// commit transaction
		 			session.getTransaction().commit();

		 			
		 			System.out.println("Done !");
		 			
		 		
			}  finally {
				factory.close();
			}
		 	
		 	
	}

	private static void displayStudents(List<Student> theStudents) {
		for( Student tempStudent : theStudents ){
			System.out.println(tempStudent);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
