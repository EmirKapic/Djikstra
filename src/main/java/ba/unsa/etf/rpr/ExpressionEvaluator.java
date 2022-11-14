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
        for (String str : s.split(" ")){
            if (str.equals("(")){}
            else if (str.equals("+"))operators.push(str);
            else if (str.equals("-"))operators.push(str);
            else if (str.equals("*"))operators.push(str);
            else if (str.equals("/"))operators.push(str);
            else if (str.equals("sqrt"))operators.push(str);
            else if (str.equals(")")){
                String op = operators.pop();
                Double v = values.pop();
                switch (op) {
                    case "+":
                        v = values.pop() + v;
                        break;
                    case "-":
                        v = values.pop() - v;
                        break;
                    case "*":
                        v = values.pop() * v;
                        break;
                    case "/":
                        v = values.pop() / v;
                        break;
                    case "sqrt":
                        v = Math.sqrt(v);
                        break;
                }
                values.push(v);
            }
            else{
                try{
                    values.push(Double.parseDouble(str));
                }
                catch(Exception e){
                    throw new RuntimeException();
                }
            }
        }
        return values.pop();
    }
}
