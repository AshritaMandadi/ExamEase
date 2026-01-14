package service;

import model.Faculty;
import model.Student;

public class AuthService {

    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_PASSWORD = "admin123";

    public static boolean loginAdmin(String username, String password) {
        return ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password);
    }

    public static boolean loginStudent(String rollNo, String password) {
        for (Student s : DataStore.students) {
            if (s.getRollNo().equals(rollNo) && s.getPassword().equals(password)) return true;
        }
        return false;
    }

    public static boolean loginFaculty(String facultyId, String password) {
        for (Faculty f : DataStore.faculties) {
            if (f.getFacultyId().equals(facultyId) && f.getPassword().equals(password)) return true;
        }
        return false;
    }
}

