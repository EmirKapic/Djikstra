package ba.unsa.etf.rpr;
import static ba.unsa.etf.rpr.OperatorAndOperand.*;
import java.util.Stack;
import static ba.unsa.etf.rpr.ValidityCheck.check;

/**
 * Uses Dijkstra algorithm to evaluate expressions
 */
public class ExpressionEvaluator {
    /**
     * An implementation of Dijkstra's algorithm without priority
     * @param s the arithmetical expression to be evaluated
     * @return the result of the expression
     * @throws RuntimeException if the string isn't correct
     */
    public Double evaluate(String s){
        check(s);
        Stack<String> operators = new Stack<>();
        Stack<Double> values = new Stack<>();

        for (int i = 0; i < s.length(); ++i){
            if (isOperator(s.charAt(i)))operators.push(String.valueOf(s.charAt(i)));
            else if (isOperand(s.charAt(i)))values.push((double) s.charAt(i) - 48);
            else if (s.charAt(i) == ')'){
                String operator = operators.pop();
                Double value2 = values.pop();
                Double value1 = values.pop();
                Double result = 0D;
                switch (operator) {
                    case "+":
                        result = value1 + value2;
                        break;
                    case "-":
                        result = value1 - value2;
                        break;
                    case "*":
                        result = value1 * value2;
                        break;
                    case "/":
                        result = value1 / value2;
                        break;
                }
                values.push(result);
            }
        }
        return values.pop();
    }
}
