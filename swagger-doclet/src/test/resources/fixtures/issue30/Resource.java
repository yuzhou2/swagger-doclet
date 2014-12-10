package fixtures.issue30;

import java.util.ArrayList;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.annotation.JsonView;

@Path("/issue30")
@Produces({ MediaType.APPLICATION_JSON })
@SuppressWarnings("javadoc")
public class Resource {

	@POST
	@JsonView(Comment.UserView.class)
	public Comment postComment(Comment comment) {
		User u = new User();
		u.name = "Name";
		u.comments = new ArrayList<Comment>(1);

		CommentThread t = new CommentThread();
		t.name = "Name";
		t.comments = new ArrayList<Comment>(1);

		Comment c = new Comment();
		c.text = "Text";
		c.user = u;
		c.commentThread = t;

		u.comments.add(c);
		t.comments.add(c);

		return c;
	}
}
