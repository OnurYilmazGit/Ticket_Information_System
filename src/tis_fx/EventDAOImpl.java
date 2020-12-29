/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tis_fx;

import DAOs.EventDAO;
import Models.Event;
import java.util.ArrayList;
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
    
      public ArrayList<Event> getAllEvents_() {
       ArrayList<Event> events = null;
       try {
            session.beginTransaction();
            events = (ArrayList<Event>) session.createQuery("FROM Event").getResultList();

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
    
    public List<Event> getReservedEventsInfo(int id) {
       List<Event> events = null;
       try {
            session.beginTransaction();
            //events = session.createSQLQuery("SELECT event_name, event_type, location, start_time, date FROM events WHERE event_id='" + id + "'").getResultList();
            events = session.createQuery("FROM Event E WHERE  E.id='" + id + "'").getResultList();
            System.out.println("Test test:" +events.get(0).getName());
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
