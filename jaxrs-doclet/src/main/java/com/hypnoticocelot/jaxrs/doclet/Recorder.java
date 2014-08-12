package com.hypnoticocelot.jaxrs.doclet;

import java.io.File;
import java.io.IOException;

import com.hypnoticocelot.jaxrs.doclet.model.ApiDeclaration;
import com.hypnoticocelot.jaxrs.doclet.model.ResourceListing;

public interface Recorder {

	void record(File file, ResourceListing listing) throws IOException;

	void record(File file, ApiDeclaration declaration) throws IOException;

}
