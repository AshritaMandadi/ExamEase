package service;

import java.util.Random;

public class PasswordUtil {
    private static final Random random = new Random();

    public static String generateStudentPassword() {
        int num = 1000 + random.nextInt(9000);
        return "STD@" + num;
    }

    public static String generateFacultyPassword() {
        int num = 1000 + random.nextInt(9000);
        return "FAC@" + num;
    }
}

