package org.jprimer.validation.validator;

import org.jprimer.core.primer.Primer;
import org.jprimer.validation.config.ValidationConfig;
import org.jprimer.validation.tmcalc.TmCalculator;

public class ValidatorFactory {

    private ValidationConfig config;

    // Config Vars
    private double maxTm;
    private double minTm;
    private TmCalculator.tmType tmType;

    public ValidatorFactory(ValidationConfig c) {
        this.config = c;
    }

    private void configureFactory(ValidationConfig config) {
        this.maxTm = config.maxTm;
        this.minTm = config.minTm;
        this.tmType = this.config.tmType;
    }

    public Validator getValidator(Primer p) throws Exception {
        if (this.config != null) {
            return new Validator(p, this.config);
        } else {
            throw new Exception("No configuration");
        }
    }

    public PairValidator getPairValidator(Validator f, Validator r)
            throws Exception {
        if (this.config != null) {
            return new PairValidator(f,r,this.config);
        } else {
            throw new Exception("No configuration");
        }

    }

}