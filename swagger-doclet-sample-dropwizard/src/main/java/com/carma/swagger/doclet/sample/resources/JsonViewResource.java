package com.carma.swagger.doclet.sample.resources;

import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.carma.swagger.doclet.sample.api.Comment;
import com.carma.swagger.doclet.sample.api.CommentThread;
import com.carma.swagger.doclet.sample.api.User;
import com.fasterxml.jackson.annotation.JsonView;

@Path("/issue30")
@SuppressWarnings("javadoc")
public class JsonViewResource {

	@POST
	@JsonView(Comment.UserView.class)
	public Comment postComment(Comment comment) {
		User u = new User();
		u.setName("Name");
		u.setComments(new ArrayList<Comment>(1));

		CommentThread t = new CommentThread();
		t.setName("Name");
		t.setComments(new ArrayList<Comment>(1));

		Comment c = new Comment();
		c.setText("Text");
		c.setUser(u);
		c.setCommentThread(t);

		u.getComments().add(c);
		t.getComments().add(c);

		return c;
	}
}
