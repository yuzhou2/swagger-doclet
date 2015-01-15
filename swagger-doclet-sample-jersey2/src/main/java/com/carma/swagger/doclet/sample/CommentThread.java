package com.carma.swagger.doclet.sample;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

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
}