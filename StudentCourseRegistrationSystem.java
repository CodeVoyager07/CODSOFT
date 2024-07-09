import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class CourseInfo {
    String courseCode;
    String courseTitle;
    String courseDescription;
    int courseCapacity;
    int courseSchedule;
    int availableSeats;

    public CourseInfo(String courseCode, String courseTitle, String courseDescription, int courseCapacity, int courseSchedule) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseDescription = courseDescription;
        this.courseCapacity = courseCapacity;
        this.courseSchedule = courseSchedule;
        this.availableSeats = courseCapacity;
    }
}

class StudentProfile {
    String studentID;
    String studentName;
    ArrayList<String> registeredCourses;

    public StudentProfile(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.registeredCourses = new ArrayList<>();
    }
}

public class CourseRegistrationSystem {
    Map<String, CourseInfo> courseCatalog;
    Map<String, StudentProfile> studentRecords;

    public CourseRegistrationSystem() {
        this.courseCatalog = new HashMap<>();
        this.studentRecords = new HashMap<>();
    }

    public void addNewCourse(String courseCode, String courseTitle, String courseDescription, int courseCapacity, int courseSchedule) {
        courseCatalog.put(courseCode, new CourseInfo(courseCode, courseTitle, courseDescription, courseCapacity, courseSchedule));
    }

    public void addNewStudent(String studentID, String studentName) {
        studentRecords.put(studentID, new StudentProfile(studentID, studentName));
    }

    public void displayCourseCatalog() {
        System.out.println("Available Courses:");
        for (CourseInfo course : courseCatalog.values()) {
            System.out.println(course.courseCode + " - " + course.courseTitle + " (" + course.availableSeats + "/" + course.courseCapacity + ")");
        }
    }

    public void registerStudentForCourse(String studentID, String courseCode) {
        StudentProfile student = studentRecords.get(studentID);
        CourseInfo course = courseCatalog.get(courseCode);
        if (course.availableSeats > 0 &&!student.registeredCourses.contains(courseCode)) {
            student.registeredCourses.add(courseCode);
            course.availableSeats--;
            System.out.println("Registration successful!");
        } else {
            System.out.println("Course is full or you are already registered.");
        }
    }

    public void dropCourseForStudent(String studentID, String courseCode) {
        StudentProfile student = studentRecords.get(studentID);
        CourseInfo course = courseCatalog.get(courseCode);
        if (student.registeredCourses.contains(courseCode)) {
            student.registeredCourses.remove(courseCode);
            course.availableSeats++;
            System.out.println("Course dropped successfully!");
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        system.addNewCourse("CS101", "Introduction to Computer Science", "Learn the basics of CS", 30, 2);
        system.addNewCourse("MATH102", "Calculus II", "Learn advanced math concepts", 25, 3);
        system.addNewStudent("S12345", "John Doe");
        system.addNewStudent("S67890", "Jane Smith");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. View Course Catalog");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    system.displayCourseCatalog();
                    break;
                case 2:
                    System.out.print("Enter student ID: ");
                    String studentID = scanner.next();
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.next();
                    system.registerStudentForCourse(studentID, courseCode);
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    studentID = scanner.next();
                    System.out.print("Enter course code: ");
                    courseCode = scanner.next();
                    system.dropCourseForStudent(studentID, courseCode);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}