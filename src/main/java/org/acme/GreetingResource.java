package org.acme;

import io.vertx.core.Vertx;
import io.vertx.core.file.FileSystem;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    private Vertx vertx;

    public GreetingResource(Vertx vertx)
    {
        this.vertx = vertx;
    }
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        FileSystem fsVertx = vertx.fileSystem();
        fsVertx.readDir("rakesh").andThen( entry -> {
            entry.result().forEach( e -> fsVertx.readFile(e).andThen(result -> System.out.println(result.result())));
        });
        return "Hello from RESTEasy Reactive";
    }
}