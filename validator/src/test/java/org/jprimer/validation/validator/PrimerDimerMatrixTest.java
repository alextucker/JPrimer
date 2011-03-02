/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.jprimer.validation.validator;

import org.junit.Test;
import static org.junit.Assert.*;
import org.jprimer.core.primer.Primer;
import org.jprimer.validation.validator.PrimerDimerMatrix;

/**
 *
 * @author Alex
 */
public class PrimerDimerMatrixTest {

    public PrimerDimerMatrixTest() {
    }

    @Test
    public void testPDMatrix_SelfCompliment_ContainsPD() {

        Primer p = null;
        try {
            p = new Primer("ATTGATGCTGATGGGGCGGAGGCGCTGATGTATCC");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        PrimerDimerMatrix pd = new PrimerDimerMatrix(p, p);

        if (!pd.containsPrimerDimerRun) {
            fail("This primer contains self compliment primer dimer");
        }
    }

    @Test
    public void testPDMatrix_SelfCompliment_ContainsPD2() {

        Primer p = null;
        try {
            p = new Primer("GATGATGCTGTTAAATGGCTGA");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        PrimerDimerMatrix pd = new PrimerDimerMatrix(p, p);

        if (!pd.containsPrimerDimerRun) {
            fail("This primer contains self compliment primer dimer");
        }
    }

    @Test
    public void testPDMatrix_SelfCompliment_NoPD() {

        Primer p = null;
        try {
            p = new Primer("ATGGTAGCGGTCGTAGT");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        PrimerDimerMatrix pd = new PrimerDimerMatrix(p, p);

        if (pd.containsPrimerDimerRun) {
            fail("This primer does not contains self compliment primer dimer");
        }
    }

    @Test
    public void testPDMatrix_ForwardReverse_NoPD() {

        Primer primera = null;
        try {
            primera = new Primer("ATGGTAGCGGTCGTAGT");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        Primer primerb = null;
        try {
            primerb = new Primer("GTGATGCTGGATG");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        PrimerDimerMatrix pd = new PrimerDimerMatrix(primera, primerb);

        if (pd.containsPrimerDimerRun) {
            fail("This primer does not contains primer dimer");
        }
    }

    @Test
    public void testPDMatrix_ForwardReverse_ContainsPD() {

        Primer primera = null;
        try {
            primera = new Primer("ATGGTAGCGGTCGTAGT");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        Primer primerb = null;
        try {
            primerb = new Primer("ACTACGTGATGATAGTGA");
        } catch (Exception e) {
            fail("Primer is valid");
        }

        PrimerDimerMatrix pd = new PrimerDimerMatrix(primera, primerb);

        if (!pd.containsPrimerDimerRun) {
            fail("This primer contains primer dimer");
        }
    }

}