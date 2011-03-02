/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jprimer.automation;

import org.jprimer.validation.config.ValidationConfig;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class GeneTest {

    public GeneTest() {
    }



    @Test
    public void testIterateForward() throws Exception {

        AutomationConfig auto = new AutomationConfig();
        auto.maxSequenceLength = 20;
        auto.minSequenceLength = 5;

        ValidationConfig val = new ValidationConfig();

        String gene = "TGATGGGCTGATCGATGCGATCGCTGATCGTAGGCTAGCTAGCTACGATCGACTAGCTATCAGCTACGATCGACTAGCTACGATCGATCAGCTACGATCGACTACGATCGACTAGCTACGATCGACTAGCATCGACTAGCTACGACTAGCTACGATCGACTACGATCGACTAGCATCGACTAGCATCG";
        
        try {
            Gene g = new Gene(gene, auto);
            g.IterateForward();
//            System.out.println("--------FORWARD-----------");
//            for (SubSequence s : g.subSeqList) {
//                System.out.println(s.sequence + "  :  " + s.position);
//            }
        } catch (Exception e) {}

        
    }

    @Test
    public void testIterateBackward() throws Exception {

        AutomationConfig auto = new AutomationConfig();
        auto.maxSequenceLength = 20;
        auto.minSequenceLength = 5;

        ValidationConfig val = new ValidationConfig();

        String gene = "TGATGGGCTGATCGATGCGATCGCTGATCGTAGGCTAGCTAGCTACGATCGACTAGCTATCAGCTACGATCGACTAGCTACGATCGATCAGCTACGATCGACTACGATCGACTAGCTACGATCGACTAGCATCGACTAGCTACGACTAGCTACGATCGACTACGATCGACTAGCATCGACTAGCATCG";

        try {
            Gene g = new Gene(gene, auto);
            g.IterateBackward();
//            System.out.println("-------BACKWARD-----------");
//            for (SubSequence s : g.subSeqList) {
//                System.out.println(s.sequence + "  :  " + s.position);
//            }
        } catch (Exception e) {}


    }


}