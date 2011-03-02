package org.jprimer.validation.validator;

import org.jprimer.core.primer.Primer;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex
 */
public class PrimerDimerMatrix {

	public Primer primerx;
	public Primer primery;

	public char[][] matrix;
	public char[][] pdmatrix;

	public Boolean containsPrimerDimerRun = false;

	public PrimerDimerMatrix(Primer x, Primer y) {
		this.primerx = x;
		this.primery = y;
		this.SolveMatrix();
		this.DetectPrimerDimerRuns();
	}

	private void SolveMatrix() {
		matrix = new char[this.primery.sequence.length()+2]
                        [this.primerx.sequence.length()+2];
		pdmatrix = new char[this.primery.sequence.length()+2]
                        [this.primerx.sequence.length()+2];

		for (int i = 0; i <= primerx.sequence.length() - 1; i++) {
			for (int j = 0; j <= primery.sequence.length() - 1; j++){
				if ( primerx.sequence.charAt(i) == 'A' &&
                                        primery.reverseSequence.charAt(j) == 'T'){
					this.matrix[j][i] = '1';
				} else if ( primerx.sequence.charAt(i) == 'T' &&
                                        primery.reverseSequence.charAt(j) == 'A'){
					this.matrix[j][i] = '1';
				} else if ( primerx.sequence.charAt(i) == 'G' &&
                                        primery.reverseSequence.charAt(j) == 'C'){
					this.matrix[j][i] = '1';
				} else if ( primerx.sequence.charAt(i) == 'C' &&
                                        primery.reverseSequence.charAt(j) == 'G'){
					this.matrix[j][i] = '1';
				} else {
					this.matrix[j][i] = '0';
				}
			}
		}

	}

	private void DetectPrimerDimerRuns() {

		this.pdmatrix = this.matrix.clone();

		for (int i = 0; i <= primerx.sequence.length() - 1; i++) {
			for (int j = 0; j <= primery.sequence.length() - 1; j++) {
				if (this.matrix[j][i] == '1' ||
                                        this.matrix[j][i] == 'A') {

					//Debug
					//System.out.println("PrimerDimer Found At: i=" + i + " j=" + j);

					if (j <= primerx.sequence.length() - 3 &&
                                                i <= primery.sequence.length() - 3) {
						if ((matrix[j+1][i+1] == '1' || matrix[j+1][i+1] == 'A')  &&
                                                        (matrix[j+2][i+2] == '1' || matrix[j+2][i+2] == 'A')
								&& (matrix[j+3][i+3] == '1' || matrix[j+3][i+3] == 'A') ) {
							this.pdmatrix[j][i] = 'A';
							this.pdmatrix[j+1][i+1] = 'A';
							this.pdmatrix[j+2][i+2] = 'A';
							this.pdmatrix[j+3][i+3] = 'A';
							this.containsPrimerDimerRun = true;
						}
					}

				}
			}
		}

	}

}
