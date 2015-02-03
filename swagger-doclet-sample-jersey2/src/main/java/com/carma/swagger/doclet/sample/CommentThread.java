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

	/**
	 * This gets the name
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This sets the name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This gets the comments
	 * @return the comments
	 */
	public List<Comment> getComments() {
		return this.comments;
	}

	/**
	 * This sets the comments
	 * @param comments the comments to set
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}