
public class AlgThread extends Thread {

    public void run() {
	try {
	    MainFrame.doAlg();
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
