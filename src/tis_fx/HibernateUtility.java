/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tis_fx;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



/**
 *
 * @author HP
 */
public class HibernateUtility {
    private final static SessionFactory sf = new Configuration()
	        .configure("hibernate.cfg.xml").buildSessionFactory();
    	private volatile static Session session = sf.openSession();
    
	private HibernateUtility() {
	}
	public static Session getHibernateSession() {
	      if(session == null) {
	    	  session = (Session) new HibernateUtility();
	       }
	       return session;
	}
    
}
