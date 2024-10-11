package powerpay;


import java.awt.*;
import javax.swing.*;
import java.io.InputStream;


public class Login extends JFrame {
    // Declare font at class level
    private Font spaceGroteskBold;
    private Font spaceGroteskRegular;
    
    // Method to load Space Grotesk fonts
    private void loadFonts() {
        try {
            // Load bold font from the font file
            InputStream isBold = getClass().getResourceAsStream("/fonts/SpaceGrotesk-Bold.otf");
            spaceGroteskBold = Font.createFont(Font.TRUETYPE_FONT, isBold);
            
            // Load regular font from the font file
            InputStream isRegular = getClass().getResourceAsStream("/fonts/SpaceGrotesk-Regular.otf");
            spaceGroteskRegular = Font.createFont(Font.TRUETYPE_FONT, isRegular);
            
            // Register fonts with the GraphicsEnvironment
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(spaceGroteskBold);
            ge.registerFont(spaceGroteskRegular);
            
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback fonts if Space Grotesk fails to load
            spaceGroteskBold = new Font("Arial", Font.BOLD, 12);
            spaceGroteskRegular = new Font("Arial", Font.PLAIN, 12);
        }
    }
    
    Login() {
        // Load font first
        loadFonts();
        
        setLayout(null);
        
        int frameWidth = 850;
        int frameHeight = 550;
        
        // Adjust the split: 40% orange, 60% white
        int splitPoint = (int)(frameWidth * 0.43);                                     // To change space of orange on the frame
        
        // Add orange background panel for the left side
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(255, 203, 164));
        leftPanel.setBounds(0, 0, splitPoint, frameHeight);
        leftPanel.setLayout(null);
        add(leftPanel);
        
        // Add PowerPay text with Space Grotesk
        JLabel titleLabel = new JLabel("PowerPay");
        titleLabel.setBounds(32, 55, 300, 50);
        titleLabel.setFont(spaceGroteskBold.deriveFont(Font.BOLD, 40f));  // Bold weight for title
        titleLabel.setForeground(new Color(0, 0, 0));
        leftPanel.add(titleLabel);
        
        // Add tagline text with Space Grotesk
        JLabel taglineLabel = new JLabel("<html>Streamlining Your Energy, Simplifying Your Payments!</html>");
        taglineLabel.setBounds(32, 105, 300, 50);
        taglineLabel.setFont(spaceGroteskRegular.deriveFont(14f));  // Regular weight for tagline
        taglineLabel.setForeground(new Color(0, 0, 0));
        leftPanel.add(taglineLabel);
        
        // Add white curved panel for the right side
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(-0, 0, getWidth() + 50, getHeight(), 100, 100);
                
                
            }
        };
        rightPanel.setBackground(new Color(255, 203, 164));
        rightPanel.setBounds(splitPoint - 0, 0, frameWidth - splitPoint + 50, frameHeight);
        rightPanel.setLayout(null);
        add(rightPanel);
        
        // Add language selection with Space Grotesk
        JLabel languageLabel = new JLabel("English (UK)");
        languageLabel.setBounds(360, 20, 100, 30);
        languageLabel.setFont(spaceGroteskRegular.deriveFont(14f));
        languageLabel.setForeground(new Color(0, 0, 0));
        rightPanel.add(languageLabel);
        
         // Add dropdown arrow icon
        JLabel arrowLabel = new JLabel("▼");
        arrowLabel.setBounds(rightPanel.getWidth() - 90, 20, 20, 30);
        arrowLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
        arrowLabel.setForeground(new Color(0, 0, 0));
        rightPanel.add(arrowLabel);
        
        // Frame settings
        setSize(850, 550);
        setLocation(360, 170);
        setUndecorated(true);
        setVisible(true);
    }
    
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new Login();
    }
}