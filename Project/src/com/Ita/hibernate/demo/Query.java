package com.Ita.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.Ita.hibernate.demo.entity.User;



public class Query {

	public static void main(String[] args) {
		
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.xml")
								.addAnnotatedClass(User.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {						
			// start a transaction
			session.beginTransaction();
			
			// query users: retrieve all the users 
			List<User> theUsers = session.createQuery("from User").list();
			
			// display the users
			displayUsers(theUsers);
			
			// query user: retrieve an user by username
			theUsers = session.createQuery("from User u where u.username='Matteo'").list();
			
			// display the users
			System.out.println("Users who have username='Matteo'");
			displayUsers(theUsers);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}

	}

	private static void displayUsers(List<User> theUsers) {
		for(User u : theUsers) {
			System.out.println(u);
		}
	}

}
