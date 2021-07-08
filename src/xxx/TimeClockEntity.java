package xxx;

import java.time.LocalDateTime;

/**
 * ���u���d���
 * @author Rhys
 */

public class TimeClockEntity {

	private long id; //���ID
	private LocalDateTime timeIn; //���d�W�Z�ɶ�
	private LocalDateTime timeOut; //���d�U�Z�ɶ�
	private String employeeName; //���u�m�W
	private double hourilyRate; //���~(����)
	
	public TimeClockEntity(long id, String timeIn, String timeOut, String employeeName, double hourilyRate) {
		super();
		this.id = id;
		this.timeIn = LocalDateTime.parse(timeIn);
		this.timeOut = LocalDateTime.parse(timeOut);
		this.employeeName = employeeName;
		this.hourilyRate = hourilyRate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getTimeIn() {
		return timeIn;
	}

	public void setTimeIn(LocalDateTime timeIn) {
		this.timeIn = timeIn;
	}

	public LocalDateTime getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(LocalDateTime timeOut) {
		this.timeOut = timeOut;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public double getHourilyRate() {
		return hourilyRate;
	}

	public void setHourilyRate(double hourilyRate) {
		this.hourilyRate = hourilyRate;
	}
	
	
}
