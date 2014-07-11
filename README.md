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
Support custum/override put/post body model type
support exclude resources/fields (via exclude tags and also via deprecation annotations/tags)












