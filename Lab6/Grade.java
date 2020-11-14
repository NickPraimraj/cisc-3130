/**
 * @author Nicholas Praimraj
 * @version 1.0
 * @since November 14, 2020
 * 
 * Description: Constructor for the grade.
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

public class Grade {
    
    private int practiceProblemGrade;
    private int labGrade;
    private int midtermGrade;
    private int finalExamGrade;
    private String letterGrade;
    
    public Grade(int practiceProblemsGrade, int labGrade, int midtermGrade,
            int finalExamGrade) throws GradeToHighException{
        
        this.practiceProblemGrade = practiceProblemsGrade;
        this.labGrade = labGrade;
        this.midtermGrade = midtermGrade;
        this.finalExamGrade = finalExamGrade;
        
        if(practiceProblemGrade > 44 || labGrade > 16 || midtermGrade > 20 ||
                finalExamGrade > 20) {
            throw new GradeToHighException("One of the values for the grades "
                    + "entered was above the allowable amount.");
        }
        
        // Max value should be 100
        int gradeTally = practiceProblemGrade + labGrade + midtermGrade + 
                finalExamGrade;
        
        if(gradeTally <= 100 && gradeTally >= 90){
            letterGrade = "A";
        }else if (gradeTally <= 89 && gradeTally >= 80){
            letterGrade = "B";
        }else if(gradeTally <= 79 && gradeTally >= 70) {
            letterGrade = "C";        
        }else if(gradeTally <= 69 && gradeTally >= 60) {
            letterGrade = "D";
        }else if(gradeTally <= 59 && gradeTally >=0){
            letterGrade = "F";
        }
    }

    public int getPracticeProblemGrade() {
        return practiceProblemGrade;
    }

    public void setPracticeProblemGrade(int practiceProblemGrade) {
        this.practiceProblemGrade = practiceProblemGrade;
    }

    public int getLabGrade() {
        return labGrade;
    }

    public void setLabGrade(int labGrade) {
        this.labGrade = labGrade;
    }

    public int getMidtermGrade() {
        return midtermGrade;
    }

    public void setMidtermGrade(int midtermGrade) {
        this.midtermGrade = midtermGrade;
    }

    public int getFinalExamGrade() {
        return finalExamGrade;
    }

    public void setFinalExamGrade(int finalExamGrade) {
        this.finalExamGrade = finalExamGrade;
    }
    
    public String getLetterGrade(){
        return letterGrade;
    }
}
