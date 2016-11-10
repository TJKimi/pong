/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;

/**
 *
 * @author TJ
 */
public abstract class Core{
    private static DisplayMode modes[] = {
        new DisplayMode(800,600,32,0),
        new DisplayMode(800,600,24,0),
        new DisplayMode(800,600,16,0),
        new DisplayMode(640,480,32,0),
        new DisplayMode(640,480,24,0),
        new DisplayMode(640,480,16,0),
    };
    private boolean running;
    protected ScreenManager s;

    public void stop(){
        running = false;
    }

    //CALL INIT AND GAMELOOP
    public void run(){
        try{
            init();
            gameLoop();
        }finally{
            s.restoreScreen();
        }
    }
    //SET TO FULLSCREEN
    public void init(){
        s = new ScreenManager();
        DisplayMode dm = s.findFirstCompatibleMode(modes);
        s.setFullScreen(dm);

        Window w = s.getFullScreenWindow();
        w.setFont(new Font("Arial", Font.PLAIN,20));
        w.setBackground(Color.BLACK);
        w.setForeground(Color.WHITE);
        running = true;
    }

    //MAIN GAMELOOP
    public void gameLoop(){
        long startTime = System.currentTimeMillis();
        long cumTime = startTime;

        while(running){
            long timePassed = System.currentTimeMillis() - cumTime;
            cumTime += timePassed;
            update(timePassed);
            Graphics2D g = s.getGraphics();
            draw(g);
            g.dispose();
            s.update();

            try{
                Thread.sleep(20);
            }catch(Exception ex){}

        }
    }

    //UPDATE ANIMATION
    public void update(long timePassed){

    }
    public abstract void draw(Graphics2D g);

}
