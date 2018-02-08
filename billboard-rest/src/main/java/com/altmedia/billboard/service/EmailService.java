package com.altmedia.billboard.service;

import java.util.List;

import com.altmedia.billboard.entity.Bid;
import com.altmedia.billboard.entity.Listing;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EmailService {

    private static final String LISTING_CREATED_BODY = "Here are the listing details!";
    private static final String LISTING_CREATED_SUBJECT = "listing created!";

    private static final String LISTING_UPDATED_BODY = "Here are the listing details!";
    private static final String LISTING_UPDATED_SUBJECT = "listing updated!";

    private static final String LISTING_DELETED_BODY = "Here are the listing details!";
    private static final String LISTING_DELETED_SUBJECT = "listing deleted!";

    private static final String BID_CREATED_BODY = "Here are the bid details!";
    private static final String BID_CREATED_SUBJECT = "bid created!";

    private static final String BID_UPDATED_BODY = "Here are the bid details!";
    private static final String BID_UPDATED_SUBJECT = "bid updated!";

    private static final String BID_DELETED_BODY = "Here are the bid details!";
    private static final String BID_DELETED_SUBJECT = "bid deleted!";

    private CredentialService credentialService;

    private AmazonSimpleEmailService client;

    private static final String FROM = "no-reply@altmedia.com";

    private ObjectMapper mapper;

    private static final EmailService instance = new EmailService();

    public static EmailService getInstance() {
        return instance;
    }

    private EmailService() {
        credentialService = CredentialService.getInstance();
        mapper = new ObjectMapper();
        client = AmazonSimpleEmailServiceClientBuilder.defaultClient();
    }

    public void sendListingCreatedEmail(Listing listing) throws JsonProcessingException {

        List<String> toEmailAddress = credentialService.getAdminEmailIds();
        String vendorEmail = credentialService.getVendorEmailId(listing.getCreatedById());
        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(vendorEmail).withBccAddresses(toEmailAddress);

        // Create the subject and body of the message.
        Content subject = new Content().withData(LISTING_CREATED_SUBJECT);
        Content textBody = new Content().withData(LISTING_CREATED_BODY
                + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listing));
        Body body = new Body().withText(textBody);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination)
                .withMessage(message);
        client.sendEmail(request);

    }

    public void sendListingDeletedEmail(Listing listing) throws JsonProcessingException {

        List<String> toEmailAddress = credentialService.getAdminEmailIds();
        String vendorEmail = credentialService.getVendorEmailId(listing.getCreatedById());
        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(vendorEmail).withBccAddresses(toEmailAddress);

        // Create the subject and body of the message.
        Content subject = new Content().withData(LISTING_DELETED_SUBJECT);
        Content textBody = new Content().withData(LISTING_DELETED_BODY
                + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listing));
        Body body = new Body().withText(textBody);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination)
                .withMessage(message);
        client.sendEmail(request);

    }

    public void sendListingUpdatedEmail(Listing listing) throws JsonProcessingException {
        List<String> toEmailAddress = credentialService.getAdminEmailIds();
        String vendorEmail = credentialService.getVendorEmailId(listing.getCreatedById());
        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(vendorEmail).withBccAddresses(toEmailAddress);

        // Create the subject and body of the message.
        Content subject = new Content().withData(LISTING_UPDATED_SUBJECT);
        Content textBody = new Content().withData(LISTING_UPDATED_BODY
                + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(listing));
        Body body = new Body().withText(textBody);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination)
                .withMessage(message);
        client.sendEmail(request);
    }

    public void sendBidCreatedEmail(Bid bid, String vendorId) throws JsonProcessingException {
        List<String> toEmailAddress = credentialService.getAdminEmailIds();
        String vendorEmail = credentialService.getVendorEmailId(vendorId);
        String bidderEmail = credentialService.getVendorEmailId(bid.getCreatedById());

        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(bidderEmail).withCcAddresses(vendorEmail)
                .withBccAddresses(toEmailAddress);

        // Create the subject and body of the message.
        Content subject = new Content().withData(BID_CREATED_SUBJECT);
        Content textBody = new Content().withData(BID_CREATED_BODY
                + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bid));
        Body body = new Body().withText(textBody);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination)
                .withMessage(message);
        client.sendEmail(request);
    }

    public void sendBidUpdatedEmail(Bid bid, String vendorId) throws JsonProcessingException {
        List<String> toEmailAddress = credentialService.getAdminEmailIds();
        String vendorEmail = credentialService.getVendorEmailId(vendorId);
        String bidderEmail = credentialService.getVendorEmailId(bid.getCreatedById());

        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(bidderEmail).withCcAddresses(vendorEmail)
                .withBccAddresses(toEmailAddress);

        // Create the subject and body of the message.
        Content subject = new Content().withData(BID_UPDATED_SUBJECT);
        Content textBody = new Content().withData(BID_UPDATED_BODY
                + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bid));
        Body body = new Body().withText(textBody);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination)
                .withMessage(message);
        client.sendEmail(request);
    }

    public void sendBidDeletedEmail(Bid bid, String vendorId) throws JsonProcessingException {
        List<String> toEmailAddress = credentialService.getAdminEmailIds();
        String vendorEmail = credentialService.getVendorEmailId(vendorId);
        String bidderEmail = credentialService.getVendorEmailId(bid.getCreatedById());

        // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(bidderEmail).withCcAddresses(vendorEmail)
                .withBccAddresses(toEmailAddress);

        // Create the subject and body of the message.
        Content subject = new Content().withData(BID_DELETED_SUBJECT);
        Content textBody = new Content().withData(BID_DELETED_BODY
                + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bid));
        Body body = new Body().withText(textBody);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination)
                .withMessage(message);
        client.sendEmail(request);
    }

}
