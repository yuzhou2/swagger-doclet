package fixtures.issue30b;

import org.codehaus.jackson.map.annotate.JsonView;

@SuppressWarnings("javadoc")
public class Comment {

	public interface CommentView {
		// noop
	}

	public interface UserView extends User.UserView {
		// noop
	}

	public interface CommentThreadView extends CommentThread.CommentThreadView {
		// noop
	}

	@JsonView(Comment.CommentView.class)
	protected String text;

	@JsonView(Comment.UserView.class)
	protected User user;

	@JsonView(Comment.CommentThreadView.class)
	protected CommentThread commentThread;
}