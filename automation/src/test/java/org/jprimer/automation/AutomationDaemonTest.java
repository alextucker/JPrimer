/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jprimer.automation;

import org.jprimer.validation.config.ValidationConfig;
import org.jprimer.validation.tmcalc.TmCalculator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class AutomationDaemonTest {

    public AutomationDaemonTest() {
    }

    @Test
    public void testIterateBackward() throws Exception {

        AutomationConfig auto = new AutomationConfig();
        auto.maxSequenceLength = 120;
        auto.minSequenceLength = 80;
        auto.minPrimerLength = 18;
        auto.maxPrimerLength = 30;
        auto.threadPool = 2;

        ValidationConfig val = new ValidationConfig();
        val.maxTm = 62.5;
        val.minTm = 59.5;
        val.tmDifference = 2.0;
        val.tmType = TmCalculator.tmType.OPERON;

        String gene = "ATGAGTGAACATCATGGTCGCCCATCTGGAGTGGCGTTTCTCATGCAGCACAGGCGGATTAAGGGAGTCCCGGCGCATGTAGCAGCCAATACCAATTACACGGCAAACCAGGTTTATGAGCATCGTTCAGAGGATGACTGTTGGGTCACCTACCGTGGTCGTGTCTACGACATCACGCAATATTTAGACTGGCATCCTGCTGGAAAAGACATTCTGCGCCCCTTCTTTGGCTATGATATTACGGAAGCGTGCAATGTGGCTCACTCCTGGGTGGGTATACACAAGATGATAGAACCCTTGCACATAGGAATGCTTCAAGGACCTCCACGTCTTCTGCAAGGCTATGACTATGATGCTCTGAGAACCAGAGATCTTAGAAGAGGGTCGCCGGCCTAA";

        AutomationDaemon ad = new AutomationDaemon(val, auto);
        ad.setGene(gene);
        ad.genereateSubSeqQueue();
        ad.validateSubSequences();

    }

    
}