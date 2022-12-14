package ba.unsa.etf.rpr;

import java.util.Stack;

import static ba.unsa.etf.rpr.OperatorAndOperand.isOperator;
import static ba.unsa.etf.rpr.OperatorAndOperand.isSQRT;

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
        if (s.isEmpty())throw new RuntimeException();

        //self-evident
        if (s.charAt(0) != '(' || s.charAt(s.length() - 1) != ')')throw new RuntimeException();


        int numberOfLeftPar = 0, numberOfRightPar = 0, numberOfOperators = 0;
        for (int i = 0; i < s.length(); ++i){
            if (s.charAt(i) == '(') numberOfLeftPar++;
            if (s.charAt(i) == ')') numberOfRightPar++;
            if (isOperator(s.charAt(i))) {
                //Short explanation: the only allowed symbols around operators are as follows(i'll take + as an example operator and 1 as number):
                // ) + 1, ) + (, ) + s, t + (, 1 + 1
                //Everything else should fall
                if ((!Character.isDigit(s.charAt(i-2)) && s.charAt(i-2) != 't') || (!Character.isDigit(s.charAt(i+2)) && s.charAt(i+2) != 's') ){
                    if (s.charAt(i-2) == ')' && (Character.isDigit(s.charAt(i+2)) || s.charAt(i+2) == '(') || s.charAt(i+2) == 's'){}
                    else if (s.charAt(i+2) == '(' && (Character.isDigit(s.charAt(i-2)) || s.charAt(i-2) == ')' || s.charAt(i-2) == 't')){}
                    else throw new RuntimeException();

                }
                numberOfOperators++;
            }
            if (isSQRT(s,i)) {
                i+=4;
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
