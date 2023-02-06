package com.Ita.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.Ita.hibernate.demo.entity.User;

public class CreateUser {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.xml")
								.addAnnotatedClass(User.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {						
			// create a new users objects
			System.out.println("Creating new users objects...");
			User u = new User("Matteo","Bonza","teo@info.com","1234","admin");
			User u2 = new User("Paul","Dick","paul@gmail.it","1111","user");
			User u3 = new User("John","Doe","jdoe@hotmail.it","222","admin");
			User u4 = new User("Peter","Red","ptr@webmail.com","3698","user");
			
			// start a transaction
			session.beginTransaction();
			
			// save the objects
			System.out.println("Saving the new users...");
			session.save(u);
			session.save(u2);
			session.save(u3);
			session.save(u4);
						
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}
}