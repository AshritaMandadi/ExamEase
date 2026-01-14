package model;

public class Allocation {
    private String rollNo;
    private String studentName;
    private String roomNo;
    private String seatNo;

    public Allocation(String rollNo, String studentName, String roomNo, String seatNo) {
        this.rollNo = rollNo;
        this.studentName = studentName;
        this.roomNo = roomNo;
        this.seatNo = seatNo;
    }

    public String getRollNo() {
	 return rollNo;
	 }
    public String getStudentName() {
	 return studentName;
	 }
    public String getRoomNo() { 
	return roomNo;
	 }
    public String getSeatNo() { 	
	return seatNo;
	 }
}

