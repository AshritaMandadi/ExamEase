
 ExamEase – Automated Seating Allocation System 

ExamEase is a Java Swing based application designed to automate and simplify the exam seating allocation process. It provides a role-based system for managing students, faculty, rooms, and generating seat allocations in a structured and efficient way.

Why This Project Matters?
Manual seating arrangement during exams is time-consuming and error-prone. ExamEase reduces effort by providing a centralized system where allocation can be generated instantly based on room capacity, while allowing faculty and students to view allocation details securely.

Core Features

1) Role-Based Login System
The system supports three user roles:
Admin:
- Full access to manage students, faculty, and rooms
- Generate seating allocation
Faculty:
- View seating allocation list
- Refresh allocation details
Student:
- Check personal seat allocation details after login

2) Student Management (Admin)
Admin can add students with details such as:
- Name
- Roll Number (used as Username)
- Branch
- Year
Passwords are automatically generated during student creation.

3) Faculty Management (Admin)
Admin can add faculty details such as:
- Faculty Name
- Faculty ID (used as Username)
- Department
Passwords are automatically generated during faculty creation.

4) Room Management (Admin)
Admin can add rooms with:
- Room Number
- Seating Capacity

5) Seating Allocation Generation
Allocation is generated automatically based on:
- Student list order
- Room capacities

The final allocation includes:
- Roll Number
- Student Name
- Room Number
- Seat Number

6) User Interface
The project includes:
- Login screen with role selection
- Dashboard-based interface
- Tabbed admin panel for navigation
- Table views for student, faculty, room, and allocation data

Project Structure
src/
├── app/ (Main entry point)
├── model/ (Student, Faculty, Room, Allocation classes)
├── service/ (Auth, Password generator, Allocation logic, Data store)
└── ui/ (Login screen, Dashboard, Admin/Faculty/Student panels)

Login Details:
Admin Login:
- Username: admin
- Password: admin123

Student Login:
- Username: Roll Number
- Password: Auto-generated while adding student

Faculty Login:
- Username: Faculty ID
- Password: Auto-generated while adding faculty

Requirements
- Java 8 or higher
- Java Swing (built-in)

Compilation Command
javac app/Main.java ui/*.java model/*.java service/*.java

Run Command
java app.Main

      

