package org.jprimer.validation.tmcalc;

import java.text.DecimalFormat;
import org.jprimer.core.primer.Primer;

public class TmCalculator {

    Primer primer;
    tmType calcType = tmType.OPERON;

    public enum tmType {
        DEFAULT, OPERON;
    }

    public TmCalculator(Primer p) {
        this.primer = p;
    }

    public void setCalcType(tmType type) {
        this.calcType = type;
    }

    public double calculateTm() {
        switch(calcType) {
        //From Dr. Yee's (Trent University) Lab book
        case DEFAULT: return (double)( 4*(primer.getGcCount())
                + 2*(primer.getLength() - (primer.getGcCount())) - 5 );

        //Tm calc for Eurofins Operon (www.operon.com).
        //Thanks to Russell Everett from Operon for helping me get
        //this calculation correct.
        case OPERON:
            double result = (double)64.9 + ( (41 * (primer.getGcCount()))
                / (double)primer.getLength() ) - (500/(double)primer.getLength());

            // Round result to match output from Operon tool
            DecimalFormat twoPlaces = new DecimalFormat("#.##");
            return Double.valueOf(twoPlaces.format(result));
        }
        throw new AssertionError("Unkown Tm Calc");
    }

}
