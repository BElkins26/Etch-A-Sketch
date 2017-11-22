import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Etch_A_Sketch implements MouseListener, MouseMotionListener, ActionListener  // NOTE multiple interfaces
{
 JFrame window;
 Container content;
 int mouseX,mouseY,oldX,oldY,clickX,clickY, whatColor, whatShape, lineThickness;
 JLabel coords;
 boolean hasReleased,wasClicked;
 JButton colorb, shapeb, clearb, linebincrease, linebdecrease; 
 Color currColor;
 Graphics g;
 Graphics2D g2;
 

 public Etch_A_Sketch()
 {
  JFrame window = new JFrame("Classic Etch a Sketch");
  content = window.getContentPane();
  content.setLayout( new FlowLayout() );
  coords = new JLabel();
  coords.setFont(new Font("TimesRoman", Font.ITALIC + Font.BOLD, 32));
  content.add( coords); 
  content.addMouseListener(this);        // "this" is the class that implements that listener
  content.addMouseMotionListener(this);  // "this" is the class that implements that listener
  window.setSize(640,480);
  content.setBackground(Color.WHITE);
  window.setVisible(true);
  colorb=new JButton("Change Color");
  shapeb=new JButton("Shape Change");
  clearb=new JButton("Erase");
  linebincrease=new JButton("Increase Line Weight");
  linebdecrease=new JButton("Decrease Line Weight");
  colorb.addActionListener(this);
  shapeb.addActionListener(this);
  clearb.addActionListener(this);
  linebincrease.addActionListener(this);
  linebdecrease.addActionListener(this);
  window.add(colorb);
  window.add(shapeb);
  window.add(clearb);
  window.add(linebincrease);
  window.add(linebdecrease);
  whatColor=0;
  whatShape=0;
  wasClicked=false;
  lineThickness=0;
  g = content.getGraphics();
  g2 = (Graphics2D)g;

 }
 // ..............................................................
 // IMPLEMENTING MOUSELISTENER REQUIRES YOU TO WRITE (OVER-RIDE) THESE METHODS 

 //when you press & release with NO MOVEMENT while pressed
 public void mouseClicked( MouseEvent me)
 {
  mouseX = me.getX();
  mouseY = me.getY();
  clickX=me.getX();
  clickY=me.getY();
  if(!wasClicked)
    wasClicked=true;
  else
    wasClicked=false;
 }
 
 // when you press 
 public void mousePressed( MouseEvent me)
 {
  mouseX = me.getX();
  mouseY = me.getY();
  System.out.println(wasClicked);
  if(whatShape==1 && wasClicked)
  {
    g.setColor(currColor);
    g.drawRect(clickX,clickY,mouseX-clickX,mouseY-clickY);
  }
  else if(whatShape==2 && wasClicked)
  {
    g.setColor(currColor);
    g.drawOval(clickX,clickY,mouseX-clickX,mouseY-clickY);
  }
 }

 //when you let release after dragging
 public void mouseReleased( MouseEvent me)
 {
  mouseX = me.getX();
  mouseY = me.getY();
  hasReleased=true;
 }

 // the mouse just moved off of the JFrame
 public void mouseExited( MouseEvent me)
 {
  mouseX = me.getX();
  mouseY = me.getY();
 }
 
 // the mouse just moved onto the JFrame
 public void mouseEntered( MouseEvent me)
 {
  mouseX = me.getX();
  mouseY = me.getY();
 }
 // ...............................................................
 // IMPLEMENTING MOUSEMOTIONLISTENER REQUIRES YOU WRITE (OVER-RIDE) THESE METHODS 

 // mouse is moving while pressed
 public void mouseDragged( MouseEvent me)
 {
  if(whatShape==0)
  {
  g.setColor(currColor);
  mouseX = me.getX();
  mouseY = me.getY();
 // use g to draw onto the pane
  if (oldX ==0 )
  {
   oldX=mouseX;
   oldY=mouseY;
   return;
  }
  
  // draw  dot (actually small line segment) between old (x,y) and current (x,y)
    if(hasReleased)
    {
    oldX=mouseX;
    oldY=mouseY;
    }
  g.drawLine( oldX,oldY, mouseX, mouseY );
  oldX = mouseX;
  oldY = mouseY;
  hasReleased=false;
  }
 }
 
 // moved mouse but not pressed
 public void mouseMoved( MouseEvent me)
 {
  mouseX = me.getX();
  mouseY = me.getY();

 }
 public void actionPerformed(ActionEvent me)
 {

   if(me.getSource().equals(colorb))
   {
     if(whatColor==0)
     {
       currColor=Color.RED;
       whatColor++;
     }
     else if(whatColor==1)
     {
       currColor=Color.BLUE;
       whatColor++;
     }
     else if(whatColor == 2)
     {
       currColor=Color.GREEN;
       whatColor++;
     }
     else if(whatColor==3)
     {
       currColor=Color.BLACK;
       whatColor=0;
     }
   }
   else if(me.getSource().equals(shapeb))
     if(whatShape<2)
          whatShape++;
     else
          whatShape=0;
   else if(me.getSource().equals(clearb))
     {
       currColor=Color.WHITE;
       g2.setStroke(new BasicStroke(25));
       whatShape=0;
     }
   else if(me.getSource().equals(linebincrease))
   {
     lineThickness+=3;
     g2.setStroke(new BasicStroke(lineThickness));
   }
   else if(me.getSource().equals(linebdecrease))
   {
     lineThickness-=3;
     if(lineThickness < 0)
       lineThickness=0;
     g2.setStroke(new BasicStroke(lineThickness));
   }
 }

 // ..............................................................

 public static void main( String[] args)
 {
  new Etch_A_Sketch();
 }
}//EOF