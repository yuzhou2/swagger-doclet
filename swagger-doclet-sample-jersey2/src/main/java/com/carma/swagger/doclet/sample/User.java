package com.carma.swagger.doclet.sample;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonView;

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
