package org.jprimer.automation;

public class SubSequence {

    public int position;
    public String sequence;
    
    public SubSequence(String seq, int p) throws Exception {
        try {
            this.setSequence(seq);
            this.position = p;
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
            throw new Exception("Invalid Sub Sequence");
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

}
