/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Models.User;
import java.util.List;

/**
 *
 * @author HP
 */
public interface UserDAO {
    public User insertUser(User u);
	
    public List<User> getAllUsers();

    public void updateUser(int id,String name);
	
    public void removeUser(int id);
	
    public User findUser(int id);
    
    public List<Byte> checkIfStudent(String name);
}
