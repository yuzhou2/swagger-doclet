package fixtures.issue30;

import java.util.Collection;

import org.codehaus.jackson.map.annotate.JsonView;

@SuppressWarnings("javadoc")
public class User {

	public interface UserView {
		// noop
	}

	public interface CommentView extends Comment.UserView {
		// noop
	}

	@JsonView(User.UserView.class)
	protected String name;

	@JsonView(User.CommentView.class)
	protected Collection<Comment> comments;
}
