package Analyse;

public class Vector {
	
	private int timeUp;
	private int timeDown;
	private int pressure;
	private int shiftUp;
	private int shiftDown;
	private int shiftLocation;
	private int ctrlUp;
	private int ctrlDown;
	private int ctrlLocation;
	private int altUp;
	private int altDown;
	private int altLocation;
	private int capslockUp;
	private int capslockDown;
	private double norm; // norme 2
	
	public Vector (int timeUp, int timeDown, int pressure, int shiftUp, int shiftDown, int shiftLocation,
			int ctrlUp, int ctrlDown, int ctrlLocation, int altUp, int altDown, int altLocation, int capslockUp, int capslockDown){
		this.timeUp = timeUp;
		this.timeDown = timeDown;
		this.pressure = pressure;
		this.shiftUp = shiftUp;
		this.shiftDown = shiftDown;
		this.shiftLocation = shiftLocation;
		this.ctrlUp = ctrlUp;
		this.ctrlDown = ctrlDown;
		this.ctrlLocation = ctrlLocation;
		this.altUp = altUp;
		this.altDown = altDown;
		this.altLocation = altLocation;
		this.capslockUp = capslockUp;
		this.capslockDown = capslockDown;
		this.setNorm(Math.sqrt(timeUp*timeUp+timeDown*timeDown+pressure*pressure+shiftUp*shiftDown+shiftLocation*shiftLocation+
				ctrlUp*ctrlUp+ctrlDown*ctrlDown+ctrlLocation*ctrlLocation+altUp*altUp+altDown*altDown+altLocation*altLocation));
		
	}
	
	public double euclidianDistance (Vector v){
		double dTimeUp = Math.pow(v.timeUp-this.timeUp, 2);
		double dTimeDown = Math.pow(v.timeDown-this.timeDown, 2);
		double dPressure = Math.pow(v.pressure-this.pressure, 2);
		double dShiftUp = Math.pow(v.shiftUp-this.shiftUp, 2);
		double dShiftDown = Math.pow(v.shiftDown-this.shiftDown, 2);
		double dShiftLocation = Math.pow(v.shiftLocation-this.shiftLocation, 2);
		double dCtrlUp = Math.pow(v.ctrlUp-this.ctrlUp, 2);
		double dCtrlDown = Math.pow(v.ctrlDown-this.ctrlDown, 2);
		double dCtrlLocation = Math.pow(v.ctrlLocation - this.ctrlLocation, 2);
		double dAltUp = Math.pow(v.altUp-this.altUp, 2);
		double dAltDown = Math.pow(v.altDown-this.altDown, 2);
		double dAltLocation = Math.pow(v.altLocation-this.altLocation, 2);
		double dCapslockUp = Math.pow(v.capslockUp-this.capslockUp, 2);
		double dCapslockDown = Math.pow(v.capslockDown-this.capslockDown, 2);

		return Math.sqrt(dTimeUp+dTimeDown+dPressure+dShiftUp+dShiftDown+dShiftLocation+dCtrlUp+dCtrlDown+dCtrlLocation
				+dAltUp+dAltDown+dAltLocation+dCapslockUp+ dCapslockDown);

	}
	
	public double manhattanDistance(Vector v){
		double dTimeUp = Math.abs(v.timeUp-this.timeUp);
		double dTimeDown = Math.abs(v.timeDown-this.timeDown);
		double dPressure = Math.abs(v.pressure-this.pressure);
		double dShiftUp = Math.abs(v.shiftUp-this.shiftUp);
		double dShiftDown = Math.abs(v.shiftDown-this.shiftDown);
		double dShiftLocation = Math.abs(v.shiftLocation-this.shiftLocation);
		double dCtrlUp = Math.abs(v.ctrlUp-this.ctrlUp);
		double dCtrlDown = Math.abs(v.ctrlDown-this.ctrlDown);
		double dCtrlLocation = Math.abs(v.ctrlLocation - this.ctrlLocation);
		double dAltUp = Math.abs(v.altUp-this.altUp);
		double dAltDown = Math.abs(v.altDown-this.altDown);
		double dAltLocation = Math.abs(v.altLocation-this.altLocation);
		double dCapslockUp = Math.abs(v.capslockUp-this.capslockUp);
		double dCapslockDown = Math.abs(v.capslockDown-this.capslockDown);

		return (dTimeUp+dTimeDown+dPressure+dShiftUp+dShiftDown+dShiftLocation+dCtrlUp+dCtrlDown+dCtrlLocation
				+dAltUp+dAltDown+dAltLocation+dCapslockUp+dCapslockDown);
	}

	public int getTimeUp() {
		return timeUp;
	}

	public void setTimeUp(int timeUp) {
		this.timeUp = timeUp;
	}

	public int getTimeDown() {
		return timeDown;
	}

	public void setTimeDown(int timeDown) {
		this.timeDown = timeDown;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getShiftUp() {
		return shiftUp;
	}

	public void setShiftUp(int shiftUp) {
		this.shiftUp = shiftUp;
	}

	public int getShiftDown() {
		return shiftDown;
	}

	public void setShiftDown(int shiftDown) {
		this.shiftDown = shiftDown;
	}

	public int getShiftLocation() {
		return shiftLocation;
	}

	public void setShiftLocation(int shiftLocation) {
		this.shiftLocation = shiftLocation;
	}

	public int getCtrlUp() {
		return ctrlUp;
	}

	public void setCtrlUp(int ctrlUp) {
		this.ctrlUp = ctrlUp;
	}

	public int getCtrlDown() {
		return ctrlDown;
	}

	public void setCtrlDown(int ctrlDown) {
		this.ctrlDown = ctrlDown;
	}

	public int getCtrlLocation() {
		return ctrlLocation;
	}

	public void setCtrlLocation(int ctrlLocation) {
		this.ctrlLocation = ctrlLocation;
	}

	public int getAltUp() {
		return altUp;
	}

	public void setAltUp(int altUp) {
		this.altUp = altUp;
	}

	public int getAltDown() {
		return altDown;
	}

	public void setAltDown(int altDown) {
		this.altDown = altDown;
	}

	public int getAltLocation() {
		return altLocation;
	}

	public void setAltLocation(int altLocation) {
		this.altLocation = altLocation;
	}

	public double getNorm() {
		return norm;
	}

	public void setNorm(double norm) {
		this.norm = norm;
	}
}
