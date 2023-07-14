// Import for the JFrame
import javax.swing.JFrame;

public class GameFrame extends JFrame{
    
    public GameFrame()
    {
        // Here we set up our JFrame for the game
        // It is meant to have a panel where we can add things to
        // A title for the window that will pop up
        // The ability to close the window
        // Making it where the user can't resize the window
        // Make sure that our window expands to the fetures we add
        // Make sure the window is visable
        // Make sure the window is set to the center of our screen (0,0)
        GamePanel panel = new GamePanel();
        this.add(panel);
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        // As a note, the coordinate system works by 
        // measuring from (0, 0) at the top left corner of the screen
    }
}
