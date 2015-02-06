package fixtures.genericcircular;

@SuppressWarnings("javadoc")
public class MeasurementsQuery<G> {

	/**
	 * This creates a MeasurementsQuery
	 */
	public MeasurementsQuery() {
	}

	private AggregationGroupExtractor<G> extractor;

	public AggregationGroupExtractor<G> getExtractor() {
		return this.extractor;
	}

	public void setExtractor(AggregationGroupExtractor<G> extractor) {
		this.extractor = extractor;
	}

}
