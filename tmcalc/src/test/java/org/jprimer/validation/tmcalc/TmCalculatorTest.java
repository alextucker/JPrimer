package org.jprimer.validation.tmcalc;

import junit.framework.TestCase;
import org.jprimer.validation.tmcalc.TmCalculator.tmType;
import org.jprimer.core.primer.Primer;

/**
 *
 * @author Alex
 */
public class TmCalculatorTest extends TestCase {
    
    public void testOperon_ValidPrimer_ValidTemp() {
        Primer p = null;
        try {
            p = new Primer("ATTGATGCTGATGGCTGATGTATCC");
            TmCalculator calc = new TmCalculator(p);
            calc.setCalcType(tmType.OPERON);
            double result = calc.calculateTm();
            System.out.println(result);
            assertEquals(62.94, result);
        } catch (Exception e) {
            fail ("Primer must be valid");
        }
    }

    public void testOperon_ValidPrimer_ValidTemp2() {
        Primer p = null;
        try {
            p = new Primer("ATTGATGCTGATGGGGCGGAGGCGCTGATGTATCC");
            TmCalculator calc = new TmCalculator(p);
            calc.setCalcType(tmType.OPERON);
            double result = calc.calculateTm();
            System.out.println(result);
            assertEquals(74.04, result);
        } catch (Exception e) {
            fail ("Primer must be valid");
        }
    }

}
