// These are the imports we will need.
// The "*" serves as a way to import everything from that package
import javax.swing.*;
    // import javax.swing.JPanel;

import java.awt.*;
    // import java.awt.Graphics;

import java.awt.event.*;
    // import java.awt.event.ActionEvent;
    // import java.awt.event.ActionListener;
    // import java.awt.event.KeyAdapter;
    // import java.awt.event.KeyEvent;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    

    // Here we will be making the final screen dimensions
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    
    // This will dicate how big the objects in our game are
    static final int UNIT_SIZE = 25;

    // This will determine how much screen real estate we are able to play with (based on the dimensions and the unit_size)
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;

    // This will be the delay our timer has when moving our object (Higher the number makes the game slower)
    static final int DELAY = 75; 


    // These are meant to hold the coordinates for each body part of the snake
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    // intial game values 
    int bodyParts = 6;
    int applesEaten = 0;
    // apple position
    int appleX;
    int appleY;
    // direction of the snake
    char direction = 'R';
    // checker if the game is running
    boolean running = false;
    Timer timer;
    Random random;

    // our constructor for the panel
    public GamePanel()
    {
        // we want the ability to do random this, so we will make an instance of random
        random = new Random();
        // we also want to set the size of our panel to the game size
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        // we will make background a color 
        this.setBackground(Color.gray);
        // This will allow the our panel to be "focused" in terms of taking in key components
        this.setFocusable(true);
        // get keyboard input
        this.addKeyListener(new MyKeyAdapter());
        // Begin the game
        startGame();
    }

    public void startGame()
    {
        // Make the starting apple for the game
        newApple();
        // Tell the computer we are running (so when we hit a wall or the snake, we can turn the varaible to false and end the game that way)
        running = true;
        // This is here to give the panel access to the Timer. As well we state the speed at which it goes at
        timer = new Timer(DELAY, this);
        // Set the timer
        timer.start();
        
    }

    //The EDT (Event Dispatch Thread) with timer calls this implicitly
    public void paintComponent(Graphics g)
    {
        //through overiding, we want to make sure we retain the basic strucutre
        super.paintComponent(g);
        //and then we add this as to add our own spin to the GUI
        draw(g);
    }

    // This will draw out a grid for us, as to vizulize the map and our snakes dimensions better.
    public void draw(Graphics g)
    {
        // this is the running games graphics
        if (running == true)
        { 
            g.setColor(Color.black);
            // vertical lines
            // horizoantal lines
            for (int i = 0 ; i < SCREEN_HEIGHT/UNIT_SIZE ; i++)
            {
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
            }

            // construct the apple
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            // construct the snake
            for (int i = 0 ; i < bodyParts; i++)
            {
                if (i == 0)
                {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else
                {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
        // Current Score Setup
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free", Font.BOLD, 40));
        
        //font metric to line up text to the middle of the screen
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());

        }
        else
        {
            //if the game is over, we give the graphics to the gameOver method
            gameOver(g);
        }
    }

    public void newApple()
    {
        // create the placement of the apple
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE)) * UNIT_SIZE;
    }

    public void move()
    {
        // This is to shift the body parts of the snake to make it move
        for (int i = bodyParts ; i > 0 ; i--)
        {
            // Also as an FYI, the body parts generate all in the same place in the orginal running of this program. However, as the timer ticks, it expands till it is at full length of 6 and then just continues to expand as the snake eats
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        // this is to move the direction of the snake. Through shifiting the head of our snake 
        // This allows us to do different things when our varaible "direction" is different things.
        // Its like a train track and the code is the operator on where the train should ge diverted
        // Break is so it doesn't run forever, it only runs for the timer periods.
        switch(direction)
        {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;   
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkApple()
    {
        // Check the coordinates of the snake's head and apple to see if they become the same
        if((x[0] == appleX) && (y[0] == appleY))
        {
            // if the apple was eaten, then extend the snakes body, increase the appleEaten counter, and then generate a new apple
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() 
    {
        // Check if the head of the snake hits the body/itself
        for (int i = bodyParts ; i > 0 ; i--)
        {
            if ((x[0] == x [i]) && (y[0] == y[i]))
            {
                running = false;
            }
        }

        // Check if the head touches the left border
        if (x[0] < 0)
        {
            running = false;
        }
        // Check if the head touches the right border
        if (x[0] > SCREEN_WIDTH)
        {
            running = false;
        }
        // Check if the head touches the bottom border 
        if (y[0] > SCREEN_HEIGHT)
        {
            running = false;
        }
        // Check if the head touches the top border
        if (y[0] < 0)
        {
            running = false;
        }

        if(!running)
        {
            timer.stop();
        }
    }

    public void gameOver(Graphics g)
    {
        // Current Score Setup
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free", Font.BOLD, 40));
        
        // In a way, we are making our blue print, and then sending it to getFontMetrics to use when actuall creating the text on display

        // font metric to line up text to the middle of the screen
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: " + applesEaten))/2, g.getFont().getSize());

        
        // Game Over Text setupt
        g.setColor(Color.red);
        g.setFont( new Font("Ink Free", Font.BOLD, 75));
        
        // font metric to line up text to the middle of the screen
        FontMetrics metrics2 = getFontMetrics(g.getFont());

        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // This is what is constantly running via the timer, so our game can functionally run
        if(running)
        {
            System.out.println("" + x[0] + ", "+ y[0]);
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
        // This is a manual error message, it is not used in the code but was outpopulated. 
        // throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
    public class MyKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            switch(e.getKeyCode())
            {

                // These are our basic key movements, and the way they are controlled
                case KeyEvent.VK_LEFT:
                    if(direction != 'R')
                    {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L')
                    {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D')
                    {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U')
                    {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}