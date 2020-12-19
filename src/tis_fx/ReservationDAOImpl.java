/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tis_fx;

import DAOs.ReservationDAO;
import Models.Event;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import Controllers.SignInController;
import Models.Reservation;
import Models.User;



public class ReservationDAOImpl implements ReservationDAO{

    Session session = HibernateUtility.getHibernateSession();

    public List<Integer> getReservedEvents(String username) {
       List<Integer> reserved_events = null;
       try {
            session.beginTransaction();
            reserved_events = session.createSQLQuery("SELECT event_id FROM reservations  WHERE username = '" + username + "'" ).getResultList();
            System.out.println(username); //Sonra sil
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
             session.getTransaction().commit();
        }
       return reserved_events;
    }

    
    public Reservation insertReservation(Reservation r) {
        try {
            System.out.println(r.getUsername());
            session.beginTransaction();
            session.save(r);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return r;
    }    

    @Override
    public List<Reservation> getReservedEvents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
