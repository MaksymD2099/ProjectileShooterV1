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
import java.io.File;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.ImagePattern;

/**
 *
 * @author Family Desktop
 */
public class AssetManager {

    //-------START SPRITES----------
    //Guns
    static private ImagePattern gunFire;
    static private ImagePattern gunIce;
    static private ImagePattern gunAntiGravity;
    static private ImagePattern gunPortalIn;
    static private ImagePattern gunPortalOut;
    
    static private Image gunFire_Img;

    //Barriers
    static private ImagePattern barrierIce2;
    static private ImagePattern barrierIce1;
    static private ImagePattern barrierFire;

    //Projectiles
    static private ImagePattern ballIce;
    static private ImagePattern ballFire;
    static private ImagePattern ballPortalIn;
    static private ImagePattern ballPortalOut;
    static private ImagePattern ballAntiGravity;


    //Portals
    static private ImagePattern portalIn;
    static private ImagePattern portalOut;

    //Player
    static private Image characterImage;
    

    //Enemy
    static private ImagePattern enemyPattern;
    
    //miscellaneous
    static private ImagePattern edge;

    //--------END SPRITES--------

    //-------START SOUNDS--------
    //--------Music

    static private Media backgroundMusic = null;

    //--------Sound Effects

    //Shot sound effects
    static private AudioClip shotFire = null;
    static private AudioClip shotIce = null;
    static private AudioClip shotPortal = null;
    static private AudioClip shotAntiGravity = null;

    //Enemy collision sound effects
    static private AudioClip hitFire = null;
    static private AudioClip hitIce = null;
    static private AudioClip hitPortal_AntiGravity = null;

    //Enviornment collision sound effects
    static private AudioClip bounce = null;
    static private AudioClip iceHitsFireBarrier = null;
    static private AudioClip iceHitsIceBarrier = null;
    static private AudioClip fireHitsIceBarrier = null;
    static private AudioClip activateAntiGravity = null;
    static private AudioClip activatePortal = null;
    static private AudioClip transportPortal = null;


    //Barrier Destruction sound effects
    static private AudioClip destroyIceBarrier = null;
    static private AudioClip destroyFireBarrier = null;

    //-------END SOUNDS-------


    //Background
    static private Background backgroundImage = null;
    static private Background backgroundImage_Victory = null;

    //TO BE REMOVED
  /*  static private ImagePattern projectileLaser;
    static private ImagePattern projectileLaser_Enemy;
    static private ImagePattern MainSpaceShip;
    static private ImagePattern shield;
    static private ImagePattern explosion;
    static private ImagePattern heart; */


    static private String fileURL(String relativePath)
    {
        return new File(relativePath).toURI().toString();
    }

    static public void preloadAllAssets()
    {
        Image background = new Image(fileURL("./assets/images/background.gif"));

        backgroundImage = new Background(
                                    new BackgroundImage(background,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundPosition.DEFAULT,
                                    new BackgroundSize(1, 1, true, true, true, true)));

        /*Image background_victory = new Image(fileURL("./assets/images/giphy.gif"));

        backgroundImage_Victory = new Background(
                                    new BackgroundImage(background_victory,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundRepeat.NO_REPEAT,
                                    BackgroundPosition.DEFAULT,
                                    new BackgroundSize(1, 1, true, true, true, true)));*/


        //Preload all gun assets
        gunFire = new ImagePattern(new Image(fileURL("./assets/Guns/gunFire.gif")));
        gunIce = new ImagePattern(new Image(fileURL("./assets/Guns/gunIce.gif")));
        gunPortalIn = new ImagePattern(new Image(fileURL("./assets/Guns/gunInPortal.gif")));
        gunPortalOut = new ImagePattern(new Image(fileURL("./assets/Guns/gunOutPortal.gif")));
        gunAntiGravity = new ImagePattern(new Image(fileURL("./assets/Guns/gunAntiGravity.gif")));
        
        gunFire_Img = new Image(fileURL("./assets/Guns/gunFire.gif"));

        //Preload all barrier assets
        barrierFire = new ImagePattern(new Image(fileURL("./assets/Barriers/barrierFire.gif")));
        barrierIce2 = new ImagePattern(new Image(fileURL("./assets/Barriers/barrierIce2.gif")));
        barrierIce1 = new ImagePattern(new Image(fileURL("./assets/Barriers/barrierIce1.gif")));        

        //Preload all porjectile assets
        ballFire = new ImagePattern(new Image(fileURL("./assets/Projectiles/ballFire.gif")));
        ballIce = new ImagePattern(new Image(fileURL("./assets/Projectiles/ballIce.gif")));
        ballPortalOut = new ImagePattern(new Image(fileURL("./assets/Projectiles/ballPortalIn.gif")));
        ballPortalIn = new ImagePattern(new Image(fileURL("./assets/Projectiles/ballPortalOut.gif")));
        ballAntiGravity = new ImagePattern(new Image(fileURL("./assets/Projectiles/ballAntiGravity.gif")));

        //Preload all portal assets
        portalIn = new ImagePattern(new Image(fileURL("./assets/Portals/portalIn.gif")));
        portalOut = new ImagePattern(new Image(fileURL("./assets/Portals/portalOut.gif")));

        //Preload character asset
        characterImage = new Image(fileURL("./assets/Characters/character.gif"));

        //Preload Enemy asset
        enemyPattern = new ImagePattern(new Image(fileURL("./assets/Enemies/enemy.gif")));

        //Preload music
        backgroundMusic = new Media(fileURL("./assets/music/background_music.mp3"));
        
        //preload miscellaneous assets
        edge = new ImagePattern(new Image(fileURL("./assets/Misc/edgeTest.png")));
        
    }

    //---GETTERS---

    //Getters for all gun assets
    static public ImagePattern getGunFire(){return gunFire;}
    static public ImagePattern getGunIce(){return gunIce;}
    static public ImagePattern getGunPortalIn(){return gunPortalIn;}
    static public ImagePattern getGunPortalOut(){return gunPortalOut;}
    static public ImagePattern getGunAntiGravity(){return gunAntiGravity;}
    
    static public Image getGunFire_Img(){return gunFire_Img;}

    //Getters for all barrier assets
    static public ImagePattern getBarrierFire(){return barrierFire;}
    static public ImagePattern getBarrierIce1(){return barrierIce2;}
    static public ImagePattern getBarrierIce2(){return barrierIce1;}

    //Getters for all projectile assets
    static public ImagePattern getBallFire(){return ballFire;}
    static public ImagePattern getBallIce(){return ballIce;}
    static public ImagePattern getBallPortalOut(){return ballPortalOut;}
    static public ImagePattern getBallPortalIn(){return ballPortalIn;}
    static public ImagePattern getBallAntiGravity(){return ballAntiGravity;}

    //Getters for portal assets
    static public ImagePattern getPortalIn(){return portalIn;}
    static public ImagePattern getPortalOut(){return portalOut;}

    //Getter for Character asset
    static public Image getCharacterImage(){return characterImage;}

    //Getter for Enemy asset
    static public ImagePattern getEnemyPattern(){return enemyPattern;}
    
    //Getter for miscellaneous
    static public ImagePattern getEdgePattern() {return edge;}


    static public Media getBackgroundMusic(){return backgroundMusic;}
    
    static public AudioClip getBounce() {return bounce;}

}
