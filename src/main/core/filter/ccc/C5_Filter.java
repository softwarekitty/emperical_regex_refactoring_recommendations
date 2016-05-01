package main.core.filter.ccc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.core.features.FeatureCount;
import main.core.features.FeatureCountFactory;
import main.core.features.FeatureDictionary;
import main.core.filter.AbstractFilter;
import main.core.filter.ModelNames;

public class C5_Filter extends AbstractFilter {

	private static final Pattern C5_ORofLITERALS_OR_DEFAULTS_OR_RANGES = Pattern.compile(
			"OR•DOWN•(ALTERNATIVE•DOWN•ELEMENT•DOWN•(.|\\\\0x..|\\\\d|\\\\D|\\\\s|\\\\S|\\\\w|\\\\W|ANY|(RANGE•DOWN•(.|\\\\0x..)•(.|\\\\0x..)•UP•))•UP•UP•)+UP•");

	public C5_Filter() {
		super(ModelNames.C5, "an OR of length-one sub-patterns");
	}

	@Override
	public boolean accepts(String rawPattern, FeatureCount featureCount) {
		if (featureCount.featureSetSubsumes(requiredFeatures())) {
			String tokenStream = getTokenStream(rawPattern);
			Matcher m1 = C5_ORofLITERALS_OR_DEFAULTS_OR_RANGES.matcher(tokenStream);
			return (m1.find());
		}
		return false;
	}

	public FeatureCount requiredFeatures() {
		return FeatureCountFactory.getFeatureCount(FeatureDictionary.I_META_OR);
	}
}