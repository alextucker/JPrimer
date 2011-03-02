/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jprimer.core.primer;

import org.junit.Ignore;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alex
 */
public class PrimerTest {

    @Test
    public void testSetSequence_Valid() throws Exception {
        String seq = "ATTGTGATGGTGAGTGATGATGGACCGGCAG";
        try {
            Primer p = new Primer(seq);
        } catch (Exception e) {
            fail("Valid Sequence. No Exception");
        }
    }

    @Test(expected = Exception.class)
    public void testSetSequence_Invalid() throws Exception {
        String seq = "ATTGTAGTGXXX";
        Primer p = new Primer(seq);
    }


    @Test
    public void testAnalysePrimer_Forward_Valid() {
        String seq = "ATCG";
        Primer p = null;

        try {
            p = new Primer(seq);
        } catch (Exception e) {
            fail("Primer is valid");
        }

        assertEquals("ATCG", p.sequence);
        assertEquals("GCTA", p.reverseSequence);
        assertEquals("TAGC", p.complimentSequence);
        assertEquals("CGAT", p.reverseCompliment);
    }

    @Test
    public void testAnalysePrimer_ReverseFalse_Valid() {
        String seq = "ATCG";
        Primer p = null;

        try {
            p = new Primer(seq, false);
        } catch (Exception e) {
            fail("Primer is valid");
        }

        assertEquals("ATCG", p.sequence);
        assertEquals("GCTA", p.reverseSequence);
        assertEquals("TAGC", p.complimentSequence);
        assertEquals("CGAT", p.reverseCompliment);
    }

    @Test @Ignore
    public void testAnalysePrimer_ReverseTrue_Valid() {
        String seq = "ATCG";
        Primer p = null;

        try {
            p = new Primer(seq, true);
        } catch (Exception e) {
            fail("Primer is valid");
        }
        
        assertEquals("ATCG", p.originalSequence);
        assertEquals("TAGC", p.complimentSequence);
        assertEquals("CGAT", p.reverseCompliment);

        // For Primer Dimer
        assertEquals("CGAT", p.reverseSequence);
        
    }

    @Test
    public void testGetGcCount() {
        String seq = "AAATTTGGGCCC";
        Primer p = null;

        try {
            p = new Primer(seq, true);
        } catch (Exception e) {
            fail("Primer is valid");
        }

        assertEquals(6, p.getGcCount());
    }

    @Test
    public void testGetGcConcentration() {
        String seq = "AAATTTGGGCCC";
        Primer p = null;

        try {
            p = new Primer(seq, true);
        } catch (Exception e) {
            fail("Primer is valid");
        }

        assertEquals(0.5, p.getGcConcentration());
    }

    @Test
    public void testGetLength() {
        String seq = "AAATTTGGGCCC";
        Primer p = null;

        try {
            p = new Primer(seq, true);
        } catch (Exception e) {
            fail("Primer is valid");
        }

        assertEquals(12, p.getLength());
    }


}