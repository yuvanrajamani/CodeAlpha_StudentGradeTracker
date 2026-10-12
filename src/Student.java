import java.util.ArrayList;
import java.util.List;
public class Student {
    private final String name;
    private final List<Double> grades = new ArrayList<>();

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Double> getGrades() {
        return grades;
    }

    public void addGrade(double grade) {
        grades.add(grade);
    }

    public boolean hasGrades() {
        return !grades.isEmpty();
    }

    public double getAverage() {
        if (grades.isEmpty()) return 0;
        double sum = 0;
        for (double g : grades) sum += g;
        return sum / grades.size();
    }

    public double getHighest() {
        double max = Double.NEGATIVE_INFINITY;
        for (double g : grades) max = Math.max(max, g);
        return grades.isEmpty() ? 0 : max;
    }

    public double getLowest() {
        double min = Double.POSITIVE_INFINITY;
        for (double g : grades) min = Math.min(min, g);
        return grades.isEmpty() ? 0 : min;
    }
    public String getLetterGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A";
        if (avg >= 80) return "B";
        if (avg >= 70) return "C";
        if (avg >= 60) return "D";
        return "F";
    }
}
