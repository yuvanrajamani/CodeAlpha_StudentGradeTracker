# CodeAlpha_StudentGradeTracker

A console-based Java application to manage student grades (CodeAlpha Java Internship, Task 1).

## Features
- Add students and record multiple grades per student (stored in `ArrayList`s)
- Per-student average, highest, lowest score and letter grade
- Class statistics (class average, top/lowest student, overall highest/lowest score)
- Summary report table of all students
- Input validation (grades must be 0-100)

## Project Structure
```
src/
  Student.java        // Student model + grade calculations
  GradeTracker.java   // Console UI / main entry point
```

## How to Run
Requires JDK 17 or later.
```bash
cd src
javac *.java
java GradeTracker
```

## Sample Output
```
---------------- SUMMARY REPORT ----------------
Name            Grades   Average  Highest  Lowest   Grade
------------------------------------------------
Asha            3        88.33    95.00    80.00    B
Ravi            2        72.50    75.00    70.00    C
```

## Concepts Used
OOP (encapsulation), ArrayList, loops, input validation, `printf` formatting.
