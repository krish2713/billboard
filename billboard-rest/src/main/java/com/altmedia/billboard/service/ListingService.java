package com.altmedia.billboard.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.altmedia.billboard.entity.Listing;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.S3Link;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.util.IOUtils;

public class ListingService {
	private DynamoDBMapper mapper;
	private String bucketName;

	private static final ListingService instance = new ListingService();

	public static ListingService getInstance() {
		return instance;
	}

	private ListingService() {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
		mapper = new DynamoDBMapper(client, new DefaultAWSCredentialsProviderChain());
		bucketName = System.getenv("IMAGES_BUCKET_NAME") == null ? "billboard-images-whitebid-use1"
				: System.getenv("IMAGES_BUCKET_NAME");
	}

	public void create(Listing listing) {
		mapper.save(listing);
	}

	public URL storeImageInS3(InputStream imageInput, String fileName, String listingId) throws IOException {
		try {
			S3Link s3Link = mapper.createS3Link(System.getenv("AWS_REGION"), bucketName, listingId + "/" + fileName);
			s3Link.uploadFrom(IOUtils.toByteArray(imageInput));
			return s3Link.getUrl();
		} finally {
			imageInput.close();
		}
	}

	public Listing retrieve(String listingId) {
		return mapper.load(Listing.class, listingId);

	}

	public void update(Listing listing) {
		mapper.save(listing);
	}

	public void delete(String listingId) {
		Listing listing = new Listing();
		listing.setId(listingId);
		mapper.delete(listing);
	}

	public List<Listing> getAllListings() {
		return mapper.scan(Listing.class, new DynamoDBScanExpression());

	}

	public List<Listing> getListingsByUserId(String userId) {
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(userId));
		DynamoDBScanExpression queryExpression = new DynamoDBScanExpression()
				.withFilterExpression("CreatedById = :val1").withExpressionAttributeValues(eav);
		return mapper.scan(Listing.class, queryExpression);

	}

}
