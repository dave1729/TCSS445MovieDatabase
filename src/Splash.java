import java.awt.*;
import javax.swing.*; 

public class Splash extends JWindow
{
    private int duration;

    public Splash(int dur)
    {
        duration = dur;
    }

    // A simple little method to show a title screen in the center
    // of the screen for the amount of time given in the constructor
    public void showSplash()
    {

        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.lightGray);

        // Set the window's bounds, centering the window
        int width = 1200;
        int height = 816;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);

        // Build the splash screen
        JLabel label = new JLabel(new ImageIcon("src/splashImg.png"));
        JLabel copyrt = new JLabel
                ("Copyright 2016, Yam DB", JLabel.CENTER);
        copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 12));
        content.add(label, BorderLayout.CENTER);
        content.add(copyrt, BorderLayout.SOUTH);
        Color border = new Color(50, 20, 20,  55);
        content.setBorder(BorderFactory.createLineBorder(border, 10));

        // Display it
        setVisible(true);

        // Wait a little while, maybe while loading resources
        try 
        {
            Thread.sleep(duration);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        setVisible(false);

    }

}