package com.carma.swagger.doclet.sample;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.carma.swagger.doclet.sample.api.Comment;
import com.carma.swagger.doclet.sample.api.CommentThread;
import com.carma.swagger.doclet.sample.api.User;
import com.fasterxml.jackson.annotation.JsonView;

@Path("jsonview")
@Produces({ MediaType.APPLICATION_JSON })
@SuppressWarnings("javadoc")
public class JsonViewResource {

	@GET
	@JsonView(User.UserView.class)
	public User getUser() {
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

		return u;
	}
}
