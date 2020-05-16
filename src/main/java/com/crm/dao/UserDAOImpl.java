package com.crm.dao;

import java.util.List;

import com.crm.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<User> getUsers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<User> theQuery = currentSession.createQuery("FROM User order by username", User.class);

		// return the results		
		return theQuery.getResultList();
	}

	@Override
	public void saveUser(User theUser) {

		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save/update the customer ...
		currentSession.saveOrUpdate(theUser);
		
	}

	@Override
	public User getUser(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		return currentSession.get(User.class, theId);
	}

	@Override
	public void deleteUser(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery = currentSession.createQuery("DELETE FROM User WHERE id=:userId");
		theQuery.setParameter("userId", theId);
		
		theQuery.executeUpdate();		
	}
}











