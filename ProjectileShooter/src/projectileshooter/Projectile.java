/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectileshooter;

import javafx.scene.shape.Circle;

/**
 *
 * @author cstuser
 */
public class Projectile extends GameObject {    
    
    public Projectile(Vector position, Vector velocity, Vector acceleration, double radius, String type)
    {
        super(position, velocity, acceleration, radius, type);
        
        //Might be wrong
        //circle.setRadius(radius);
        
        //Checking what kind of projectile it is to assign the correct image        
        if(type == "fire") {
            circle.setFill(AssetManager.getBallFire());
        }
        
        if(type == "ice") {
            circle.setFill(AssetManager.getBallIce());
        }
        
        if(type == "antigravity") {
            circle.setFill(AssetManager.getBallAntiGravity());            
        }
        
        if(type == "portalIn"){
            circle.setFill(AssetManager.getBallPortalIn());
        }
        
        if(type == "portalOut"){
            circle.setFill(AssetManager.getBallPortalOut());
        }
    }
}
