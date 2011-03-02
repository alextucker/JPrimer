package org.jprimer.validation.validator;

import org.jprimer.validation.config.ValidationConfig;
import org.jprimer.core.primer.Primer;
import org.jprimer.validation.tmcalc.TmCalculator;

public class Validator {

    public ValidationConfig config;
    public Primer primer;
    private double tm;
    private boolean isTmValid;
    private boolean containsPrimerDimer;
    private PrimerDimerMatrix pd;

    public Validator(Primer p, ValidationConfig c) {
        this.primer = p;
        this.config = c;
    }

    public boolean validateTm(Primer primer, ValidationConfig config) {
        TmCalculator calc = new TmCalculator(primer);
        calc.setCalcType(config.tmType);
        this.tm = calc.calculateTm();

        // Compare the calculated tm to the min/max
        if ((this.tm > config.maxTm) || (this.tm < config.minTm)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean validatePrimerDimer(Primer primer) {

        this.pd = new PrimerDimerMatrix(primer, primer);

        if (pd.containsPrimerDimerRun) {
            this.containsPrimerDimer = true;
            return false;
        } else {
            this.containsPrimerDimer = false;
            return true;
        }
    }

    public boolean validatePrimer() {
        if (this.validateTm(this.primer, this.config)) {
            if (this.validatePrimerDimer(this.primer)) {
                return true;
            }
        }

        return false;
    }

    public double getTm() {
        return this.tm;
    }
}
