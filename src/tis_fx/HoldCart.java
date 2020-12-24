/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tis_fx;

import java.util.HashMap;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author d_ros
 */
public final class HoldCart {
    private HashMap<Integer, Integer> cart;
    private static HoldCart INSTANCE = null;
    
    public static HoldCart getInstance(){
        if(INSTANCE == null){
            INSTANCE = new HoldCart();
        }
        return INSTANCE;
    }
    private HoldCart(){
        
    }
    
    public HashMap<Integer, Integer> getCart() {
        return cart;
    }

    public void setCart(HashMap<Integer, Integer> cart) {
        this.cart = cart;
    }

  
}
