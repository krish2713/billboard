package com.altmedia.billboard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.altmedia.billboard.entity.Bid;
import com.altmedia.billboard.entity.UserInfo;
import com.altmedia.billboard.service.BidService;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.KeyType;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ScalarAttributeType;

@Ignore
public class BidCRUDTest {

    private static DynamoDB dynamoDB;
    private static String tableName = "Bid";
    private BidService service = new BidService();

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
        Bid bid = new Bid();
        UserInfo info = new UserInfo();
        info.setFirstName("fn").setLastName("ln").setEmailAddress("a@b.c").setPhone("444");
        bid.setType("AuctionBid").setCreatedById("kck").setCreatedDate(new Date()).setListingId("124").setPriority(1)
                .setUserInfo(info);

        // Save the item (book).
        service.create(bid);

        List<Bid> bids = service.getAllBidsForListing("124");
        Assert.assertEquals(1, bids.size());

        String bidId = bids.get(0).getId();

        // Retrieve the item.
        Bid itemRetrieved = service.retrieve(bidId);
        System.out.println("Item retrieved:");
        System.out.println(itemRetrieved);
        Assert.assertEquals(itemRetrieved.getCreatedById(), "kck");

        // Update the item.
        itemRetrieved.setModifiedById("vxn");
        service.update(itemRetrieved);

        // Retrieve the updated item.
        Bid updatedItem = service.retrieve(bidId);

        Assert.assertEquals(updatedItem.getModifiedById(), "vxn");

        // // Delete the item.
        service.delete(bidId);
        //
        // // Try to retrieve deleted item.
        Bid deletedItem = service.retrieve(bidId);
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
