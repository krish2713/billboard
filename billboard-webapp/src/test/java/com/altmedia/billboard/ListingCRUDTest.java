package com.altmedia.billboard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.altmedia.billboard.entity.Address;
import com.altmedia.billboard.entity.Listing;
import com.altmedia.billboard.service.ListingService;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

public class ListingCRUDTest {

    private static DynamoDB dynamoDB;
    private static String tableName = "Listing";
    private ListingService service = new ListingService();

    @BeforeClass
    public static void createTable() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        dynamoDB = new DynamoDB(client);
        try {
            System.out.println("Attempting to create table; please wait...");
            ArrayList<AttributeDefinition> attributeDefinitions = new ArrayList<AttributeDefinition>();
            attributeDefinitions.add(new AttributeDefinition().withAttributeName("Id")
                    .withAttributeType(ScalarAttributeType.S));
            Table table = dynamoDB.createTable(tableName, Arrays.asList(new KeySchemaElement("Id", KeyType.HASH)),
                                               attributeDefinitions, new ProvisionedThroughput(10L, 10L));
            table.waitForActive();
            System.out.println("Success.  Table status: " + table.getDescription().getTableStatus());

        }
        catch (Exception e) {
            System.err.println("Unable to create table: ");
            System.err.println(e.getMessage());
            Assert.fail();
        }

    }

    @Test
    public void testCRUDOperations() throws ParseException {

        Listing listing = new Listing();
        listing.setId("601").setCreatedById("kck").setCreatedDate(new Date()).setType("Auction")
                .setAddress(new Address()).setMinimumPeriod(12).setFromDate(new Date("01/12/2017"))
                .setToDate(new Date("31/12/2017")).setPricePerMonth(100).setSize(100);

        // Save the item (book).
        service.create(listing);

        // Retrieve the item.
        Listing itemRetrieved = service.retrieve("601");
        System.out.println("Item retrieved:");
        System.out.println(itemRetrieved);
        Assert.assertEquals(itemRetrieved.getCreatedById(), "kck");

        // Update the item.
        itemRetrieved.setModifiedById("vxn");
        service.update(itemRetrieved);

        // Retrieve the updated item.
        Listing updatedItem = service.retrieve("601");

        Assert.assertEquals(updatedItem.getModifiedById(), "vxn");

        Listing listingByUserId = service.getListingsByUserId("kck").get(0);

        Assert.assertNotNull(listingByUserId);

        // Delete the item.
        service.delete("601");

        // Try to retrieve deleted item.
        Listing deletedItem = service.retrieve("601");
        Assert.assertNull(deletedItem);

    }

    @AfterClass
    public static void deleteTable() {
        try {
            System.out.println("Attempting to delete table; please wait...");
            Table table = dynamoDB.getTable(tableName);
            table.delete();
            table.waitForDelete();
        }
        catch (Exception e) {
            System.err.println("Unable to delete table: ");
            System.err.println(e.getMessage());
            Assert.fail();
        }

    }
}