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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

}
