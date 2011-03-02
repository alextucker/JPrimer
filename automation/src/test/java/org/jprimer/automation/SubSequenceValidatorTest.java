/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jprimer.automation;


import java.util.Queue;
import org.junit.Ignore;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.jprimer.validation.tmcalc.TmCalculator;
import org.jprimer.validation.config.ValidationConfig;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class SubSequenceValidatorTest {

    public SubSequenceValidatorTest() {
    }


    
    @Test @Ignore
    public void testValidateSubSequence() throws Exception {
        SubSequence ss = new SubSequence("AGTGCTGATGCTGATGCTGATGATGCTGATCGTAGCTAGCTACGTACGATCGACTACGATCGACTAGCATCGACTAGCTACGATCGACTAGCTCTGATGCTAGCTAATGTAGTGGC", 5);
        AutomationConfig auto = new AutomationConfig();
        auto.maxSequenceLength = 20;
        auto.minSequenceLength = 5;
        auto.minPrimerLength = 18;
        auto.maxPrimerLength = 30;
        auto.threadPool = 2;

        ValidationConfig val = new ValidationConfig();
        val.maxTm = 70.0;
        val.minTm = 50.0;
        val.tmDifference = 2.0;
        val.tmType = TmCalculator.tmType.OPERON;

        Queue<SubSequenceValidator> list = new ConcurrentLinkedQueue<SubSequenceValidator>();

        SubSequenceValidator ssv = new SubSequenceValidator(ss, val, auto, list);
        ssv.validateSubSequence();
    }

  

}