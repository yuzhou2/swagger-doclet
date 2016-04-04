package fixtures.fileupload;

import java.io.File;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * The FileUploadResource represents a resource for testing various flavours of file upload.
 * @version $Id$
 * @author conor.roche
 */
@Path("/fileupload")
@SuppressWarnings("javadoc")
public class FileUploadResource {

	@POST
	@Path("/jerseyformdata")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response jerseyFormData(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("contentDisposition") FormDataContentDisposition contentDispositionHeader) {
		int fileSize = 0;
		return Response.ok().entity("Received file of " + fileSize + " bytes.").build();
	}

	@POST
	@Path("/multipartjavafile")
	@Consumes({ MediaType.MULTIPART_FORM_DATA, MediaType.APPLICATION_OCTET_STREAM })
	public Response multipartJavafile(File theFile) {
		long fileSize = theFile.length();
		return Response.ok().entity("Received file of " + fileSize + " bytes.").build();
	}

	@POST
	@Consumes({ "image/*" })
	@Path("/singlepartjavafile")
	Response singlePartJavaFile(File theFile) {
		// NOTE as this is NOT multipart it should be rendered in swagger as param type body
		long fileSize = theFile.length();
		return Response.ok().entity("Received file of " + fileSize + " bytes.").build();
	}

	@POST
	@Path("/formparams")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response formParams(@FormParam("param1") String param1a, @FormParam("param2") String param2a) {
		return Response.ok().entity("Received param1: " + param1a + ", param2: " + param2a).build();
	}

	// NOTE in this case we expect the image data to be in a part named photo, we could rename the
	// method parameter from data to photo and that would work, however data is more correct
	// and because it may be useful in other scenarios we use the new
	// @paramsName option
	/**
	 * @paramsName data photo
	 */
	@POST
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Path("/resteasymultipartformdatainput")
	// TODO ideally composite multipart types like Map<String, Customer> and @MultipartForm that resteasy supports,
	// can be treated similar to @BeanParam type classes where
	// the related class can define the parts that should be submitted on upload as additional parameters...
	Response resteasyMultipartFormDataInput(MultipartFormDataInput data) {
		return Response.ok().build();
	}

	@POST
	@Consumes({ "image/*" })
	@Path("/singlepartinputstream")
	Response singlePartInputStream(InputStream photo) {
		// NOTE as this is NOT multipart it should be rendered in swagger as param type body
		return Response.ok().build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@Path("/bytearraysinglepart")
	public Response byteArraySinglePart(byte[] payload) {
		if (payload != null) {
			return Response.ok().build();
		}
		return Response.status(400).build();
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/bytearraymultipart")
	public Response byteArrayMultiPart(byte[] payload) {
		if (payload != null) {
			return Response.ok().build();
		}
		return Response.status(400).build();
	}

}
