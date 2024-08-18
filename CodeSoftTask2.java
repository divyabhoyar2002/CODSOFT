/*                              CODSOFT TASK 2 ***STUDENT GRADE CALCULATOR***
___________________________________________________________________________________________________________
-Input: Take marks obtained (out of 100) in each subject.
-Calculate Total Marks: Sum up the marks obtained in all subjects.
-Calculate Average Percentage: Divide the total marks by the total number of subjects to get the
 average percentage.
-Grade Calculation: Assign grades based on the average percentage achieved.
-Display Results: Show the total marks, average percentage, and the corresponding grade to the user.*/

import java.util.Scanner;
public class CodeSoftTask2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        int[] marks = new int[numberOfSubjects];
        int totalMarks = 0;

        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("\n Enter marks for subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
            totalMarks += marks[i];
        }

        float averagePercentage = (float) totalMarks / numberOfSubjects;
        String grade = calculateGrade(averagePercentage);

        System.out.println("\n Total Marks: " + totalMarks);
        System.out.println(" Average Percentage: " + averagePercentage + "%");
        System.out.println(" Grade: " + grade);
    }

    private static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A";
        } else if (averagePercentage >= 80) {
            return "B";
        } else if (averagePercentage >= 70) {
            return "C";
        } else if (averagePercentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}
