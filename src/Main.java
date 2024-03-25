import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static class GradeBook {
        private Map<Integer, Student> students;

        public GradeBook() {
            this.students = new HashMap<>();
        }

        public void addStudent(String name, int id) {
            students.put(id, new Student(name, id));
        }

        public void addGrade(int studentId, int grade) {
            if (students.containsKey(studentId)) {
                students.get(studentId).addGrade(grade);
            } else {
                System.out.println("Student with ID " + studentId + " does not exist.");
            }
        }

        public void displayGrades(int studentId) {
            if (students.containsKey(studentId)) {
                System.out.println("Grades for Student ID: " + studentId);
                students.get(studentId).displayGrades();
            } else {
                System.out.println("No grades found for Student ID: " + studentId);
            }
        }

        static class Student {
            private String name;
            private int id;
            private ArrayList<Integer> grades;

            public Student(String name, int id) {
                this.name = name;
                this.id = id;
                this.grades = new ArrayList<>();
            }

            public void addGrade(int grade) {
                grades.add(grade);
            }

            public void displayGrades() {
                for (int grade : grades) {
                    System.out.println("Grade: " + grade);
                }
            }
        }
    }

    public static void main(String[] args) {
        GradeBook gradeBook = new GradeBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter 'add' to add a new student or 'check' to check grades:");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "add":
                    System.out.println("Enter the name of the new student:");
                    String name = scanner.nextLine();
                    System.out.println("Enter the ID of the new student:");
                    int id;
                    try {
                        id = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. ID should be an integer.");
                        break;
                    }

                    gradeBook.addStudent(name, id);
                    System.out.println("New student added: " + name + ", ID: " + id);

                    while (true) {
                        System.out.println("Enter grade for the student (or enter 'end' to finish adding grades):");
                        String gradeInput = scanner.nextLine().trim().toLowerCase();
                        if (gradeInput.equals("end")) {
                            break;
                        }
                        int grade;
                        try {
                            grade = Integer.parseInt(gradeInput);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Grade should be a number.");
                            continue;
                        }
                        gradeBook.addGrade(id, grade);
                    }
                    break;

                case "check":
                    System.out.println("Enter student ID to check grades:");
                    int studentId;
                    try {
                        studentId = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. ID should be an integer.");
                        break;
                    }
                    gradeBook.displayGrades(studentId);
                    break;

                default:
                    System.out.println("Invalid input. Please enter 'add' or 'check'.");
            }

            System.out.println("Do you want to continue? (yes/no):");
            String continueChoice = scanner.nextLine().trim().toLowerCase();
            if (!continueChoice.equals("yes")) {
                break;
            }
        }

        scanner.close();
    }
}
