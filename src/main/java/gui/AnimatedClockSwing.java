package gui;

import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

public class AnimatedClockSwing extends JFrame{

    public static void main(String[] args) {
        ///Get us into a non-static context
        new AnimatedClockSwing();
    }

    public AnimatedClockSwing()  {
        
        Clock clock = new Clock();
        ClockPanel clockPanel = new ClockPanel(clock);
        
        ///Set up a timer to allow for constant repaints
        ///From: http://stackoverflow.com/questions/4044726/how-to-set-a-timer-in-java
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){

            @Override
            public void run() {
                clock.update();
                clockPanel.repaint();
            }
            
        }, 0, 1000);
        
        //Display the window
        this.setVisible(true);      
        
        //Shut down the program when the windown closes
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Give the frame a name
        this.setTitle("Animated Clock");

        //Add the panel to the frame
        this.add(clockPanel);

        //Don't let the user shrink the window smaller than the clock
        this.setMinimumSize(clockPanel.getMinimumSize());

        //Put everything together
        this.pack();
    }
}