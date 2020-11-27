/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tis_fx;

import DAOs.EventDAO;
import Models.Event;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author HP
 */
public class EventDAOImpl implements EventDAO{
    
    Session session = HibernateUtility.getHibernateSession();

    @Override
    public List<Event> getAllEvents() {
       List<Event> events = null;
       try {
            session.beginTransaction();
            events = session.createQuery("FROM Event").getResultList();

        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
             session.getTransaction().commit();
        }
       return events;
    }
    
}
