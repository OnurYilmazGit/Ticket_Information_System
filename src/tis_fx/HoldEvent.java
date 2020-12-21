/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tis_fx;

import javafx.scene.input.MouseEvent;

/**
 *
 * @author HP
 */
public final class HoldEvent {
    private MouseEvent event;

   
    private static HoldEvent INSTANCE = null;
    
    public static HoldEvent getInstance(){
        if(INSTANCE == null){
            INSTANCE = new HoldEvent();
        }
        return INSTANCE;
    }
    private HoldEvent(){
        
    }
    
    public MouseEvent getEvent() {
        return event;
    }

    public void setEvent(MouseEvent event) {
        this.event = event;
    }

  
    
}
