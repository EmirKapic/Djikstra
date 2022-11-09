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
                Double toBePushed = 0D;
                if (s.charAt(i+1) == ' ') //if after the number there is only a space then its not a decimal number and we can simply push
                    values.push((double) s.charAt(i) - 48);
                else if (s.charAt(i+1) == '.'){ //otherwise look after decimal point
                    toBePushed += (double)s.charAt(i) - 48; //the whole part
                    i+=2;
                    if (!Character.isDigit(s.charAt(i)))throw new RuntimeException(); //If after decimal point there is no number, it's wrong
                    int factor = 10;
                    do{
                        toBePushed += (((double)s.charAt(i) - 48)/factor); //
                        factor *= 10;
                        i++;
                    }while(s.charAt(i) != ' ');
                    values.push(toBePushed);
                }
                else if (Character.isDigit(s.charAt(i+1))){
                    int counter = 0;
                    for (int j = i; s.charAt(j) != ' '; ++j)counter++;
                    int factor = (int) Math.pow(10,counter - 1);
                    do{
                        toBePushed += (((double)s.charAt(i) - 48) * factor);
                        factor /= 10;
                        i++;
                    }while (s.charAt(i) != ' ');
                    values.push(toBePushed);
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
