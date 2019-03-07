/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectileshooter;

import java.awt.Point;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author cstuser
 */
public class FXMLDocumentController implements Initializable {

    private double lastFrameTime = 0.0;

    @FXML
    private Label coordinates;
    
    @FXML
    private ImageView gunImage;
    
    @FXML
    private ImageView character;

    @FXML
    private AnchorPane pane;



    //Local Variables
    ArrayList<Double> values = new ArrayList<Double>();
    public ArrayList<GameObject> objectList = new ArrayList<>();
    private ArrayList<Projectile> arrayListProjectiles = new ArrayList<>();
    
    private int counterProjectiles;
    private int counterBounces;
    
    //rectangles for detecting collisions with the edges of the game environment
    private Edge edgeRoof;     
    private Edge edgeFloor;
    private Edge edgeLeftWall;
    private Edge edgeRightWall;
    
    
    //private Gun gun;
    private boolean increasing = true;
    
    //Boolean to check if the projectile is within the bounds of an antigravity region    
    private boolean isWithinGravity = false;
    //private boolean decreasing = false;

    public Point mouseAim;
    public Point gunPivot;
    
    double theta;
    
    //Variables for the mouse's position within the game environment    
    private double mouseX;
    private double mouseY;
    
    private Vector velocityProjectile;
    private Projectile projectile;
    
    //Final variables 
    private final double PROJECTILE_RADIUS = 10; // Radius of the projectiles
    private final double PROJECTILE_VELOCITY = 500; //Magnitude of the projectile's velocity
    private final double GRAVITY = 50; //Magnitude of gravity    
    private final int MAX_NUMBER_OF_BOUNCES = 4;
    
    private Vector acceleration;


    public void addToPane(Node node) {
        pane.getChildren().add(node);
    }

    public void removeFromPane(Node node) {
        pane.getChildren().remove(node);
    }

    @FXML    
    public void mouseMoved(MouseEvent event)
    {
        mouseX = event.getSceneX();
        mouseY = (pane.getHeight() - event.getSceneY());     
      // double theta = Math.atan(x/y);
      // double degrees = Math.toDegrees(theta);
 
        mouseAim = new Point((int)mouseX,(int)mouseY);       
        gunRotationAngle(mouseAim, gunPivot);
        
        coordinates.setText("MouseX: " + mouseX + "MouseY: " + mouseY);
        //gunRotation(degrees - 90);
    }
    
    @FXML
    public void mouseClicked(MouseEvent event)
    {        
        if(isWithinGravity)
        {
            acceleration = new Vector(0, -20);
        }
        
        mouseX = event.getSceneX();
        mouseY = (pane.getHeight() - event.getSceneY());
        
        acceleration = new Vector(0, GRAVITY);
        //if(gunImage.getType() == "fire....")
        
        //This is for trying to get the velocity of the projectile to be prpoportional to the mouse's position        
        projectile = new Projectile(new Vector(gunImage.getLayoutX() + 100, gunImage.getLayoutY()), new Vector(Math.cos(theta)*PROJECTILE_VELOCITY, -Math.sin(theta)*PROJECTILE_VELOCITY), acceleration, PROJECTILE_RADIUS, "fire");
        
        if(arrayListProjectiles.isEmpty())
        {
            addToPane(projectile.getCircle()); 
            objectList.add(projectile); 
            arrayListProjectiles.add(projectile);  
        }
        
    }
    
  
    
      public void gunRotationAngle(Point mouseAim, Point gunPivot)
      {                  
        theta = Math.atan2(mouseAim.y - gunPivot.y, mouseAim.x - gunPivot.x);
        double angle = Math.toDegrees(theta);
        System.out.println(angle + "");      
        gunImage.setRotate(-angle);
        
      }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lastFrameTime = 0.0f;
        long initialTime = System.nanoTime();

        AssetManager.preloadAllAssets();

    
       
       //Image of MainCharacter
       character.setImage(AssetManager.getCharacterImage());      
       
       //Image Gun
        gunImage.setImage(AssetManager.getGunFire_Img());
        

        //gunImage.rotationAxisProperty().set(new Point3D(0,0,0));
        
        //gunImage.setRotationAxis(new Point3D(0,0,0));        
        gunPivot = new Point();
        gunPivot.setLocation(100, 100);
        
        edgeFloor = new Edge(new Vector(0, pane.getPrefHeight()), pane.getPrefWidth(), 50);
        edgeRoof = new Edge(new Vector(0, 0), pane.getPrefWidth(), 50);
        edgeLeftWall = new Edge(new Vector(0, 0), 50, pane.getPrefHeight());
        edgeRightWall = new Edge(new Vector(pane.getPrefWidth(), 0), 50, pane.getPrefHeight());
                
        
        
      
        
        //Adding edges to the pane so that collisions can be detected with the edge        
        addToPane(edgeFloor.getRectangle());
        addToPane(edgeRoof.getRectangle());
        addToPane(edgeLeftWall.getRectangle());
        addToPane(edgeRightWall.getRectangle());
       
        
        //Adding edges to the objectList so that their existance within the program can be monitored                
        objectList.add(edgeFloor);
        objectList.add(edgeRoof);
        objectList.add(edgeLeftWall);
        objectList.add(edgeRightWall);
   
      
        new AnimationTimer() {
            @Override
            public void handle(long now)
            {
                try{
            double currentTime = (now - initialTime) / 1000000000.0;
            double frameDeltaTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            
            for (GameObject obj : objectList) {
                if (obj != null) 
                {
                    obj.updateRectangle(frameDeltaTime);
                    obj.updateCircle(frameDeltaTime);
                }
            }                       
        }catch(Exception e){} 
            //Set the bounds of the game objects
            //Create class variables for each game object's bounds            
            for (int i = 0; i < arrayListProjectiles.size(); i++) 
            {          
                //AudioClip tempBounce = AssetManager.getBounce();  //---------------------SOUNDS BROKEN
                Projectile tempProjectile = arrayListProjectiles.get(i);

                Circle projectileCircle = tempProjectile.getCircle();
                Bounds boundProjectileCircle = projectileCircle.getBoundsInParent();

                //Setting bounds for the edges of the game environment               
                //Bounds of floor
                Rectangle rectangleEdgeFloor = edgeFloor.getRectangle();
                Bounds boundRectangleEdgeFloor = rectangleEdgeFloor.getBoundsInParent();
                
                Rectangle rectangleEdgeRoof = edgeRoof.getRectangle();
                Bounds boundRectangleEdgeRoof = rectangleEdgeRoof.getBoundsInParent();
                
                Rectangle rectangleEdgeLeftWall = edgeLeftWall.getRectangle();
                Bounds boundRectangleEdgeLeftWall = rectangleEdgeLeftWall.getBoundsInParent();
                
                Rectangle rectangleEdgeRightWall = edgeRightWall.getRectangle();
                Bounds boundRectangleEdgeRightWall = rectangleEdgeRightWall.getBoundsInParent();

                //If statments that check if the projectiles have collided with the edges of the game environment
                if(boundProjectileCircle.intersects(boundRectangleEdgeFloor) || boundProjectileCircle.intersects(boundRectangleEdgeRoof))
                {
                    ++counterBounces;
                    //put if() statments to check for collision between antigravity and portals with the edges                    
                    tempProjectile.setVelocity(new Vector(tempProjectile.getVelocity().getX(), -tempProjectile.getVelocity().getY()));   
                    
                    if(counterBounces == MAX_NUMBER_OF_BOUNCES)
                    {
                        objectList.remove(arrayListProjectiles.get(0));
                        arrayListProjectiles.remove(0);
                        removeFromPane(tempProjectile.getCircle());
                    }
                    //tempBounce.play(); --------------THE FUCKING SOUND DOESNT WORK
                }            
                else if(boundProjectileCircle.intersects(boundRectangleEdgeLeftWall)|| boundProjectileCircle.intersects(boundRectangleEdgeRightWall))
                {
                    ++counterBounces;
                    //AssetManager.getBounce().play();
                    projectile.setVelocity(new Vector(-projectile.getVelocity().getX(), projectile.getVelocity().getY()));                                                        
                    if(counterBounces == MAX_NUMBER_OF_BOUNCES)
                    {
                        objectList.remove(arrayListProjectiles.get(0));
                        arrayListProjectiles.remove(0);
                        removeFromPane(tempProjectile.getCircle());
                    }
                }                 
                
                projectile = tempProjectile;
            }
        }
    }.start();
}
}



  /*
            
             if (prj_rec_Bounds.intersects(shield_rec_Bounds1) || prj_rec_Bounds.intersects(shield_rec_Bounds2) || prj_rec_Bounds.intersects(shield_rec_Bounds3)) {

                        AssetManager.getEnnemyHitSound().play();

                        pane.getChildren().remove(projectiles_enemy.get(i).getRectangle());
                        projectiles_enemy.remove(i);

                    } else if (prj_rec.getY() + prj_rec.getHeight() > pane.getHeight() || prj_rec.getY() < 0) {
                        pane.getChildren().remove(projectiles_enemy.get(i).getRectangle());
                        projectiles_enemy.remove(i);
                    }
*/
