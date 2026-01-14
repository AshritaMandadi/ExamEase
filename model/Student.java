package model;

public class Student {
    private String name;
    private String rollNo;
    private String branch;
    private String year;
    private String password;

    public Student(String name, String rollNo, String branch, String year, String password) {
        this.name = name;
        this.rollNo = rollNo;
        this.branch = branch;
        this.year = year;
        this.password = password;
    }

    public String getName() {
        return name;
         }
    public String getRollNo() {
         return rollNo;
         }
    public String getBranch() {
         return branch;
         }
    public String getYear() {
         return year;
         }
    public String getPassword() {
        return password;
         }
}


