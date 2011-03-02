/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jprimer.validation.validator;

import org.junit.Test;
import static org.junit.Assert.*;

import org.jprimer.core.primer.Primer;
import org.jprimer.validation.config.ValidationConfig;
import org.jprimer.validation.tmcalc.TmCalculator;


/**
 *
 * @author Alex
 */
public class ValidatorTest {

    public ValidatorTest() {
    }

    @Test
    public void testTmValidation_Pass_Operon() {
        Primer p = null;
        try {
            p = new Primer("ATTGATGCTGATGGGGCGGAGGCGCTGATGTATCC");
        } catch (Exception e) {
            fail("Primer is valid");
        }
        
        // Set up validator config for pass
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 75.0;
        config.minTm = 73.0;
        config.tmType = TmCalculator.tmType.OPERON;

        Validator val = new Validator(p, config);

        if (!val.validateTm(p, config)) {
            fail("Primer should validate this tm");
        }
    }

    @Test
    public void testTmValidation_FailMax_Operon() {
        Primer p = null;
        try {
            p = new Primer("ATTGATGCTGATGGGGCGGAGGCGCTGATGTATCC");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        // Set up validator config for pass
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 70.0;
        config.minTm = 65.0;
        config.tmType = TmCalculator.tmType.OPERON;

        Validator val = new Validator(p, config);

        if (val.validateTm(p, config)) {
            fail("Primer should fail this tm");
        }
    }

    @Test
    public void testTmValidation_FailMin_Operon() {
        Primer p = null;
        try {
            p = new Primer("ATTGATGCTGATGGGGCGGAGGCGCTGATGTATCC");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        // Set up validator config for pass
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 80.0;
        config.minTm = 75.0;
        config.tmType = TmCalculator.tmType.OPERON;

        Validator val = new Validator(p, config);

        if (val.validateTm(p, config)) {
            fail("Primer should fail this tm");
        }
    }

    @Test
    public void testPrimerDimerValidation_Fail() {
        Primer p = null;
        try {
            p = new Primer("ATTGATGCTGATGGGGCGGAGGCGCTGATGTATCC");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        // Set up validator config for pass
        ValidationConfig config = new ValidationConfig();
        Validator val = new Validator(p, config);

        if (val.validatePrimerDimer(p)) {
            fail("Primer should have a primer dimer");
        }
    }

    @Test
    public void testPrimerDimerValidation_Pass() {
        Primer p = null;
        try {
            p = new Primer("ATGGTAGCGGTCGTAGT");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        // Set up validator config for pass
        ValidationConfig config = new ValidationConfig();
        Validator val = new Validator(p, config);

        if (!val.validatePrimerDimer(p)) {
            fail("Primer should not have a primer dimer");
        }
    }

    @Test
    public void testWholeValidation_PassTmOperon_PassPD() {
        Primer p = null;
        try {
            p = new Primer("ATGGTAGCGGTCGTAGT");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        // Set up validator config for pass
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 58.0;
        config.minTm = 54.0;
        config.tmType = TmCalculator.tmType.OPERON;

        Validator val = new Validator(p, config);

        if (!val.validatePrimer()) {
            fail("Tm and PD are valid");
        }
    }

    @Test
    public void testWholeValidation_FailTmOperon_PassPD() {
        Primer p = null;
        try {
            p = new Primer("ATGGTAGCGGTCGTAGT");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        // Set up validator config for pass
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 55.0;
        config.minTm = 50.0;
        config.tmType = TmCalculator.tmType.OPERON;

        Validator val = new Validator(p, config);

        if (val.validatePrimer()) {
            fail("Tm is not valid");
        }
    }

    @Test
    public void testWholeValidation_PassTmOperon_FailPD() {
        Primer p = null;
        try {
            p = new Primer("GATGATGCTGTTAAATGGCTGA");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        // Set up validator config for pass
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 80.0;
        config.minTm = 50.0;
        config.tmType = TmCalculator.tmType.OPERON;

        Validator val = new Validator(p, config);

        if (val.validatePrimer()) {
            fail("PD is not valid");
        }
    }

    @Test
    public void testWholeValidation_FailTmOperon_FailPD() {
        Primer p = null;
        try {
            p = new Primer("GATGATGCTGTTAAATGGCTGA");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        // Set up validator config for pass
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 55.0;
        config.minTm = 50.0;
        config.tmType = TmCalculator.tmType.OPERON;

        Validator val = new Validator(p, config);

        if (val.validatePrimer()) {
            fail("PD is not valid");
        }
    }

}