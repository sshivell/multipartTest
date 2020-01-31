package io.openliberty.guides.formdata;

import io.openliberty.guides.formdata.client.FormdataClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;


@RequestScoped
@Path("/test")
public class FormdataResource {
    @Inject
    @RestClient
    private FormdataClient formdataClient;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloworld() {
        return "{\"name\":\"helloworld\"}";
    }

    @POST
    @Path("request")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String sendRequest(
            @FormParam("name") String name,
            @FormParam("value") String value,
            @FormParam("file") File file) {
        String requestContentString = "Name: [ " + name + " ] Value: [ " + value + " ]  FilePath: [ " + file.getPath() + " ]";
        System.out.println(requestContentString); //Lazy Programming
        return formdataClient.respondToRequest(name, value, file);
    }

    @POST
    @Path("requestwrong")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String sendRequestWrong(
            @FormParam("name") String name,
            @FormParam("value") String value,
            @FormParam("file") File file) {
        String requestContentString = "Name: [ " + name + " ] Value: [ " + value + " ]  FilePath: [ " + file.getPath() + " ]";
        System.out.println(requestContentString); //Lazy Programming
        return formdataClient.respondToRequestWrong(name, value, file);
    }

    @POST
    @Path("respond")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String respondTo(
            @FormParam("name") String name,
            @FormParam("value") String value,
            @FormParam("file") File file) {
        String returnString = "Name: [ " + name + " ] Value: [ " + value + " ]  FilePath: [ " + file.getPath() + " ]";
        System.out.println(returnString); //Lazy Programming
        return returnString;
    }

    @POST
    @Path("respondwrong")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String respondToWrongHeader(
            @FormParam("name") String name,
            @FormParam("value") String value,
            @FormParam("file") File file) {
        String returnString = "Name: [ " + name + " ] Value: [ " + value + " ]  FilePath: [ " + file.getPath() + " ]";
        System.out.println(returnString); //Lazy Programming
        return returnString;
    }



}
