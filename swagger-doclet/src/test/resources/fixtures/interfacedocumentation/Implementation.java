package fixtures.interfacedocumentation;

import javax.ws.rs.Path;

@SuppressWarnings("javadoc")
public class Implementation implements ParentInterface {

	@Override
	public String findAll(String param) {
		return null;
	}
}
