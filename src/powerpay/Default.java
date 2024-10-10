
package powerpay;

import java.awt.Image;
import javax.swing.*;

public class Default extends JFrame{

    

    Default(){
       
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icon/Login1.jpg"));           // Selecting image
        Image i5 = i4.getImage().getScaledInstance(850, 550,Image.SCALE_DEFAULT);               // Fit the image to the frame 
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image = new JLabel(i6); 
        image.setBounds(0, 0, 850, 550);                                                         // positioning and sizing components in Java Swing
        add(image); 
      
        setSize(850,550);
        setLocation(360, 170);
        setVisible(true);
        setUndecorated(true);
       
    }
    
    public static void main(String arg[]){
        new Default();

    }

    

}

