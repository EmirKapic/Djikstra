package ba.unsa.etf.rpr;


/**
 * Helper methods to determine if the character we're currently checking is an operator or an operand
 */
//Testovi nisu pisani jer su klasa i metode ocigledno trivijalni
class OperatorAndOperand {
    /**
     * For the given char checks if it is an operator
     * @param c the character to check
     * @return true if it's an arithmetical operator, otherwise false
     */

     static boolean isOperator(char c){
        return (c == '+' || c == '/' || c == '*' || c == '-');
    }
    /**
     * For the given char checks if it is an operand, in other words, if its a number
     * @param c the character to check
     * @return true if it's a number, otherwise falsee
     */
    static boolean isOperand(char c){
        return Character.isDigit(c);
    }

    static boolean isSQRT(String s, int i){
        if (s.charAt(i) != 's')return false;
        if (s.substring(i, i + 4).equals("sqrt")){
            return true;
        }
        else throw new RuntimeException();
    }
}
