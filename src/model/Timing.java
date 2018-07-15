package model;

public class Timing {
	double startTime,endTime;

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public double getEndTime() {
		return endTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}
	public double returnTime()
	{
		return endTime-startTime;
	}
}
