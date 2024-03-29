package com.luv2code.hivernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hivernate.demo.entity.Student;

import net.bytebuddy.asm.Advice.AssignReturned.ToThis.Handler.Factory;


public class UpdataStudentDemo {

	public static void main(String[] args) {
		// create session factory
		 	SessionFactory factory = new Configuration()
		 								 .configure("hibernate.cfg.xml")
		 								 .addAnnotatedClass(Student.class)
		 								 .buildSessionFactory();
		// create session
		 	Session session = factory.getCurrentSession();
		 	
		 	try {
		 		int studentId=1;
		 		
		 		
	 			// now get a new session and start transaction
	 			session = factory.getCurrentSession();
	 			session.beginTransaction();
	 			
	 			//retrieve student based on the id :primary key
	 			System.out.println("\nGetting student with id: "+ studentId);	 			
	 			
	 			Student myStudent = session.get(Student.class, studentId);
	 			
	 			System.out.println("Udating student ... ");
	 			
	 			myStudent.setFirstName("Scooby"); // updata in memory
	 			
	 			//commit the transaction
	 			session.getTransaction().commit(); // updata in database
	 			
	 			// NEW CODE
	 			
	 			session = factory.getCurrentSession();
	 			session.beginTransaction();
	 			
	 			// update email for all students
	 			System.out.println("Udate email for all students");
	 			
	 			session.createQuery("update Student set email ='foo@gmail.com'").executeUpdate();
	 			
	 						
	 			//commit the transaction
	 			session.getTransaction().commit(); 
	 			
	 			
	 			System.out.println("Done !");
	 			
		 		
			}  finally {
				factory.close();
			}
		 	
		 	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
