package recreateArtifacts.edgeTests;

import java.util.Arrays;

/**
 * essentially a wrapper for Double[], but also handling NAs
 * 
 * @author cc
 *
 */
public class AnswerColumn {
	private final String regexCode;
	private final Double[] values;
	private final double[] existingValues;
	private final double sum;

	public AnswerColumn(String regexCode, Double[] values){
		this.regexCode = regexCode;
		this.values = values;
		int count = 0;
		for(Double d : values){
			if(!d.equals(Double.NaN)){
				count++;
			}
		}
		double s = 0;
		double[] exValues = new double[count];
		int index = 0;
		for(Double d : values){
			if(!d.equals(Double.NaN)){
				exValues[index++] = d;
				s +=d;
			}
		}
		this.existingValues = exValues;
		this.sum = s;
	}

	@Override
	public String toString() {
		return "AnswerColumn [regexCode=" + regexCode + ", values=" +
			Arrays.toString(values) + "]";
	}

	public String getRegexCode() {
		return regexCode;
	}

	public Double[] getValues() {
		return values;
	}

	public double getAvg() {
		return sum/(existingValues.length + 0.0);
	}

	public int getNGreaterThanZero() {
		int count = 0;
		for(double d : existingValues){
			if(d >0){
				count++;
			}
		}
		return count;
	}

	public int getNExistingValues() {
		return existingValues.length;
	}

}
