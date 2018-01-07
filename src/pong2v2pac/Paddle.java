package pong2v2pac;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Paddle
{
// Class variable -- fields
 public static final boolean RIGHT_PADDLE = true;
 public static final boolean LEFT_PADDLE = false;

 private Image pImage;
  
 private int xLoc, yLoc;
 private int xSpeed, ySpeed;
 private  String paddleFileName = "AirPaddle.png";
 private  boolean mySide;
 private  PongAirHockey myTable;
 private int pDiameter;
 
 public Paddle ( int d, boolean whichPaddle, PongAirHockey t)
 {
   
       pDiameter = d;
       mySide = whichPaddle;
       myTable = t;
       xSpeed = 0;
       ySpeed = 0;
   
   //This puts paddle in middle of table in either side...
       yLoc = (int)(myTable.getHeight()/2) - (int)(pDiameter/2);
   
   //This locates x for the different paddles
         if(mySide == this.RIGHT_PADDLE)
         {
           xLoc = (int) (myTable.getWidth() * 0.75) - (int)(pDiameter/2);
         } else {
           xLoc = (int) (myTable.getWidth() * 0.25) -  (int)(pDiameter/2);
         }
         
   //This is to open up paddle image
       try{
         
         File paddlePicFile = new File(paddleFileName);
         pImage = ImageIO.read(paddlePicFile);
         
       } catch ( IOException e) {
         System.out.println("ERROR  --- CAN'T OPEN PADDLE FILE");
       }
 }
 
 
 public void drawPaddle(Graphics g)
 {
   
     g.setColor(Color.GRAY);
     g.fillOval(xLoc+10, yLoc+10, pDiameter, pDiameter);
     g.drawImage(pImage, xLoc, yLoc, pDiameter, pDiameter, myTable);
 }
 
 
 public void movePaddle()
 {
        yLoc += ySpeed;
        xLoc += xSpeed;
  
 }
 

 public void setYSpeed( int newSpeed)
 {
         ySpeed = newSpeed;
 }
 
 public int getYSpeed()
 {
         return ySpeed;
 }
  
  
  
}
