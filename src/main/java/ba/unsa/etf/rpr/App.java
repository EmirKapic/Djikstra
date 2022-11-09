package ba.unsa.etf.rpr;

public class App {
    public static void main(String[] args) {
       /* String s = args[0];
        s.trim();
        try{
            ExpressionEvaluator e = new ExpressionEvaluator();
            Double rez = e.evaluate(s);
            System.out.print("Rezultat unesenog aritmetickog izraza je: ");
            System.out.println(rez);
        }
        catch(RuntimeException e){
            System.out.println("Niste unijeli validan aritmetički izraz!");
        }*/

        String s = "( 2 + ( 250 / 0.5 ) )";
        ExpressionEvaluator e = new ExpressionEvaluator();
        System.out.println(e.evaluate(s));

    }
}