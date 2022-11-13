package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluatorTest {

    ExpressionEvaluator e = new ExpressionEvaluator();

    @Test
    void evaluateTest1() {
        String s = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        assertEquals(101D, e.evaluate(s));
    }

    @Test
    void evaluateTest2(){
        String s = "( 1 + ( ( 2 / 5 ) * 3 ) )";
        assertEquals(2.2D, e.evaluate(s));
    }

    @Test
    void evaluateTest3(){
        String s = "( ( ( 1 + ( ( 5 / 3 ) + 6 ) ) / 4 ) * 6 )";
        assertEquals(13D, Math.round(e.evaluate(s)));
        //Forced to round because difference was 10^-9
    }

    @Test
    void evaluateTest4(){
    //Testing for decimal numbers + 2 digit numbers
        String s = "( 2 + ( 250 * 0.5 ) )";
        assertEquals(127D, e.evaluate(s));
    }

    @Test
    void evaluateExceptionsTest1(){
        //Correct numbner of parentheses but an operator is missing
        String s = "( 1 + ( ( 2 + 3 ) * ( 4  5 ) ) )";
        assertThrows(RuntimeException.class, () -> e.evaluate(s));
    }

    @Test
    void evaluateExceptionsTest2(){
        //correct number of parentheses and operators but incorrect number of operands
        String s = "( 1 + ( 2 * ( + ) ) )";
        assertThrows(RuntimeException.class, () -> e.evaluate(s));
    }

    @Test
    void evaluateExceptionsTest3(){
        //correct number of parentheses but they are reversed at one point
        String s = "( 1 + ( ( 2 + 3 ) * ) 4 * 5 ( ) )";
        assertThrows(RuntimeException.class, () -> e.evaluate(s));
    }

    @Test
    void evaluateExceptionsTest4(){
        String s = "( 1 + 2 )( 2 + 4)";
        assertThrows(RuntimeException.class, () -> e.evaluate(s));
    }



}