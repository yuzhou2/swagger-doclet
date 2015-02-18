package fixtures.jsr303;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * The Data represents a simple pojo for testing JSR 303 annotation support
 * @version $Id$
 * @author conor.roche
 */
public class Data {

	@Null
	String nullField;

	@NotNull
	String notNullField = "";

	@Size(min = 0, max = 10)
	String stringSizeField;

	@Size(min = 1)
	int minField;

	@Size(max = 3)
	int maxField;

	@Size(min = 3, max = 5)
	int minMaxField;

	@DecimalMin(value = "5.1")
	double minDecimalField;

	@DecimalMax(value = "5.2")
	double maxDecimalField;

	@DecimalMin(value = "5.1")
	@DecimalMax(value = "6.2")
	double minMaxDecimalField;

}
