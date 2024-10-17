package powerpay;


import java.awt.*;
import javax.swing.*;
import java.io.InputStream;
import java.awt.geom.AffineTransform;


public class Login extends JFrame {
    // Declare font at class level
    private Font spaceGroteskBold;
    private Font spaceGroteskRegular;
    private Font telegrafBold;
    private Font telegrafRegular;
    
    // Declare image field
    private Image characterImage;                                                               // Added a new field to store the loaded image.
    
    // Method to load Space Grotesk fonts
    private void loadFonts() {
        try {
            InputStream isBold = getClass().getResourceAsStream("/fonts/SpaceGrotesk-Bold.otf");
            spaceGroteskBold = Font.createFont(Font.TRUETYPE_FONT, isBold);
            
            InputStream isRegular = getClass().getResourceAsStream("/fonts/SpaceGrotesk-Regular.otf");
            spaceGroteskRegular = Font.createFont(Font.TRUETYPE_FONT, isRegular);
            
            // Register Space Grotesk fonts
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(spaceGroteskBold);
            ge.registerFont(spaceGroteskRegular);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading Space Grotesk fonts. Falling back to default.");
            spaceGroteskBold = new Font("Arial", Font.BOLD, 12);
            spaceGroteskRegular = new Font("Arial", Font.PLAIN, 12);
        }

        try {
            // Load Telegraf fonts
            InputStream isTelegrafBold = getClass().getResourceAsStream("/fonts/PPTelegraf-UltraBold.otf");
            telegrafBold = Font.createFont(Font.TRUETYPE_FONT, isTelegrafBold);
            
            InputStream isTelegrafRegular = getClass().getResourceAsStream("/fonts/PPTelegraf-Regular.otf");
            telegrafRegular = Font.createFont(Font.TRUETYPE_FONT, isTelegrafRegular);
            
            // Register Telegraf fonts
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(telegrafBold);
            ge.registerFont(telegrafRegular);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading Telegraf fonts. Falling back to default.");
            telegrafBold = new Font("Arial", Font.BOLD, 12);
            telegrafRegular = new Font("Arial", Font.PLAIN, 12);
        }
    }
    
    // Method to load the character image
    private void loadImage() {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/icon/Customer.png"));
            characterImage = icon.getImage();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading image: " + e.getMessage());
        }
    }
    
    private void addIconToField(JTextField field, String iconPath) {
        try {
            ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
            Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            icon = new ImageIcon(img);
            field.setLayout(new BorderLayout());
            field.add(new JLabel(icon), BorderLayout.WEST);
            field.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 10));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading icon: " + e.getMessage());
        }
    }
    
    Login() {
        // Load font first
        loadFonts();
        loadImage();
        
        setLayout(null);
        
        int frameWidth = 850;
        int frameHeight = 550;
        
        // Adjust the split: 40% orange, 60% white
        int splitPoint = (int)(frameWidth * 0.43);                                     // To change space of orange on the frame
        
        // Add orange background panel for the left side
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(0xFDC18F));
        leftPanel.setBounds(0, 0, splitPoint, frameHeight);
        leftPanel.setLayout(null);
        add(leftPanel);
        
        // Add PowerPay text with Space Grotesk
        JLabel titleLabel = new JLabel("PowerPay");
        titleLabel.setBounds(32, 55, 300, 50);
        titleLabel.setFont(spaceGroteskBold.deriveFont(Font.BOLD, 43f));  // Bold weight for title
        titleLabel.setForeground(new Color(0, 0, 0));
        leftPanel.add(titleLabel);
        
        // Add tagline text with Space Grotesk
        JLabel taglineLabel = new JLabel("<html>Streamlining Your Energy, Simplifying Your Payments!</html>");
        taglineLabel.setBounds(32, 105, 300, 50);
        taglineLabel.setFont(spaceGroteskRegular.deriveFont(14.3f));  // Regular weight for tagline
        taglineLabel.setForeground(new Color(0, 0, 0));
        leftPanel.add(taglineLabel);
        
        
        
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (characterImage != null) {
                    Graphics2D g2d = (Graphics2D) g.create();
                    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                    
                    // Create an AffineTransform for positioning and rotation
                    AffineTransform at = new AffineTransform();
                    
                    // First, translate to the desired position
                    at.translate(-92, 182);
                    
                    // Then, apply a slight rotation (adjust the angle as needed)
                    double rotationAngle = Math.toRadians(-7); // 5 degrees rotation, adjust as needed
                    at.rotate(rotationAngle, 640 / 2.0, 473 / 2.0); // Rotate around the center of the image
                    
                    g2d.setTransform(at);
                    g2d.drawImage(characterImage, 0, 0, 644, 478, this);
                    g2d.dispose();
                }
            }
        };
        imagePanel.setBounds(0, 0, splitPoint, frameHeight);
        imagePanel.setOpaque(false);
        leftPanel.add(imagePanel);
        
        
        // Add white curved panel for the right side
        JPanel rightPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0xFFFAFA)); // New shade of white
                g2d.fillRoundRect(-0, 0, getWidth() + 50, getHeight(), 100, 100);
                
                
            }
        };
        rightPanel.setBackground(new Color(0xFDC18F));
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
        
        // New login components
        JLabel loginAsLabel = new JLabel("Login as");
        loginAsLabel.setBounds(70, 80, 200, 30);
        loginAsLabel.setFont(telegrafBold.deriveFont(16f));
        rightPanel.add(loginAsLabel);

        String[] loginOptions = {"Admin", "Customer"};
        JComboBox<String> loginAsComboBox = new JComboBox<>(loginOptions);
        loginAsComboBox.setBounds(70, 115, 360, 40);
        loginAsComboBox.setFont(telegrafRegular.deriveFont(14f));
        rightPanel.add(loginAsComboBox);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(70, 170, 200, 30);
        usernameLabel.setFont(telegrafBold.deriveFont(16f));
        rightPanel.add(usernameLabel);

        JTextField usernameField = new JTextField("Enter your username") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0xFFFFFF));
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g2);
                // Add space for icon (you'll need to add the actual icon)
                g2.setColor(new Color(0xFFFAFA));
                g2.fillRect(5, 5, 30, getHeight() - 10);
                g2.dispose();
            }
        };
        usernameField.setBounds(70, 205, 360, 40);
        usernameField.setFont(telegrafRegular.deriveFont(14f));
        addIconToField(usernameField, "/icons/user.png"); 
        usernameField.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 10));
        rightPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(70, 260, 200, 30);
        passwordLabel.setFont(telegrafBold.deriveFont(16f));
        rightPanel.add(passwordLabel);

        JPasswordField passwordField = new JPasswordField("Enter your Password") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0xFFFFFF));
                g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);
                super.paintComponent(g2);
                // Add space for icon (you'll need to add the actual icon)
                g2.setColor(new Color(0xFFFAFA));
                g2.fillRect(5, 5, 30, getHeight() - 10);
                g2.dispose();
            }
        };
        passwordField.setBounds(70, 295, 360, 40);
        passwordField.setFont(telegrafRegular.deriveFont(14f));
        passwordField.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 10));
        rightPanel.add(passwordField);

        JCheckBox rememberMeCheckBox = new JCheckBox("Remember me");
        rememberMeCheckBox.setBounds(70, 345, 150, 30);
        rememberMeCheckBox.setFont(telegrafRegular.deriveFont(14f));
        rightPanel.add(rememberMeCheckBox);

        JLabel forgotPasswordLabel = new JLabel("Forgot password?");
        forgotPasswordLabel.setBounds(300, 345, 150, 30);
        forgotPasswordLabel.setFont(telegrafRegular.deriveFont(14f));
        forgotPasswordLabel.setForeground(new Color(0xFF7500));
        rightPanel.add(forgotPasswordLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(70, 390, 360, 40);
        loginButton.setFont(telegrafBold.deriveFont(16f));
        loginButton.setBackground(new Color(0xFF7500));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorderPainted(false);
        rightPanel.add(loginButton);

        JLabel noAccountLabel = new JLabel("Don't have an account?");
        noAccountLabel.setBounds(120, 440, 200, 30);
        noAccountLabel.setFont(telegrafRegular.deriveFont(14f));
        rightPanel.add(noAccountLabel);

        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setBounds(300, 440, 100, 30);
        signUpLabel.setFont(telegrafRegular.deriveFont(14f));
        signUpLabel.setForeground(new Color(0xFF7500));
        rightPanel.add(signUpLabel);
        
        
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