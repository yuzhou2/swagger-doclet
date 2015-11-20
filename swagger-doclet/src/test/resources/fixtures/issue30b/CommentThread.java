package fixtures.issue30b;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonView;

@SuppressWarnings("javadoc")
public class CommentThread {

	public interface CommentThreadView {
		// noop
	}

	public interface CommentView extends Comment.UserView {
		// noop
	}

	@JsonView(CommentThread.CommentThreadView.class)
	protected String name;

	@JsonView(CommentThread.CommentView.class)
	protected List<Comment> comments;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Comment> getComments() {
		return this.comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}