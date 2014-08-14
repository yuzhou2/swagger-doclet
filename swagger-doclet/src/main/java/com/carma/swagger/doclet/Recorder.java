package com.carma.swagger.doclet;

import java.io.File;
import java.io.IOException;

import com.carma.swagger.doclet.model.ApiDeclaration;
import com.carma.swagger.doclet.model.ResourceListing;

public interface Recorder {

	void record(File file, ResourceListing listing) throws IOException;

	void record(File file, ApiDeclaration declaration) throws IOException;

}
