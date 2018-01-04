package pong2v2pac;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Puck
{
      //Data
      /* ********************  Version 2V */
      
      int puckD;
      int xLoc, yLoc;
      int xSpeed, ySpeed;
      Color outerColor;
      Color innerColor;
      
      int frictionFactor = 1;
      
      int rinkW, rinkH;
      // Image???
      String puckFile = "peterPuck.gif";
      Image pImage;
      PongAirHockey myRink;
  
      // Constructors
      public Puck( int size, PongAirHockey newRink)
      {
          puckD = size;
          myRink = newRink;
          rinkW = myRink.getRinkWidth();
          rinkH = myRink.getRinkHeight();
          
          xSpeed = -40;
          ySpeed =  0;
          xLoc= (int)(rinkW/2) - (int)(puckD/2);
          //yLoc= (int)(rinkH/2) - (int)(puckD/2);  //middle
          yLoc = 0 ; // top text
          //yLoc = rinkH - puckD - 20;
          outerColor = Color.BLACK;
          innerColor = new Color(232, 145, 16);
        
          try{
            File thePuckFile = new File(puckFile);
            pImage = ImageIO.read(thePuckFile);
            
          } catch (IOException e){
            System.out.println("AAHHHHHHHHH -- Terrible Error trying to open puck file");
            System.out.println(e.toString());
            
          }
        
      }
  
  
  //Methods
  
      public void drawPuck(Graphics g){
        
//        g.setColor(outerColor);
//        g.fillOval(xLoc,  yLoc, puckD, puckD);
//        
//        g.setColor(innerColor);
//        int outerT = (int)(puckD * 0.3);
//        g.fillOval(xLoc + (outerT/2), yLoc + (outerT/2), puckD - outerT, puckD - outerT);
        
        g.drawImage(pImage, xLoc, yLoc, puckD, puckD, myRink);
        
      }
  
  
  
      public void movePuck()
      {
        
        xLoc += xSpeed;
        yLoc += ySpeed;
        
        dealWithFriction();
        bounceCheck();

        
        
      }
      
      public void bounceCheck()
      {
        
        //System.out.println("goalYTop is " + myRink.getGoalYTop() );
        //System.out.println("goalYBot is " + myRink.getGoalYBottom() );
        
        if(xSpeed < 0)
        {
          System.out.println("xSpeed is " + xSpeed);
          
          if( yLoc < myRink.getGoalYTop()  ||  yLoc > myRink.getGoalYBottom() - puckD) {
                if (  xLoc <0 + myRink.getGoalWidth()  )
                {   
                      xLoc = 0 + myRink.getGoalWidth();
                      xSpeed *= -1;
                }
          } else  {
                if (  xLoc <0  )
                {   
                      xLoc = 0 ;
                      xSpeed *= -1;
                }
          }
            
        }
        
      }
      
      public void dealWithFriction()
      {
        
        // slow the puck down because of friction.....
        if(xSpeed > 0)
        {
          xSpeed-= frictionFactor;
          if(xSpeed < 0 ) 
            {
              xSpeed = 0;
            }
        } else {
          xSpeed+= frictionFactor;
          if(xSpeed > 0 ) 
            {
              xSpeed = 0;
            }
        }
        
        if(ySpeed > 0)
        {
          ySpeed-= frictionFactor;
          if(ySpeed < 0 ) 
            {
              ySpeed = 0;
            }
        } else {
          ySpeed += frictionFactor;
          if(ySpeed > 0 ) 
            {
              ySpeed = 0;
            }
          
        }
        
        
      }

}
