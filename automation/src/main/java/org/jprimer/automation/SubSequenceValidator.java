package org.jprimer.automation;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.jprimer.core.primer.Primer;
import org.jprimer.validation.config.ValidationConfig;
import org.jprimer.validation.validator.PairValidator;
import org.jprimer.validation.validator.Validator;

public class SubSequenceValidator implements Runnable {

    public SubSequence subSeq;
    public ValidationConfig valConfig;
    public AutomationConfig autoConfig;

    public List<Validator> forwardCandidates = new ArrayList<Validator>();
    public List<Validator> reverseCandidates = new ArrayList<Validator>();
    public List<PairValidator> pairCandidates =  new ArrayList<PairValidator>();

    // Ref to SubSeqCand Queue
    public Queue<SubSequenceValidator> candList;

    private Logger logger;

    public SubSequenceValidator(SubSequence s, ValidationConfig v, AutomationConfig a, Queue<SubSequenceValidator> sv) {
        this.subSeq = s;
        this.valConfig = v;
        this.autoConfig = a;
        this.candList = sv;
        logger = Logger.getLogger(this.getClass());
        //BasicConfigurator.configure();
    }

    public boolean validateSubSequence() {
        this.generateForwardPrimerCandidates();
        this.generateReversePrimerCandidates();
        if (!this.forwardCandidates.isEmpty() && !this.reverseCandidates.isEmpty()) {
            this.generatePrimerPairCandidates();
            if (!this.pairCandidates.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public void generateForwardPrimerCandidates() {
        for (int end = this.autoConfig.minPrimerLength;
        end <= this.autoConfig.maxPrimerLength; end++) {
            String seq = this.subSeq.sequence.substring(0, end);
            //logger.debug("Forward Sub Seq: " + seq);
            try {
                Primer p = new Primer(seq);
                Validator val = new Validator(p, this.valConfig);
                if (val.validatePrimer()) {
                    this.forwardCandidates.add(val);
                    //logger.debug("Forward Primer Candiate Found: " + p.sequence);
                }
            } catch (Exception ex) {
                //Logger.getLogger(SubSequenceValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generateReversePrimerCandidates() {
        for (int start = this.autoConfig.minPrimerLength;
        start <= this.autoConfig.maxPrimerLength; start++) {
            String seq = this.subSeq.sequence.substring(this.subSeq.sequence.length() - start, this.subSeq.sequence.length());
            try {
                Primer p = new Primer(seq, true);
                Validator val = new Validator(p, this.valConfig);
                if (val.validatePrimer()) {
                    this.reverseCandidates.add(val);
                    //logger.debug("Reverse Primer Candiate Found: " + p.originalSequence);
                }
            } catch (Exception ex) {
               // Logger.getLogger(SubSequenceValidator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generatePrimerPairCandidates() {
        for (Validator forward : this.forwardCandidates) {
            for (Validator reverse : this.reverseCandidates) {
                PairValidator pv = new PairValidator(forward, reverse, this.valConfig);
                if (pv.validatePair()) {
                    this.pairCandidates.add(pv);
                    //logger.debug("Pair Cand List Size:" + pairCandidates.size());
                    logger.debug("Primer Pair Candiate Found: " + pv.forward.primer.sequence + "      " + pv.reverse.primer.sequence + " @ POS: " + this.subSeq.position);
                }
            }
        }
    }

    @Override
    public void run() {
        //logger.debug("Validating sequence");
        if (this.validateSubSequence()) {
            //logger.debug("Adding to candiate list");
            this.candList.add(this);
        }
    }



}
