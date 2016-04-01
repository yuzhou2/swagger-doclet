package fixtures.exclusion;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * The CustomExclude represents a custom exclude annotation for tests
 * @version $Id$
 * @author conor.roche
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CustomExclude {
	// noop
}
