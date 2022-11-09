package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static ba.unsa.etf.rpr.ValidityCheck.check;
import static org.junit.jupiter.api.Assertions.*;

class ValidityCheckTest {
    String toBeEvaled;

    @Test
    void checkTest() {
        toBeEvaled = "( 1 + ( (2 + 3 ) * ( 4 * 5 ) ) )";
        check(toBeEvaled);
        //If it does not throw exception, works properly
    }

    @Test
    void checkException(){
        toBeEvaled = "( 1 + ( (2 + 3 ) * ( 4  5 ) ) )";
        assertThrows(RuntimeException.class, () -> check(toBeEvaled));
    }

}