package ba.unsa.etf.rpr;

import java.util.Stack;

import static ba.unsa.etf.rpr.ValidityCheck.check;

/**
 * Uses Dijkstra algorithm to evaluate expressions
 */
public class ExpressionEvaluator {
    public ExpressionEvaluator(){}
    public Double evaluate(String s){
        check(s);

        Stack<String> operators = new Stack<>();
        Stack<Double> values = new Stack<>();


        return 0D;
    }
}
