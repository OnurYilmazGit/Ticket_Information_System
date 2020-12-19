/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.Event;
import Models.Reservation;
import java.util.List;

/**
 *
 * @author ecemy
 */
public interface ReservationDAO {
    public List<Reservation> getReservedEvents();
    public Reservation insertReservation(Reservation r);
}
