package com.altmedia.billboard;

import java.io.File;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.junit.Ignore;
import org.junit.Test;
@Ignore
public class RestTest {

    @Test
    public void testGetIt() throws Exception {

        final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        WebTarget t = client.target("http://billboard-rest.us-east-1.elasticbeanstalk.com/api").path("listing");

        FileDataBodyPart filePart = new FileDataBodyPart("images", new File(
                "/home/kck29/repos/billboard/billboard-rest/src/test/resources/test.png"));

        String empPartJson = "{\n" + "    \"createdById\": \"kckb\"\n" + "}\n" + "";

        MultiPart multipartEntity = new FormDataMultiPart().field("listing", empPartJson,
                                                                  MediaType.APPLICATION_JSON_TYPE).bodyPart(filePart);

        Response response = t.request().post(Entity.entity(multipartEntity, multipartEntity.getMediaType()));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));

        response.close();
    }

}
