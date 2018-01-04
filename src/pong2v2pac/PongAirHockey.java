package pong2v2pac;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PongAirHockey extends JPanel implements ActionListener
{
  
  /* ********************  Version 2V */
  
    //Data
    int pWidth;
    int pHeight;
    Color bColor;
    
    //String imageFileName = "johnwarren.png";
    // Image johnWarrenImage;
    
    //String iFileStringName = "Gala.png";
    String iFileStringName = "SMHockeyLogoblue2.png";
    Image appleImage;
    int cLogoScaleFacH = 60;
    int cLogoScaleFacV = 90;
    
    Puck gamePuck;
    
    int puckDiameter;
    int goalWidth;
    int goalYTop, goalYBottom;
    
   // Paddle leftPaddle, rightPaddle;
    //int paddleDiameter;
    
    Timer t;
    
    
    
    BufferedImage bJohnWarrenImage;
  
  
    //Constructors(0---or many)
    
   public PongAirHockey(int w, int h)
   {
     pWidth = w;
     pHeight = h;
     bColor = new Color(222, 246, 247);
     this.setSize(pWidth, pHeight);
     this.setBackground(bColor);
     puckDiameter = (int)(pWidth * 0.09);
     goalWidth =(int) (pWidth/10);
     goalYTop = (int)( (pHeight/2) - (pHeight/5) );
     goalYBottom = goalYTop + (int)(pHeight/2.5);
     
     try{
       File iAppleFile = new File(iFileStringName);
       appleImage = ImageIO.read(iAppleFile);
       
       
     } catch (IOException e){
       System.out.println("AAHHHHHHHHH -- Terrible Error");
       System.out.println(e.toString());
       
     }
     

         gamePuck = new Puck (puckDiameter, this);
//       paddleDiameter = (int) (puckDiameter * .75);
//     leftPaddle = new Paddle(paddleDiameter, this, Paddle.LEFT_PADDLE);
//     rightPaddle = new Paddle(paddleDiameter, this, Paddle.RIGHT_PADDLE);
     
     t = new Timer(100, this);
     t.start();
 
     
   }
  
  
  // Methods
   //Overriding Method
   @Override
   public void paintComponent(Graphics g)
   {
     

     
     
     g.setColor(bColor);
     g.fillRect( 0, 0, pWidth,  pHeight );
     
     //double alpha = 0.6; 
     //g2.setComposite(AlphaComposite.getInstance(
      //   AlphaComposite.SRC_IN, (float) alpha));
  
    
     g.drawImage(
         appleImage, 
         (int)(pWidth/2-pHeight/4) + (cLogoScaleFacH/2), 
         (int)(pHeight/4) + (cLogoScaleFacV/2),
         (int)(pHeight/2) - cLogoScaleFacH, 
         (int)(pHeight/2) - cLogoScaleFacV,
         this);
         
        /* (int)(pWidth/2-pHeight/4) + scaleFac,  
         (int)(pHeight/4) + scaleFac, 
         (int)(pHeight/2) - scaleFac *2, 
         (int)(pHeight/2) - scaleFac *2, */
        
     
     
//    
//     g2.drawImage(
//         /* johnWarrenImage */  bJohnWarrenImage , 
//         (int)(pWidth/2-pHeight/4) , 
//         (int)(pHeight/4), 
//         (int)(pHeight/2), 
//         (int)(pHeight/2) ,
//         this);
     
   Graphics2D g2 = (Graphics2D) g;
       //draw lines...
      g2.setColor(Color.RED);
       g2.fillRect((int)(pWidth/2)-3, 0, 6, pHeight);
       
     //center circle
   
     g2.setStroke(new BasicStroke(4));
         g2.drawOval(
         (int)(pWidth/2-pHeight/4),  /* x */
         (int)(pHeight/4),  /* y */
         (int)(pHeight/2), 
         (int)(pHeight/2)
         );
     
      // make the left goal...
       //  goalWidth =(int) (pWidth/10);  *** Moved to Constructor
         g2.setColor(Color.WHITE);
         g2.fillRect(
             0,                                                     // xLoc of goal
             (int)( (pHeight/2) - (pHeight/5) ),    //yLoc of goal...
             goalWidth,
             (int)(pHeight/2.5));
         g2.setColor(Color.RED);
         g2.drawRect(
             0, 
             (int)( (pHeight/2) - (pHeight/5) ), 
             goalWidth, 
             (int)(pHeight/2.5)
             );
         
         g2.fillRect( (int)(goalWidth), 0, 4, pHeight );
         
         //make the right side goal
         
         
         g2.setColor(Color.WHITE);
         g2.fillRect(
             pWidth - goalWidth,                         // xLoc of goal
             (int)( (pHeight/2) - (pHeight/5) ),    //yLoc of goal...
             goalWidth,
             (int)(pHeight/2.5));
         g2.setColor(Color.RED);
         g2.drawRect(
             pWidth - goalWidth, 
             (int)( (pHeight/2) - (pHeight/5) ), 
             goalWidth, 
             (int)(pHeight/2.5)
             );
         
         g2.fillRect( pWidth - goalWidth, 0, 4, pHeight );
         
         
         gamePuck.drawPuck(g);
//         rightPaddle.drawPaddle(g);
//         leftPaddle.drawPaddle(g);
         
   }
   
   
   public int getRinkWidth()
   {
     return pWidth;
   }
   
   
   public int getRinkHeight()
   {
     return pHeight;
   }
   
   
   public int getGoalWidth()
   {
     return goalWidth;
   }
   
   
   public int getGoalYTop()
   {
     return goalYTop;
   }
   
   
   public int getGoalYBottom()
   {
     return goalYBottom;
   }
   
   
   
   public void drawJohnWarrenImage(Graphics2D g2)
   {
     
     
   }
   
   
   public static BufferedImage makeImageTranslucent(BufferedImage source,
       double alpha) {
     BufferedImage target = new BufferedImage(source.getWidth(),
         source.getHeight(), java.awt.Transparency.TRANSLUCENT);
     // Get the images graphics
     Graphics2D g = target.createGraphics();
     // Set the Graphics composite to Alpha
     g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
         (float) alpha));
     // Draw the image into the prepared receiver image
     g.drawImage(source, null, 0, 0);
     // let go of all system resources in this Graphics
     g.dispose();
     // Return the image
     return target;
   }


  public void actionPerformed(ActionEvent e)
  {
    // TODO Auto-generated method stub
    
    gamePuck.movePuck();
    repaint();
    
  }

}
