package com.altmedia.billboard.service;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.altmedia.billboard.entity.Address;
import com.altmedia.billboard.entity.Listing;
import com.fasterxml.jackson.core.JsonProcessingException;

@Ignore
public class EmailServiceTest {

    @Test
    public void testAdminIds() throws JsonProcessingException {
    
        Listing listing = new Listing();

        Address address = new Address();
        address.setAddressLine1("Addr1").setAddressLine2("Addr2").setCity("CMH").setState("OH").setCountry("US");

        listing.setTitle("Listing_email").setCreatedById("krish2713").setCreatedDate(new Date()).setType("FixedBid")
                .setAddress(address).setMinimumPeriod(12).setFromDate(new Date("01/12/2018"))
                .setToDate(new Date("31/08/2018")).setPricePerMonth(100).setSize(100);
        EmailService.getInstance().sendListingCreatedEmail(listing);
    }

}
