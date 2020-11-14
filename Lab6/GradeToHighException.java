/**
 * @author Nicholas Praimraj
 * @version 1.0
 * @since November 14,2020
 * 
 * Description: Custom exception class for when a value for a grade is too 
 * large.
 */

package gradecalulator3130;

public class GradeToHighException extends Exception {
    
    public GradeToHighException(String errorMessage){
        super(errorMessage);
    }
    
}
