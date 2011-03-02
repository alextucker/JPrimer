package org.jprimer.validation.validator;

import org.jprimer.core.primer.Primer;
import org.jprimer.validation.config.ValidationConfig;

public class PairValidator {

    public Validator forward;
    public Validator reverse;
    private ValidationConfig config;
    private double tmDiff = 0;
    private boolean tmFlag = false;
    private boolean containsPrimerDimer;
    private PrimerDimerMatrix pd;

    public PairValidator(Validator f, Validator r, ValidationConfig c) {
        this.forward = f;
        this.reverse = r;
        this.config = c;
    }

    public boolean validatePrimerDimer(Primer forward, Primer reverse) {

        this.pd = new PrimerDimerMatrix(forward, reverse);

        if (pd.containsPrimerDimerRun) {
            this.containsPrimerDimer = true;
            return false;
        } else {
            this.containsPrimerDimer = false;
            return true;
        }
    }

    public boolean validateTmDiff(Validator forward, Validator reverse, ValidationConfig c) {
        this.tmDiff = (double) (forward.getTm() - reverse.getTm());
        if (this.tmDiff > config.tmDifference || this.tmDiff < -(config.tmDifference)) {
            this.tmFlag = true;
            return false;
        }
        return true;
    }

    public boolean validatePair() {
        if (this.validateTmDiff(this.forward, this.reverse, this.config)) {
            if (this.validatePrimerDimer(this.forward.primer, this.reverse.primer)) {
                return true;
            }
        }
        return false;
    }

    public double getTmDifference() {
        return this.tmDiff;
    }
}
