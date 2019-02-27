/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectileshooter;

/**
 *
 * @author cstuser
 */
public class Gun extends GameObject{
    public Gun(Vector position,double height, double width, String type)
    {
        super(position, height, width, type);
        
        //checking what knd of gun is being used to assign the correct image
        if(type == "fire"){
            rectangle.setFill(AssetManager.getGunFire());
        }
        
        if(type == "ice"){
            rectangle.setFill(AssetManager.getGunIce());
        }
        
        if(type == "antiGravity"){
            rectangle.setFill(AssetManager.getGunAntiGravity());
        }        
     
        if(type == "portalIn"){
            rectangle.setFill(AssetManager.getGunPortalIn());
        }
        
        if(type == "portalOut"){
            rectangle.setFill(AssetManager.getGunPortalOut());
        }
    }    
}
