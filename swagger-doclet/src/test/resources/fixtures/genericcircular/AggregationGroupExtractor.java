package fixtures.genericcircular;

@SuppressWarnings("javadoc")
public interface AggregationGroupExtractor<G> {

	G getGroup(MeasurementAggregation aggregation);
}
