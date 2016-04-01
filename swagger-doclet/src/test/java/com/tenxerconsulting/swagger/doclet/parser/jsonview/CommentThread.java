package com.tenxerconsulting.swagger.doclet.parser.jsonview;

import java.util.List;

@SuppressWarnings("javadoc")
public class CommentThread {

	public interface CommentThreadView {
		// noop
	}

	public interface CommentView extends Comment.UserView {
		// noop
	}

	@org.codehaus.jackson.map.annotate.JsonView(CommentThread.CommentThreadView.class)
	@com.fasterxml.jackson.annotation.JsonView(CommentThread.CommentThreadView.class)
	protected String name;

	@org.codehaus.jackson.map.annotate.JsonView(CommentThread.CommentView.class)
	@com.fasterxml.jackson.annotation.JsonView(CommentThread.CommentView.class)
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