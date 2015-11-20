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

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CommentThread getCommentThread() {
		return this.commentThread;
	}

	public void setCommentThread(CommentThread commentThread) {
		this.commentThread = commentThread;
	}

}