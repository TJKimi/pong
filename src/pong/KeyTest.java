/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author TJ
 */
public class KeyTest extends Core implements KeyListener {
    static int x=50;
    static int y=50;
    static int x1=740;
    static int y1=50;

    private String mess = "";
    private String coord = "";
    //INITIALIZE
    public void init(){
        super.init();
        Window w = s.getFullScreenWindow();
        w.setFocusTraversalKeysEnabled(false);
        w.addKeyListener(this);
        mess = "press escape to exit";
    }
    //KEY PRESSED
    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_ESCAPE){
            stop();
        }else if(keyCode == KeyEvent.VK_DOWN){
            if(y<410){
                y += 15;
                
            }
        }else if(keyCode == KeyEvent.VK_UP){
            if(y>50){
                y-=15;
                
            }
        }
        else{
            mess = "Pressed : "+ KeyEvent.getKeyText(keyCode);
            e.consume();
        }
    }
    //KEY RELEASED
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        mess = "Released : "+ KeyEvent.getKeyText(keyCode);
        coord = "X-Coor: "+ x+ " Y-Coor: "+y;
        e.consume();
    }
    //LAST METHOD FROM INTERFACE
    public void keyTyped(KeyEvent e){
        e.consume();
    }

    //DRAW
    public synchronized void draw(Graphics2D g){
        Window w = s.getFullScreenWindow();
        g.setColor(w.getBackground());
        g.fillRect(0, 0, s.getWidth(), s.getHeight());
        g.setColor(w.getForeground());
        g.drawString(mess, 30,30);
        g.drawString(coord,500, 30);
        g.setColor(Color.LIGHT_GRAY);
        g.fill3DRect(x, y, 35, 140, true);
        g.fill3DRect(x1, y1, 35, 140, true);
        
    }
}