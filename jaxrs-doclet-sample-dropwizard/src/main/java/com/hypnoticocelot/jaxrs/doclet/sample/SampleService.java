package com.hypnoticocelot.jaxrs.doclet.sample;

import com.google.common.base.Optional;
import com.hypnoticocelot.jaxrs.doclet.sample.resources.AuthResource;
import com.hypnoticocelot.jaxrs.doclet.sample.resources.GreetingsResource;
import com.hypnoticocelot.jaxrs.doclet.sample.resources.HttpServletRequestResource;
import com.hypnoticocelot.jaxrs.doclet.sample.resources.ModelResource;
import com.hypnoticocelot.jaxrs.doclet.sample.resources.ParentResource;
import com.hypnoticocelot.jaxrs.doclet.sample.resources.PersonResource;
import com.hypnoticocelot.jaxrs.doclet.sample.resources.RecursiveResource;
import com.hypnoticocelot.jaxrs.doclet.sample.resources.ResponseResource;
import com.hypnoticocelot.jaxrs.doclet.sample.resources.SubResource;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.auth.Authenticator;
import com.yammer.dropwizard.auth.basic.BasicAuthProvider;
import com.yammer.dropwizard.auth.basic.BasicCredentials;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.Environment;

public class SampleService extends Service<Configuration> {

	public static void main(String[] args) throws Exception {
		new SampleService().run(args);
	}

	@Override
	public void initialize(Bootstrap<Configuration> bootstrap) {
		bootstrap.addBundle(new AssetsBundle("/apidocs", "/apidocs", "index.html"));
	}

	@Override
	public void run(Configuration configuration, Environment environment) throws Exception {
		environment.addProvider(new BasicAuthProvider<String>(new Authenticator<BasicCredentials, String>() {

			public Optional<String> authenticate(BasicCredentials basicCredentials) throws AuthenticationException {
				return Optional.of("USERNAME");
			}
		}, "AuthResource Realm"));

		environment.addResource(new AuthResource());
		environment.addResource(new GreetingsResource());
		environment.addResource(new HttpServletRequestResource());
		environment.addResource(new RecursiveResource());
		environment.addResource(new ResponseResource());
		environment.addResource(new ModelResource());
		environment.addResource(new ParentResource());
		environment.addResource(new SubResource());
		environment.addResource(new PersonResource());
	}
}
