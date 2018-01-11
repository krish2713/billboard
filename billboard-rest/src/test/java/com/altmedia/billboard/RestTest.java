package com.altmedia.billboard;

import java.io.File;
import java.util.Date;

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

import com.altmedia.billboard.entity.Address;
import com.altmedia.billboard.entity.Listing;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestTest {

    @Test
    public void testGetIt() throws Exception {

        final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
        WebTarget t = client.target("http://localhost:8090/api").path("listing");

        FileDataBodyPart filePart = new FileDataBodyPart("images", new File(
                "/home/kck29/repos/billboard/billboard-rest/src/test/resources/img-1.jpg"));

        Listing listing = new Listing();

        Address address = new Address();
        address.setAddressLine1("Addr1").setAddressLine2("Addr2").setCity("CMH").setState("OH").setCountry("US");

        listing.setTitle("Listing5").setCreatedById("kck").setCreatedDate(new Date()).setType("FixedBid").setAddress(address)
                .setMinimumPeriod(12).setFromDate(new Date("01/12/2018")).setToDate(new Date("31/08/2018"))
                .setPricePerMonth(100).setSize(100);

        ObjectMapper mapper = new ObjectMapper();
        String listingJson = mapper.writeValueAsString(listing);

        MultiPart multipartEntity = new FormDataMultiPart().field("listing", listingJson,
                                                                  MediaType.APPLICATION_JSON_TYPE).bodyPart(filePart);

        Response response = t.request().post(Entity.entity(multipartEntity, multipartEntity.getMediaType()));
        System.out.println(response.getStatus());
        System.out.println(response.readEntity(String.class));

        response.close();
    }

}
