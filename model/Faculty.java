package model;

public class Faculty {
    private String name;
    private String facultyId;
    private String department;
    private String password;

    public Faculty(String name, String facultyId, String department, String password) {
        this.name = name;
        this.facultyId = facultyId;
        this.department = department;
        this.password = password;
    }

    public String getName() { 
	return name; 
	}
    public String getFacultyId() {
	 return facultyId;
	 }
    public String getDepartment() {
	 return department;
	 }
    public String getPassword() { 
	return password;
	 }
}

