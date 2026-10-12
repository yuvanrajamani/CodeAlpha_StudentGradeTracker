import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Console-based Student Grade Tracker. */
public class GradeTracker {
    private static final List<Student> students = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== Student Grade Tracker ===");
        boolean running = true;
        while (running) {
            printMenu();
            switch (readInt("Choose an option: ")) {
                case 1 -> addStudent();
                case 2 -> addGradeToStudent();
                case 3 -> showSummaryReport();
                case 4 -> showClassStatistics();
                case 5 -> removeStudent();
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Add student");
        System.out.println("2. Add grade to a student");
        System.out.println("3. Show summary report");
        System.out.println("4. Show class statistics");
        System.out.println("5. Remove student");
        System.out.println("0. Exit");
    }

    private static void addStudent() {
        System.out.print("Student name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        if (findStudent(name) != null) {
            System.out.println("A student with that name already exists.");
            return;
        }
        Student s = new Student(name);
        students.add(s);
        System.out.println("Added " + name + ". Enter grades (0-100), or type -1 to stop.");
        while (true) {
            double g = readDouble("  Grade: ");
            if (g == -1) break;
            if (g < 0 || g > 100) {
                System.out.println("  Grade must be between 0 and 100.");
                continue;
            }
            s.addGrade(g);
        }
    }

    private static void addGradeToStudent() {
        if (students.isEmpty()) {
            System.out.println("No students yet.");
            return;
        }
        System.out.print("Student name: ");
        Student s = findStudent(scanner.nextLine().trim());
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        double g = readDouble("Grade (0-100): ");
        if (g < 0 || g > 100) {
            System.out.println("Grade must be between 0 and 100.");
            return;
        }
        s.addGrade(g);
        System.out.println("Grade added.");
    }

    private static void removeStudent() {
        System.out.print("Student name to remove: ");
        Student s = findStudent(scanner.nextLine().trim());
        if (s == null) {
            System.out.println("Student not found.");
        } else {
            students.remove(s);
            System.out.println("Removed " + s.getName() + ".");
        }
    }

    private static void showSummaryReport() {
        if (students.isEmpty()) {
            System.out.println("No students to report.");
            return;
        }
        System.out.println("\n---------------- SUMMARY REPORT ----------------");
        System.out.printf("%-15s %-8s %-8s %-8s %-8s %-6s%n",
                "Name", "Grades", "Average", "Highest", "Lowest", "Grade");
        System.out.println("------------------------------------------------");
        for (Student s : students) {
            if (s.hasGrades()) {
                System.out.printf("%-15s %-8d %-8.2f %-8.2f %-8.2f %-6s%n",
                        s.getName(), s.getGrades().size(), s.getAverage(),
                        s.getHighest(), s.getLowest(), s.getLetterGrade());
            } else {
                System.out.printf("%-15s %-8d %s%n", s.getName(), 0, "(no grades yet)");
            }
        }
    }

    private static void showClassStatistics() {
        Student best = null, worst = null;
        double total = 0;
        int count = 0;
        double highest = Double.NEGATIVE_INFINITY, lowest = Double.POSITIVE_INFINITY;
        for (Student s : students) {
            if (!s.hasGrades()) continue;
            total += s.getAverage();
            count++;
            highest = Math.max(highest, s.getHighest());
            lowest = Math.min(lowest, s.getLowest());
            if (best == null || s.getAverage() > best.getAverage()) best = s;
            if (worst == null || s.getAverage() < worst.getAverage()) worst = s;
        }
        if (count == 0) {
            System.out.println("No grades recorded yet.");
            return;
        }
        System.out.println("\n---------- CLASS STATISTICS ----------");
        System.out.printf("Class average (of student averages): %.2f%n", total / count);
        System.out.printf("Highest single score: %.2f%n", highest);
        System.out.printf("Lowest single score:  %.2f%n", lowest);
        System.out.printf("Top student:    %s (%.2f)%n", best.getName(), best.getAverage());
        System.out.printf("Lowest student: %s (%.2f)%n", worst.getName(), worst.getAverage());
    }

    private static Student findStudent(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) return s;
        }
        return null;
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}
