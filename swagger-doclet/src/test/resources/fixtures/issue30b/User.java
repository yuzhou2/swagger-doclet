package fixtures.issue30b;

import java.util.Collection;

import org.codehaus.jackson.map.annotate.JsonView;

@SuppressWarnings("javadoc")
public class User {

	public interface UserView extends NameView, CommentView {
		// noop
	}

	public interface NameView {
		// noop
	}

	public interface CommentView extends Comment.CommentView {
		// noop
	}

	@JsonView(User.NameView.class)
	protected String name;

	@JsonView(User.CommentView.class)
	protected Collection<Comment> comments;
}
