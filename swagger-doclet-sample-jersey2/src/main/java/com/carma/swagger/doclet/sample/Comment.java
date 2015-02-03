package com.carma.swagger.doclet.sample;

import com.fasterxml.jackson.annotation.JsonView;

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

	/**
	 * This gets the text
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * This sets the text
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * This gets the user
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * This sets the user
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * This gets the commentThread
	 * @return the commentThread
	 */
	public CommentThread getCommentThread() {
		return this.commentThread;
	}

	/**
	 * This sets the commentThread
	 * @param commentThread the commentThread to set
	 */
	public void setCommentThread(CommentThread commentThread) {
		this.commentThread = commentThread;
	}
}