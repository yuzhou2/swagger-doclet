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
	public Collection<Comment> getComments() {
		return this.comments;
	}

	/**
	 * This sets the comments
	 * @param comments the comments to set
	 */
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}

}
