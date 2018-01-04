package pong2v2pac;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Paddle
{
     public static final int LEFT_PADDLE = 1;
     public static final int RIGHT_PADDLE = -1;
  
      private int paddleD;
      private int xLoc, yLoc;
      private int xSpeed, ySpeed;
      
      private String paddleFile = "AirPaddle.png";
      private Image pImage;
      private PongAirHockey myRink;
      
      private int whichPaddle;
      
      
      public Paddle( int size, PongAirHockey newRink, int which)
      {
          paddleD = size;
          myRink = newRink;
          whichPaddle = which;
          
          xSpeed =  0;
          ySpeed =  0;
          
          yLoc = (int)(myRink.getHeight()/2) - (int)(paddleD/2);
          
          if( whichPaddle == Paddle.LEFT_PADDLE)
          {
            xLoc= (int)(myRink.getWidth()/3) - (paddleD) ;
          } else {
            xLoc = (int)(myRink.getWidth() *2/3);
          }
          
        
          try{
            File thePuckFile = new File(paddleFile);
            pImage = ImageIO.read(thePuckFile);
            
          } catch (IOException e){
            System.out.println("AAHHHHHHHHH -- Terrible Error trying to open paddle file");
            System.out.println(e.toString());
            
          }
        
      }
      
      
      public void drawPaddle(Graphics g){
       
      
        g.drawImage(pImage, xLoc, yLoc, paddleD, paddleD, myRink);
      
    }

}
