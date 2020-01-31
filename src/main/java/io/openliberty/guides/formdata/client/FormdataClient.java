package io.openliberty.guides.formdata.client;

import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;


@RegisterRestClient(configKey = "formdataClient", baseUri = "http://localhost:9080/formdata/test")
@RegisterProvider(UnknownUriExceptionMapper.class)
public interface FormdataClient extends AutoCloseable {

@POST
@Path("/respond")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
    public String respondToRequest(
            @FormParam("name") String name,
            @FormParam("value") String value,
            @FormParam("file") File file);




///Same Client Definition as above, just goes to the other end point which
 //   @Consumes("MediaType.APPLICATION_FORM_URLENCODED")
@POST
@Path("/respondwrong")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public String respondToRequestWrong(
        @FormParam("name") String name,
        @FormParam("value") String value,
        @FormParam("file") File file);


}


