package org.jprimer.automation;

import org.jprimer.validation.config.ValidationConfig;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.apache.log4j.Logger;

public class Gene {

    public String sequence;
    public Queue<SubSequence> subSeqList = new LinkedList<SubSequence>();
    public int subSeqCount = 0;

    AutomationConfig autoConfig;

    Logger logger;


    public Gene(String seq, AutomationConfig autoConfig) throws Exception {
        try {
            this.setSequence(seq);
            this.autoConfig = autoConfig;
            logger = Logger.getLogger(this.getClass());
            //logger.debug("Gene seq set as: " + this.sequence);

        } catch (Exception e) {
            throw e;
        }
    }

    public void setSequence(String seq) throws Exception {
        // Normalize the format of the sequence
        seq = seq.toUpperCase();
        seq = seq.trim();

        // Verify that the sequence does not contain invalid chars
        if (this.verifySequence(seq)) {
            this.sequence = seq;
            
        } else {
            throw new Exception("Invalid Gene Sequence");
        }
    }

    private boolean verifySequence(String seq) {
        //TODO: Replace with regex ?
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) != 'A' && seq.charAt(i) != 'T'
                    && seq.charAt(i) != 'C' && seq.charAt(i) != 'G') {
                return false;
            }
        }
        return true;
    }

    public void generateSubSequencePermutations() {
        logger.debug("Iterating Forward");
        this.IterateForward();
        logger.debug("Iterating Backward");
        this.IterateBackward();
        logger.debug("SubSeq Count: " + this.subSeqCount);
    }

    public void IterateForward() {
        for (int start=0; start <= this.sequence.length() - 
                this.autoConfig.maxSequenceLength; start++) {

            for (int startSub = this.autoConfig.minSequenceLength; startSub <=
                    this.autoConfig.maxSequenceLength; startSub++ ) {
                String subSeq = this.sequence.substring(start, startSub + start);
                //logger.debug("Forward SubSeq:  " + subSeq);
                try {
                    this.subSeqList.add(new SubSequence(subSeq, start+1));
                    this.subSeqCount++;
                } catch (Exception e) {}
            }

        }
    }

    public void IterateBackward() {
        for (int end = this.sequence.length(); end > this.sequence.length() -
                this.autoConfig.maxSequenceLength; end--) {

            for (int subStart = this.autoConfig.minSequenceLength; subStart <=
                    this.autoConfig.maxSequenceLength; subStart++) {
                String seq = this.sequence.substring(end - subStart, end);
                try {
                    this.subSeqList.add(new SubSequence(seq, (end - subStart)+1 ));
                    this.subSeqCount++;
                }catch (Exception e) {}
            }

        }
    }



    
}
