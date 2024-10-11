

package powerpay;


import javax.swing.*;
import java.awt.*;


public class Splash extends JFrame{                                                             // JFrame: Class of swing
    private JProgressBar progressBar;                                                           // JProgressBar is a Swing component that shows progress visually
    private int progress;
    private JLabel loadingLabel;                                                                // Added for loading text
    
    
    // Creating Constructor: This should have same name as class name
    Splash(){
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/start.jpg"));           // Selecting image
        Image i2 = i1.getImage().getScaledInstance(1000, 700,Image.SCALE_DEFAULT);               // Fit the image to the frame 
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3); 
        image.setBounds(0, 0, 800, 530);                                                         // positioning and sizing components in Java Swing
        add(image);                                                                              // Pasting componenent Frame
      
        
        // Loading text label
        loadingLabel = new JLabel("Loading...");
        loadingLabel.setBounds(370, 400, 200, 30);
        loadingLabel.setForeground(Color.WHITE);
        loadingLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        image.add(loadingLabel);
        
        
        // Custom progress bar
        progressBar = new JProgressBar() {
            @Override
            protected void paintComponent(Graphics g) {                                                        // Graphics object as a parameter, which is like a canvas you can draw on
                Graphics2D g2d = (Graphics2D) g;                                                               // Graphics2D is a more advanced version of Graphics with additional features like better control over geometry, color management, and text layout.
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);      // enables antialiasing, which smooths out jagged edges in graphics

                // Background (gray part)
                g2d.setColor(new Color(200, 200, 200));
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Progress (black part)
                g2d.setColor(Color.BLACK);
                int width = (int) (getWidth() * (getValue() / 100.0));
                g2d.fillRect(0, 0, width, getHeight());
            }
        };
        
        
        progressBar.setBounds(200, 430, 400, 4);                                                // Make it thinner
        progressBar.setStringPainted(true);                                                    // Remove percentage text
        progressBar.setBorderPainted(false);                                                    // Remove border
        progressBar.setBackground(new Color(200, 200, 200));
        progressBar.setForeground(Color.BLACK);
        image.add(progressBar);
        
        setSize(800, 530);
        setLocation(360, 170);
        setUndecorated(true);                                                                   // Removes window decorations: You won't see header of the frame
        setVisible(true);                                                                       // By default visibility of the frame is hidden
        
        
        // Animation and loading simulation
        try {
            for (progress = 0; progress <= 100; progress += 1) {
                progressBar.setValue(progress);
                Thread.sleep(30);                                                              // Mu;tithreading concept: Adjust speed of loading
            }
            
            // After loading completes
            Thread.sleep(500); // Short pause at the end
            setVisible(false);
            // Add your next frame here, for example:
            //new Login();
            SwingUtilities.invokeLater(() -> {
                new Login();
            });
            dispose();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // Remove default UI styling
    static {
        UIManager.put("ProgressBar.background", new Color(200, 200, 200));
        UIManager.put("ProgressBar.foreground", Color.BLACK);
        UIManager.put("ProgressBar.selectionBackground", new Color(200, 200, 200));
        UIManager.put("ProgressBar.selectionForeground", Color.BLACK);
    }
        
        
    public static void main(String arg[]){
        // Create anonymous object in Splash class
        new Splash();                                                                           // We could also write Splash s = new Splash() : But we write this when we have to use 's' in the code if not then make anonymous object.
        
        
    }
    
}
