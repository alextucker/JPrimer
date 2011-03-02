/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jprimer.validation.validator;

import org.jprimer.core.primer.Primer;
import org.jprimer.validation.config.ValidationConfig;
import org.jprimer.validation.tmcalc.TmCalculator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class PairValidatorTest {

    public PairValidatorTest() {
    }

    @Test
    public void testTmDifference_Pass() {
        // Set up validation config
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 100.0;
        config.minTm = 10.0;
        config.tmType = TmCalculator.tmType.OPERON;
        config.tmDifference = 50.0;

        // Create 2 Primers that will validate
        Primer forwardPrimer = null;
        Primer reversePrimer = null;

        try {
            forwardPrimer = new Primer("ATGGTAGCGGTCGTAGT");
        } catch (Exception e) {
            fail("Primer is valid;");
        }

        try {
            reversePrimer = new Primer("ATTGCTGATGCTTGAT");
        } catch (Exception e) {
            fail("Primer is valid;");
        }

        // Validate the primers
        Validator forward = new Validator(forwardPrimer, config);
        if (!forward.validatePrimer()) {
            fail("Primer should validate");
        }
        Validator reverse = new Validator(reversePrimer, config);
        if (!reverse.validatePrimer()) {
            fail("Primer should validate");
        }

        // Validate the pair
        PairValidator pair = new PairValidator(forward, reverse, config);
        if (!pair.validateTmDiff(forward, reverse, config)) {
            fail("Tm difference should validate");
        }
    }

    @Test
    public void testTmDifference_Fail() {
        // Set up validation config
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 100.0;
        config.minTm = 10.0;
        config.tmType = TmCalculator.tmType.OPERON;
        config.tmDifference = 5.0;

        // Create 2 Primers that will validate
        Primer forwardPrimer = null;
        Primer reversePrimer = null;

        try {
            forwardPrimer = new Primer("ATGGTAGCGGTCGTAGT");
        } catch (Exception e) {
            fail("Primer is valid;");
        }

        try {
            reversePrimer = new Primer("ATTGCTGATGCTTGAT");
        } catch (Exception e) {
            fail("Primer is valid;");
        }

        // Validate the primers
        Validator forward = new Validator(forwardPrimer, config);
        if (!forward.validatePrimer()) {
            fail("Primer should validate");
        }
        Validator reverse = new Validator(reversePrimer, config);
        if (!reverse.validatePrimer()) {
            fail("Primer should validate");
        }

        // Validate the pair
        PairValidator pair = new PairValidator(forward, reverse, config);
        if (pair.validateTmDiff(forward, reverse, config)) {
            fail("Tm difference should not validate");
        }
    }

    @Test
    public void testPrimerDimer_Pass() {
        // Set up validation config
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 100.0;
        config.minTm = 10.0;
        config.tmType = TmCalculator.tmType.OPERON;
        config.tmDifference = 5.0;

        // Create 2 Primers that will validate
        Primer forwardPrimer = null;
        Primer reversePrimer = null;

        try {
            forwardPrimer = new Primer("ATGGTAGCGGTCGTAGT");
        } catch (Exception e) {
            fail("Primer is valid;");
        }

        try {
            reversePrimer = new Primer("GTGATGCTGGATG");
        } catch (Exception e) {
            fail("Primer is valid;");
        }

        // Validate the primers
        Validator forward = new Validator(forwardPrimer, config);
        if (!forward.validatePrimer()) {
            fail("Primer should validate");
        }
        Validator reverse = new Validator(reversePrimer, config);
        if (!reverse.validatePrimer()) {
            fail("Primer should validate");
        }

        // Validate the pair
        PairValidator pair = new PairValidator(forward, reverse, config);
        if (!pair.validatePrimerDimer(forward.primer, reverse.primer)) {
            fail("Pair should not contain primer dimer");
        }
    }

    @Test
    public void testPrimerDimer_Fail() {
        // Set up validation config
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 100.0;
        config.minTm = 10.0;
        config.tmType = TmCalculator.tmType.OPERON;
        config.tmDifference = 5.0;

        // Create 2 Primers that will validate
        Primer forwardPrimer = null;
        Primer reversePrimer = null;

        try {
            forwardPrimer = new Primer("ACCAGGTTTATGAGCATCGTTC");
        } catch (Exception e) {
            fail("Primer is valid;");
        }

        try {
            reversePrimer = new Primer("CCGTCCACAAATGGGTAAATGT");
        } catch (Exception e) {
            fail("Primer is valid;");
        }

        // Validate the primers
        Validator forward = new Validator(forwardPrimer, config);
        if (!forward.validatePrimer()) {
            fail("Primer should validate");
        }
        Validator reverse = new Validator(reversePrimer, config);
        if (!reverse.validatePrimer()) {
            fail("Primer should validate");
        }

        // Validate the pair
        PairValidator pair = new PairValidator(forward, reverse, config);
        if (pair.validatePrimerDimer(forward.primer, reverse.primer)) {
            fail("Pair should contain primer dimer");
        }
    }

    @Test
    public void testValidatePair_Pass() {
        // Set up validation config
        ValidationConfig config = new ValidationConfig();
        config.maxTm = 100.0;
        config.minTm = 10.0;
        config.tmType = TmCalculator.tmType.OPERON;
        config.tmDifference = 5.0;

        // Create 2 Primers that will validate
        Primer forwardPrimer = null;
        Primer reversePrimer = null;

        try {
            forwardPrimer = new Primer("ACCAGGTTTATGAGCATCGTTC");
        } catch (Exception e) {
            fail("Primer is valid;");
        }

        try {
            reversePrimer = new Primer("CAGAATGTCTTTTCCAGCAGGA");
        } catch (Exception e) {
            fail("Primer is valid;");
        }

        // Validate the primers
        Validator forward = new Validator(forwardPrimer, config);
        if (!forward.validatePrimer()) {
            fail("Primer should validate");
        }
        Validator reverse = new Validator(reversePrimer, config);
        if (!reverse.validatePrimer()) {
            fail("Primer should validate");
        }

        // Validate the pair
        PairValidator pair = new PairValidator(forward, reverse, config);
        if (!pair.validatePair()) {
            fail("Pair should validate");
        }
    }

}