/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pong;

/**
 *
 * @author TJ
 */
import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
public class ScreenManager {

    private GraphicsDevice vc;

    //GIVE VC ACCESS TO MONITOR SCREEN
    public ScreenManager(){
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        vc = e.getDefaultScreenDevice();
    }
    //GET ALL COMPATIBLE DISPLAY MODES FROM GFX CARD
    public DisplayMode[] getCompatibleDisplayModes(){
        return vc.getDisplayModes();
    }

    //COMPARES DM IN TO VC AND SEE IF THEY MATCH
    public DisplayMode findFirstCompatibleMode(DisplayMode modes[]){
        DisplayMode goodModes[] = vc.getDisplayModes();
        for(int x=0;x<modes.length;x++){
            for(int y =0; y<goodModes.length; y++){
                if(displayModesMatch(modes[x], goodModes[y])){
                    return modes[x];
                }
            }
        }
        return null;
    }

    //GET CURRENT DM
    public DisplayMode getCurrentDisplayMode(){
        return vc.getDisplayMode();
    }

    //CHECKS IF TWO MODES MATCH EACH OTHER
    public boolean displayModesMatch(DisplayMode m1, DisplayMode m2){
        if(m1.getWidth() != m2.getWidth() || m1.getHeight() != m2.getHeight()){
            return false;
        }
        if(m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()){
            return false;
        }
        if(m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate()!=DisplayMode.REFRESH_RATE_UNKNOWN && m1.getRefreshRate() != m2.getRefreshRate()){
            return false;
        }
        return true;
    }

    //MAKE FRAME SULL SCREEN
    public void setFullScreen(DisplayMode dm){
        JFrame f = new JFrame();
        f.setUndecorated(true);
        f.setIgnoreRepaint(true);
        f.setResizable(false);
        vc.setFullScreenWindow(f);

        if(dm != null && vc.isDisplayChangeSupported()){
            try{
                vc.setDisplayMode(dm);
            }catch(Exception ex){}

        }
        f.createBufferStrategy(2);
    }

    //WE WILL SET GRAPHICS OBJECT = TO THIS
    public Graphics2D getGraphics(){
        Window w = vc.getFullScreenWindow();
        if(w != null){
            BufferStrategy s = w.getBufferStrategy();
            return(Graphics2D)s.getDrawGraphics();

        }else{
            return null;
        }
    }

    //UPDATES DISPLAY
    public void update(){
        Window w = vc.getFullScreenWindow();
        if(w != null){
            BufferStrategy s = w.getBufferStrategy();
            if(!s.contentsLost()){
                s.show();
            }
        }
    }

    //RETURN FULL SCREEN WINDOW
    public Window getFullScreenWindow(){
        return vc.getFullScreenWindow();
    }

    //GET WIDTH OF WINDOW
    public int getWidth(){
        Window w = vc.getFullScreenWindow();
        if(w != null){
            return w.getWidth();
        }else{
            return 0;
        }
    }

        //GET HEIGHT OF WINDOW
    public int getHeight(){
        Window w = vc.getFullScreenWindow();
        if(w != null){
            return w.getHeight();
        }else{
            return 0;
        }
    }

    //GET OUT OF FULL SCREEN
    public void restoreScreen(){
        Window w = vc.getFullScreenWindow();
        if(w != null){
            w.dispose();
        }
        vc.setFullScreenWindow(null);
    }

    //CREATE IMAGE COMPATIBLE WITH MONITOR
    public BufferedImage createCompatibleImage(int w, int h, int t){
        Window win = vc.getFullScreenWindow();
        if(win != null){
            GraphicsConfiguration gc = win.getGraphicsConfiguration();
            return gc.createCompatibleImage(w, h, t);
        }else{
            return null;
        }
    }


}
