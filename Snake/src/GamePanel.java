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


    // These are mean to hold the coordinates for each body part of the snake
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    // intial game values 
    int bodyParts = 6;
    int applesEaten = 0;
    // apple position
    int applex;
    int appley;
    // direction of the snake
    char direction = 'R';
    // cheacker if the game is running
    boolean running = false;
    Timer timer;
    Random random;

    // our constructor for the panel
    public GamePanel()
    {
        // we want the ability to do random this, so we will make an instance of random
        random = new Random();
        // we also want to set the size of our panel to the game size
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT););
        // we will make background a color 
        this.setbackground(color.white);
        // This will allow the our panel to be "focused" in terms of taking in key components
        this.setFocusable(True);
        // get keyboard input
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame()
    {
        newApple();
        running true;
        // This is here to give the panel access to the Timer.
        timer = new Timer(DELAY, this;)
        timer.start();
    }

    public void paintComponenet(Graphics g)
    {
        super.paintComponent(g)
    }

    // This will draw out a grid for us, as to vizulize the map and our snakes dimensions better.
    public void draw(Graphics g)
    {
        // vertical lines
        for (int i = 0 ; i < SCREEN_HEIGHT/UNIT_SIZE ; i++)
        {
            g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
        }

        // horizoantal lines
        for (int i = 0 ; i < SCREEN_WIDTH/UNIT_SIZE ; i++)
        {
            g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
        }

    }

    public void newApple()
    {

    }

    public void move()
    {

    }

    public void checkApple()
    {

    }

    public void checkCollisions() 
    {

    }

    public void gameOver(Graphics g)
    {

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
    
    public class myKeyAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            
        }
    }
}