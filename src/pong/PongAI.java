package pong;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PongAI implements Runnable, KeyListener{
	static int x=50;
    static int y=50;
    static int x1=740;
    static int y1=50;
	public void run(){
		startAI();
	}
	public void startAI(){
		
	}
	public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_W){
            if(y1>50){
                y1 -= 15;
            }
        }else if(keyCode == KeyEvent.VK_S){
            if(y1<410){
                y1 += 15;
            }
        }
        else{
            e.consume();
        }
       }
	public void keyReleased(KeyEvent e) {
		e.consume();
	}
	public void keyTyped(KeyEvent e) {
		e.consume();
		
	}
	public void draw(Graphics2D g) {

		
	}
	
}
