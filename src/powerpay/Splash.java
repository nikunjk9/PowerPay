

package PowerPay;


import javax.swing.*;
import java.awt.Image;
public class Splash extends JFrame{                                                             // JFrame: Class of swing
    
    // Creating Constructor: This should have same name as class name
    Splash(){
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/start.jpg"));          // Selecting image
        Image scaleImage = i1.getImage().getScaledInstance(280, 280,Image.SCALE_DEFAULT);
        JLabel image = new JLabel(i1);
        add(image);                                                                             // Pasting componenent Frame
        setSize(512, 340);
        setLocation(500, 300);
        setVisible(true);                                                                       // By default visibility of the frame is hidden 
        
    }
    public static void main(String arg[]){
        // Create anonymous object in Splash class
        new Splash();                                                                           // We could also write Splash s = new Splash() : But we write this when we have to use 's' in the code if not then make anonymous object.
        
        
        
    }
    
}
