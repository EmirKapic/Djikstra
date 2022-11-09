package ba.unsa.etf.rpr;

/**
 * Checks the validity of string passed to ExpressionEvaluator
 */

public class ValidityCheck {
    /**
     * if the string is an arithmetic expression does nothing, otherwise throws RuntimeException
     * @param s the string to be evaluated
     */
    public static void check(String s){
        int numberOfLeftPar = 0, numberOfRightPar = 0, numberOfOperators = 0;
        for (int i = 0; i < s.length(); ++i){
            if (s.charAt(i) == '(') numberOfLeftPar++;
            if (s.charAt(i) == ')') numberOfRightPar++;
            if (s.charAt(i) == '+' || s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == '-')numberOfOperators++;
        }
        if (numberOfLeftPar != numberOfRightPar || numberOfLeftPar != numberOfOperators)
            throw new RuntimeException();

    }
}
