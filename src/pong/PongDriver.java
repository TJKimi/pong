package pong;

public class PongDriver extends KeyTest{

	/**
	 * @param args
	 */
	public static void main(String[] args){
        new KeyTest().run();
        Runnable threadJob = new PongAI();
        Thread myThread = new Thread(threadJob);
        myThread.start();
    }

}
