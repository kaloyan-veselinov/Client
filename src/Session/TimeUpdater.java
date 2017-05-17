package Session;

public class TimeUpdater implements Runnable {
	
	private Session s;
	
	public TimeUpdater(Session s){
		this.s = s;
	}

	@Override
	public void run() {
		while(s.getRunning()){
			s.getCurrentTime().setTime(System.currentTimeMillis());
			s.checkEnd();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
