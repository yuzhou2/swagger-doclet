package com.tenxerconsulting.swagger.doclet.parser.jsonview;

import java.util.Collection;

@SuppressWarnings("javadoc")
public class User {

	public interface HiddenView {
		// noop
	}

	public interface UserView extends NameView, CommentView {
		// noop
	}

	public interface NameView {
		// noop
	}

	public interface CommentView extends Comment.CommentView {
		// noop
	}

	@org.codehaus.jackson.map.annotate.JsonView(User.NameView.class)
	@com.fasterxml.jackson.annotation.JsonView(User.NameView.class)
	protected String name;

	@org.codehaus.jackson.map.annotate.JsonView(User.CommentView.class)
	@com.fasterxml.jackson.annotation.JsonView(User.CommentView.class)
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