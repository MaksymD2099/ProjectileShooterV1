/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectileshooter;

import java.awt.geom.AffineTransform;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {
    
    private double lastFrameTime = 0.0;
    @FXML
    private Label label;
    
    @FXML
    private AnchorPane pane;
    
    private ArrayList<GameObject> objectList = new ArrayList<>();
    
    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    public void removeFromPane(Node node) {
        pane.getChildren().remove(node);
    }
   
    
    public void addFireGun(){
    Gun gunFire = new Gun(new Vector(400,400),190,120, "fire");
      
        addToPane(gunFire.getRectangle());
        gunFire.getRectangle().getTransforms().add(new Rotate(15, 400, 400));
        
      //  objectList.add(gunFire);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();

        AssetManager.preloadAllAssets();
        
        
   
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                
            // Time calculation                
                double currentTime = (now - initialTime) / 1000000000.0;
                double frameDeltaTime = currentTime - lastFrameTime;
                lastFrameTime = currentTime;


                for (GameObject obj : objectList) {
                    if (obj != null) //---------------------------------------
                    {
                        obj.update(frameDeltaTime);
                    }
                }
                             
                
                }
            }.start();
    }
    
}
