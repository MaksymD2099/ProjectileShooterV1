/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectileshooter;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author cstuser
 */
public class Edge extends GameObject{
    
    public Edge(Vector position, double width, double height)
    {        
        super(position, width, height);
        
        rectangle.setFill(AssetManager.getEdgePattern());
    }
    
}
