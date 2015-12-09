package com.carma.swagger.doclet.parser.jsonview;

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

	@org.codehaus.jackson.map.annotate.JsonView(Comment.CommentView.class)
	@com.fasterxml.jackson.annotation.JsonView(Comment.CommentView.class)
	protected String text;

	// commented out due to infinite recursion issue in jackson 2

	// @org.codehaus.jackson.map.annotate.JsonView(Comment.UserView.class)
	// @com.fasterxml.jackson.annotation.JsonView(Comment.UserView.class)
	// protected User user;

	// @org.codehaus.jackson.map.annotate.JsonView(Comment.CommentThreadView.class)
	// @com.fasterxml.jackson.annotation.JsonView(Comment.CommentThreadView.class)
	// protected CommentThread commentThread;

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

}