package org.jprimer.automation;


import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import org.jprimer.automation.AutomationConfig;
import org.jprimer.automation.AutomationDaemon;
import org.jprimer.automation.Gene;
import org.jprimer.filter.FilterConfiguration;
import org.jprimer.filter.ResultsFilter;
import org.jprimer.validation.config.ValidationConfig;
import org.jprimer.validation.validator.PairValidator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class AutomationWrapper implements Runnable {
    
    private AutomationDaemon ad;
    private AutomationDaemon outside;
    private JProgressBar bar;
    private JTextArea area;
    private FilterConfiguration filterconfig;

    public AutomationWrapper(ValidationConfig v, AutomationConfig a, String gene, JProgressBar prog, JTextArea area, FilterConfiguration filterconfig) {
        this.area = area;
        this.bar = prog;
        this.filterconfig = filterconfig;

        ad = new AutomationDaemon(v, a);
        try {
            ad.setGene(gene);
        } catch (Exception ex) {
            //Logger.getLogger(DesktopApplication1View.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        this.ad.genereateSubSeqQueue();
        this.ad.validateSubSequences();
        this.outside = this.ad;


        ResultsFilter filter = new ResultsFilter(filterconfig, ad.subSeqCandidates);
        filter.filterResults();
        this.bar.setVisible(false);

        this.area.append("Number of results:   " + filter.filtered.size() + "\n");

        for (SubSequenceValidator sv : filter.filtered) {
            this.area.append("At amplicon position:   " + sv.subSeq.position +
                    "   Amplicon length: " + sv.subSeq.sequence.length() + "\n");
            for (PairValidator pv : sv.pairCandidates) {
                this.area.append("Forward Primer: " + pv.forward.primer.sequence + 
                        "  Length: " + pv.forward.primer.sequence.length() +
                        "  Tm: " + pv.forward.getTm()  +"\n");
                this.area.append("Reverse Primer: " + pv.reverse.primer.sequence +
                        "  Length: " + pv.reverse.primer.sequence.length() +
                        "  Tm: " + pv.reverse.getTm()  +"\n");
                this.area.append("--------" + "\n");
            }
            this.area.append("***********************************" + "\n");
        }
    }

}
