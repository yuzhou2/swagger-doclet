package com.tenxerconsulting.swagger.doclet.sample.resources;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * The FileResource represents a resource for testing file upload
 * @version $Id$
 * @author conor.roche
 */
@Path("/file")
@SuppressWarnings("javadoc")
public class FileResource {

	@POST
	@Path("/jerseyformdata")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response upload1(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("contentDisposition") FormDataContentDisposition contentDispositionHeader) throws IOException {
		if (fileInputStream != null) {
			byte[] data = com.google.common.io.ByteStreams.toByteArray(fileInputStream);
			int fileSize = data.length;
			String disposition = null;
			if (contentDispositionHeader != null) {
				disposition = contentDispositionHeader.getFileName();
			}
			System.out.println("Received file of " + fileSize + " bytes. disposition file name: " + disposition);
			return Response.ok().build();
		}
		return Response.status(400).build();
	}

	@POST
	@Path("/javafilesinglepart")
	@Consumes({ MediaType.APPLICATION_OCTET_STREAM })
	public Response upload2(File theFile) {
		if (theFile != null) {
			long fileSize = theFile.length();
			System.out.println("Received file of " + fileSize + " bytes.");
			return Response.ok().entity("Received file of " + fileSize + " bytes.").build();
		}
		return Response.status(400).build();
	}

	@POST
	@Path("/javafilemultipart")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response upload3(File theFile) {
		if (theFile != null) {
			long fileSize = theFile.length();
			System.out.println("Received file of " + fileSize + " bytes.");
			return Response.ok().entity("Received file of " + fileSize + " bytes.").build();
		}
		return Response.status(400).build();
	}

	@POST
	@Path("/formparam")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response upload4(@FormParam("param1") String param1a, @FormParam("param2") String param2a) {
		System.out.println("Received param1: " + param1a + ", param2: " + param2a);
		return Response.ok().entity("Received param1: " + param1a + ", param2: " + param2a).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	@Path("/bytearraysinglepart")
	public Response upload5(byte[] payload) throws UnsupportedEncodingException {
		if (payload != null) {
			int fileSize = payload.length;
			System.out.println("Received data of " + fileSize + " bytes, as String: " + new String(payload, "UTF-8"));
			return Response.ok().build();
		}
		return Response.status(400).build();
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Path("/bytearraymultipart")
	public Response upload6(byte[] payload) throws UnsupportedEncodingException {
		if (payload != null) {
			int fileSize = payload.length;
			System.out.println("Received data of " + fileSize + " bytes, as String: " + new String(payload, "UTF-8"));
			return Response.ok().build();
		}
		return Response.status(400).build();
	}

}
