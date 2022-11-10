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
            else if (isOperand(s.charAt(i))) {
                if (s.charAt(i+1) == ' ') //if after the number there is only a space then it's not a decimal number and we can simply push
                    values.push((double) s.charAt(i) - 48);
                else if (Character.isDigit(s.charAt(i+1)) || s.charAt(i+1) == '.'){
                    String num = String.valueOf(s.charAt(i));
                    i++;
                    while(s.charAt(i) != ' ') {
                        num += s.charAt(i);
                        i++;
                    }
                    values.push(Double.parseDouble(num));
                }
                else throw new RuntimeException();
            }
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
            else if (s.charAt(i) == ' '){}
        }
        return values.pop();
    }
}
