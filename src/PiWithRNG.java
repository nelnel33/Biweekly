/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piwithrng;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nelnel33
 */
public class PiWithRNG extends JFrame{    
    public final static int edgeOfSquare = 700;//ONLY CHANGE THIS 
    public final static int numberOfDots = 700;//ONLY CHANGE THIS
    
    //DO NOT CHANGE ANYTHING BELOW    
    public final static int radiusOfCircle = edgeOfSquare/2;
    public final static Circle bigCircle = new Circle(radiusOfCircle,radiusOfCircle,radiusOfCircle);
    
    public static List<Circle> randomPoints = randomPoints = new ArrayList();    
    public PiPanel pane;
    public static int inCircle = 0;
    public static double ratio = -1;
    public static double approxPi = -1;
    
    public PiWithRNG(){
        super("Calculating Pi with RNG");
        
        addRandomPoints(numberOfDots);        
        pane = new PiPanel();
        this.add(pane);
        
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);    
        pack();
        setLocationRelativeTo(null);
    }
    
    public class PiPanel extends JPanel{     
        
        public PiPanel(){
            this.setPreferredSize(new Dimension(edgeOfSquare,edgeOfSquare+200));
        }
        
        @Override
        public void paintComponent(Graphics g){
            Graphics2D gd = (Graphics2D)g;
            
            gd.draw(new Ellipse2D.Double(bigCircle.x-bigCircle.radius,bigCircle.y-bigCircle.radius,bigCircle.radius*2,bigCircle.radius*2));
            gd.draw(new Rectangle2D.Double(0,0,edgeOfSquare-1,edgeOfSquare-1));
            
            for(Circle p: randomPoints){
                int x = p.x;
                int y = p.y;
                int r = p.radius;
                
                gd.fill(new Ellipse2D.Double(x-r,y-r,2*r,2*r));
            }
            
            drawNumbers(gd);
        }
        
        public void drawNumbers(Graphics2D gd){
            String ic = "In Circle: "+inCircle;
            String is = "Not in Circle: "+(randomPoints.size()-inCircle);
            String tot = "Total: "+randomPoints.size();
            String rat = "Ratio(circle/total): "+ratio;
            String ap = "~Pi: "+approxPi;
            String error = "Error: "+calculatePercentError()+"%";
            gd.drawString(ic, 0, edgeOfSquare+12);
            gd.drawString(is, 0, edgeOfSquare+24);
            gd.drawString(tot,0,edgeOfSquare+36);
            gd.drawString(rat, 0, edgeOfSquare+48);
            gd.drawString(ap, 0, edgeOfSquare+60);
            gd.drawString(error,0,edgeOfSquare+72);
        }
    }
    
    public void addRandomPoints(int amountOfPoints){
        for(int i=0;i<amountOfPoints;i++){
            int rx = (int)(Math.random()*edgeOfSquare-1);
            int ry = (int)(Math.random()*edgeOfSquare-1);
            int rr = 3;
            
            Circle curr = new Circle(rx,ry,rr);
            
            randomPoints.add(curr);
            
            if(curr.isTouching(bigCircle)){
                inCircle++;
            }
        }
        
        ratio = (double)inCircle/(double)randomPoints.size();
        approxPi = ratio*4;
    }
    
    public double calculatePercentError(){
        double pi = Math.PI;
        
        return Math.abs(((pi-approxPi)/pi)*100);
    }
    
    public static class Circle{
        public int x;
        public int y;
        public int radius;
        public Circle(int x, int y, int radius){
            this.x = x;
            this.y = y;
            this.radius = radius;
        }
        
        public boolean isTouching(Circle o){
            int dx = Math.abs(o.x-x);
            int dy = Math.abs(o.y-y);            
            int sqdx = dx*dx;
            int sqdy = dy*dy;
            int sqhyp = sqdx+sqdy;
            
            int dr = this.radius+o.radius;
            int sqdr = dr*dr;
            
            return sqhyp<sqdr;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new PiWithRNG();
        System.out.println("inCircle: "+inCircle);
        System.out.println(ratio*4);
    }
    
}
