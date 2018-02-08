package com.altmedia.billboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.altmedia.billboard.entity.Bid;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

public class BidService {
    private DynamoDBMapper mapper;

    private static final BidService instance = new BidService();

    public static BidService getInstance() {
        return instance;
    }

    private BidService() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
        mapper = new DynamoDBMapper(client);
    }

    public void create(Bid bid) {
        mapper.save(bid);
    }

    public Bid retrieve(String bidId) {
        return mapper.load(Bid.class, bidId);
    }

    public void update(Bid bid) {

        mapper.save(bid);
    }

    public void delete(String bidId) {
        Bid bid = new Bid();
        bid.setId(bidId);
        mapper.delete(bid);
    }

    public List<Bid> getAllBidsForListing(String listingId) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(listingId));
        DynamoDBScanExpression queryExpression = new DynamoDBScanExpression().withFilterExpression("ListingId = :val1")
                .withExpressionAttributeValues(eav);
        return mapper.scan(Bid.class, queryExpression);

    }

    public List<Bid> getAllBidsForUserId(String userId) {
        Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
        eav.put(":val1", new AttributeValue().withS(userId));
        DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
                .withFilterExpression("CreatedById = :val1").withExpressionAttributeValues(eav);
        return mapper.scan(Bid.class, queryExpression);

    }
}
