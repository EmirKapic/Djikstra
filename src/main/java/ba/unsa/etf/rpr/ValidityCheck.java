package ba.unsa.etf.rpr;

import java.util.Stack;

import static ba.unsa.etf.rpr.OperatorAndOperand.isOperator;

/**
 * Checks the validity of string passed to ExpressionEvaluator
 */

class ValidityCheck {
    /**
     * if the string is an arithmetic expression does nothing, otherwise throws RuntimeException
     * @param s the string to be evaluated
     * @throws RuntimeException if the string isn't correctt
     */
    static void check(String s){

        s.trim();

        //self-evident
        if (s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')')throw new RuntimeException();


        int numberOfLeftPar = 0, numberOfRightPar = 0, numberOfOperators = 0;
        for (int i = 0; i < s.length(); ++i){
            if (s.charAt(i) == '(') numberOfLeftPar++;
            if (s.charAt(i) == ')') numberOfRightPar++;
            if (isOperator(s.charAt(i))) {
                if (!Character.isDigit(s.charAt(i-2)) || !Character.isDigit(s.charAt(i+2))){
                    if (s.charAt(i-2) == ')' && (Character.isDigit(s.charAt(i+2)) || s.charAt(i+2) == '(')){}
                    else if (s.charAt(i+2) == '(' && (Character.isDigit(s.charAt(i-2)) || s.charAt(i-2) == ')')){}
                    else throw new RuntimeException();

                }
                numberOfOperators++;
            }

        }
        //if not left_par == right_par == number_of_operations it is incorrect
        if (numberOfLeftPar != numberOfRightPar || numberOfLeftPar != numberOfOperators)
            throw new RuntimeException();

        //The next part checks if all parentheses are in order
        Stack<Character> pars = new Stack<>();
        pars.push(s.charAt(0));
        for (int i = 1; i < s.length(); ++i){
            if (pars.isEmpty())throw new RuntimeException();
            char c = s.charAt(i);
            if (c == '(')
                pars.push(c);
            else
                if (c == ')')pars.pop();
        }
        if (!pars.isEmpty())throw new RuntimeException();
    }
}
