/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.Event;
import java.util.List;

/**
 *
 * @author HP
 */
public interface EventDAO {
    public List<Event> getAllEvents();
    public List<Event> getReservedEventsInfo(int id);
    
}
