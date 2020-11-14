/**
 * @author Nicholas Praimraj
 * @version 1.0
 * @since November 14, 2020
 * 
 * Description: In this lab we have to make a program to calculate our grade 
 * for the course.
 * 
 * What goes into the calculation of the grade:
 *
 * 1. Practice Problems (8 total) : 44 
 * 2. Labs (7 total): 16
 * 3. Midterms (2 total): 20
 * 4. Final Exam (1 total): 20
 * 
 * Point sum: 100
 */
package gradecalulator3130;

import java.util.Scanner;

public class GradeCalulator3130 {
    
    public static void main(String[] args) throws GradeToHighException {
        
        Scanner sc = new Scanner(System.in);
        int practiceProblemGrade, labGrade, midtermGrade, finalExamGrade;
        String warning = "Only integers can be entered for grade values.";
        String studentGrade;
        String studentGradeRange = "";
        String reCalculate;
        boolean useCalculator = true;
        
        while(useCalculator) {
            System.out.println(warning);
            System.out.println("Please enter your overall grade for pratice "
                    + "problems, labs, midterms and final in that order. Press "
                    + "enter after typing in each value.\n");
            practiceProblemGrade = sc.nextInt();
            labGrade = sc.nextInt();
            midtermGrade = sc.nextInt();
            finalExamGrade = sc.nextInt();
            
            Grade student = new Grade(practiceProblemGrade, labGrade, 
                    midtermGrade, finalExamGrade);
            studentGrade = student.getLetterGrade();
            
            if(studentGrade.equalsIgnoreCase("A")){
                studentGradeRange = "90 - 100";
            } else if(studentGrade.equalsIgnoreCase("B")){
                studentGradeRange = "80 - 89";
            }else if (studentGrade.equalsIgnoreCase("C")){
                studentGradeRange = "70 - 79";
            }else if (studentGrade.equalsIgnoreCase("D")){
                studentGradeRange = "60 - 69";
            }else if (studentGrade.equalsIgnoreCase("F")){
                studentGradeRange = "0 - 59";
            }
            
            System.out.println("\nYour grade for CISC 3130 is: " + 
                    studentGrade);
            System.out.println("The grade range for your grade is: " + 
                    studentGradeRange);
            System.out.println("\nIf you want to recalculate your grade please "
                    + "enter: recalculate. If not enter in no.");
            reCalculate = sc.next();
            if (reCalculate.equalsIgnoreCase("recalculate")){
                useCalculator = true;
                System.out.println();
            }else {
                useCalculator = false;
            }
        }
        sc.close();
    }
}
