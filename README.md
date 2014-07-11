# Swagger Doclet 

A JavaDoc Doclet that can be used to generate a Swagger resource listing suitable for feeding to
swagger-ui. Forked from https://github.com/ryankennedy/swagger-jaxrs-doclet to add support for swagger 1.2 and includes various fixes described below. This is used as a basis for the Carma API https://api-dev.car.ma/apidoc/ref/index.html Note you can win $1,000,000 using this API see http://carmacarpool.com/prize

## Usage

To use the Swagger Doclet in your Maven project, add the following to your POM file.

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>…</groupId>
    <artifactId>…</artifactId>
    <version>…</version>
    
    <dependencies>
        …
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-javadoc-plugin</artifactId>
                <version>2.9.1</version>
                <executions>
                    <execution>
                        <id>generate-service-docs</id>
                        <phase>generate-resources</phase>
                        <configuration>
                            <doclet>com.hypnoticocelot.jaxrs.doclet.ServiceDoclet</doclet>
                            <docletArtifact>
                                <groupId>com.hypnoticocelot</groupId>
                                <artifactId>jaxrs-doclet</artifactId>
                                <version>0.0.4_carma-SNAPSHOT</version>
                            </docletArtifact>
                            <reportOutputDirectory>${project.build.outputDirectory}</reportOutputDirectory>
                            <useStandardDocletOptions>false</useStandardDocletOptions>
                            <additionalparam>-apiVersion 1 -docBasePath /apidocs -apiBasePath /</additionalparam>
                        </configuration>
                        <goals>
                            <goal>javadoc</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
</xml>

```

## Supported Javadoc Tags and Annotations

<table>
	<tr><td>Tag</td><td>Purpose</td><td>Applies To</td><td>Aliases</td></tr>
	<tr><td>hidden</td><td>Use to exclude a resource operation, model field or method from the generated documentation</td><td>operations, model methods & fields</td><td>exclude, hide</td></tr>
	<tr><td>responseMessage</td><td>Use to describe each success or response status returned from an operation. It is made up of a numeric status code, a description an an optional response model specifically for this error.<br><br>For example:<br><br>
	@responseMessage 404 not found<br><br>
	@responseMessage 404 not found `fixtures.responsemessages.Response1<br><br>
	NOTE: The optional response model class is added after a backtick as in the example above. 
	</td><td>operations</td><td>status, errorResponse, successResponse, errorCode, successCode</td></tr>
	<tr><td>responseType</td><td>If you want the documented response model class to be different to the one in the java method signature you can use this to override it. This is useful to replace the Jax Rs response class with a particular entity type that may be returned. For example: 
	<p>
	<code><pre>
	/**
	 * @responseType fixtures.responsemodel.Response2
	 */
	@SuppressWarnings("javadoc")
	@GET
	public Response responseDefinedViaTag() {
		return Response.ok().entity(new Response2()).build();
	}
	</pre></code></p>In the above method the response model documented will be fixtures.responsemodel.Response2
	<br><br>
	</td><td>operations</td><td>outputType</td></tr>
	<tr><td>inputType</td><td>If you want to have a different class type used as the model for a body parameter then you can use this. For example: 
	<p>
	<code><pre>
	/**
	 * @inputType fixtures.inputtype.Data2
	 */
	@POST
	@Path("/postData2b")
	public Response postData2b(@HeaderParam("X-forwarded-for") String xFwd, @QueryParam("p1") int p1, Data1 data) {
		return Response.ok().build();
	}
	</pre></code></p>In the above method the body parameter model documented will be fixtures.inputtype.Data2
	</td><td>PUT/POST operations</td><td>bodyType</td></tr>
	<tr><td>defaultErrorType</td><td>If error responses result in an error entity and you want to document this without having to put in each error responseMessage you can set this either on the operation method or the resource classes javadoc.</td><td>operations,resource classes</td><td></td></tr>
	<tr><td>description</td><td>For model properties this is used for the field description. For operations this is used for the notes. For operations if you do not use this then the notes will be taken from the commment text minus the first sentences of the javadoc.</td><td>operations, model methods</td><td>comment, return - (only for model methods)</td></tr>
	<tr><td>summary</td><td>This is used for the summary of the operation. If you do not use this then the summary will be taken from the first sentences of the javadoc.</td><td>operations</td><td>endpointName</td></tr>
	<tr><td>min</td><td>Defines a minimum value for a model field.</td><td>model fields and methods</td><td>minimum</td></tr>
	<tr><td>max</td><td>Defines a maximum value for a model field.</td><td>model fields and methods</td><td>maximum</td></tr>
	
	
	
</table>

TODO: complete

## Doclet Options

TODO: complete

## Example

An example project using Dropwizard is included in `jaxrs-doclet-sample-dropwizard`. To get it running, run the following commands.

```
$ cd jaxrs-doclet-sample-dropwizard
$ mvn package
$ java -jar target/jaxrs-doclet-sample-dropwizard-0.0.4_carma-SNAPSHOT.jar server sample.yml
```

The example server should be running on port 8080:

```
$ curl localhost:8080/apidocs/service.json
{
  "apiVersion" : "1",
  "basePath" : "/apidocs/",
  "apis" : [ {
    "path" : "/Auth.{format}",
    "description" : ""
  }, {
    "path" : "/HttpServletRequest.{format}",
    "description" : ""
  }, {
    "path" : "/ModelResource_modelid.{format}",
    "description" : ""
  }, {
    "path" : "/Recursive.{format}",
    "description" : ""
  }, {
    "path" : "/Response.{format}",
    "description" : ""
  }, {
    "path" : "/greetings_name.{format}",
    "description" : ""
  } ],
  "swaggerVersion" : "1.2"
}
$
```

## Override Swagger UI

To override the swagger ui included with the doclet, create your own swagger-ui.zip file and add a swaggerUiZipPath to the additionalparam attribute in the pom file.

```
<additionalparam>-apiVersion 1 -docBasePath /apidocs -apiBasePath / -swaggerUiZipPath ../../../src/main/resources/swagger-ui.zip</additionalparam>
```

## New Features/Fixes in this fork:

### Fixes Added or Merged from https://github.com/ryankennedy/swagger-jaxrs-doclet

Issue 73 Support old jackson annotations

Issue 72 (add PUT params to model)  

Issue 71 (return type overriding via doclet tags) 

Issue 70 support parameterized return types and typesToTreatAsOpaque option

Issue 65 Support header params

Issue 64 adding short and char to primitives

Issue 60 various issues such as XmlAccessor apart from the Map value extraction part

Issue 59 resource inheritance

Issue 58 Swagger ui zip path

Issue 52 Model inheritance

Issue 44 Produces/Consumes




### Features

#### Full 1.2 swagger spec support including: 

Authorizations

Info

New data types and formats

Response Messages

Response Message Model

Updated Allowable Values

Produces/Consumes

Model field descriptions

#### Other features:

Support resource operations being spread across multiple resource classes

Support custom/override put/post body model type

support exclude resources/fields (via exclude tags and also via deprecation annotations/tags)













