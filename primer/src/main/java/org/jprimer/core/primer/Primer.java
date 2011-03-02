package org.jprimer.core.primer;

public class Primer {
    // Used for reverse primers

    public String originalSequence = "";
    public String sequence = "";
    public String complimentSequence = "";
    public String reverseSequence = "";
    public String reverseCompliment = "";
    private int gcCount = 0;
    private double gcConcentration = 0.0;
    private int length = 0;
    public double tm = 0.0;
    private boolean isReverse;

    public Primer(String seq) throws Exception {
        try {
            this.setSequence(seq);
        } catch (Exception e) {
            throw e;
        }
    }

    public Primer(String seq, boolean reverse) throws Exception {
        try {
            this.isReverse = reverse;
            this.setSequence(seq);
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

            // Analyse sequence and generate seq data
            this.AnalyzePrimer();
        } else {
            throw new Exception("Invalid Primer Sequence");
        }
    }

//    public void AnalyzePrimer() {
////        this.originalSequence = this.sequence;
////        this.reverseSequence = this.ReverseSequence();
////        this.complimentSequence = this.ComplimentSequence(this.sequence);
////        this.reverseCompliment = this.ComplimentSequence(this.reverseSequence);
////
////        // For primer dimer calculation
////        if (this.isReverse) {
////            this.reverseSequence = this.reverseCompliment;
////        }
//
//
//
//        this.length = this.sequence.length();
//        this.CalcNucleotideConcentrations();
//        this.gcConcentration = (double)this.gcCount/(double)this.length;
//    }

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

    private void CalcNucleotideConcentrations() {
        int i, len = sequence.length();

        for (i = 0; i <= (len - 1); i++) {
            if (sequence.charAt(i) == 'C') {
                this.gcCount++;
            } else if (sequence.charAt(i) == 'G') {
                this.gcCount++;
            }
        }
    }

    public void AnalyzePrimer() {
		this.ReverseSequence();
		this.ComplimentSequence();
		this.ReverseCompliment();

		if (this.isReverse) {
			this.originalSequence = this.sequence;
			this.ReverseSequence();
			this.sequence = this.reverseCompliment;
			this.ReverseSequence();
		}

        this.length = this.sequence.length();
        this.CalcNucleotideConcentrations();
        this.gcConcentration = (double)this.gcCount/(double)this.length;
	}

	/**
	 * Reverses the sequence and saves the result
	 */
	private void ReverseSequence() {
		int i, len = this.sequence.length();
		StringBuffer reverse =  new StringBuffer(len);

		for (i = (len - 1); i >= 0; i--)
		      reverse.append(this.sequence.charAt(i));

		reverseSequence = reverse.toString();
	}


	/**
	 * @param seq
	 * @return
	 *
	 * To be used with the generate complimentSequence and reverseComplimentSequence
	 */
	private String ComplimentSequence(String seq) {
		int i, len = seq.length();

		StringBuffer compliment =  new StringBuffer(len);

		for (i = 0; i <= (len - 1); i++) {
			if (seq.charAt(i) == 'A') {
				compliment.append("T");
			} else if (seq.charAt(i) == 'T') {
				compliment.append("A");
			} else if (seq.charAt(i) == 'C') {
				compliment.append("G");
			} else if (seq.charAt(i) == 'G') {
				compliment.append("C");
			}
		}

		return compliment.toString();
	}

	/**
	 * Compliments the sequence and saves the result
	 */
	private void ComplimentSequence() {
		complimentSequence = this.ComplimentSequence(sequence);
	}


	/**
	 * Compliment the reverseSequence and save the result;
	 */
	private void ReverseCompliment() {
		this.reverseCompliment = this.ComplimentSequence(reverseSequence);
	}

    public int getGcCount() {
        return gcCount;
    }

    public double getGcConcentration() {
        return gcConcentration;
    }

    public int getLength() {
        return length;
    }

    public double getTm() {
        return tm;
    }
}
