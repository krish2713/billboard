package com.altmedia.billboard.entity;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "Listing")
public class Listing {
    private String id;
    private Address address;
    private int size;
    private List<URL> imageUrls = new ArrayList<URL>();
    private Date fromDate;
    private Date toDate;
    private int minimumPeriod;
    private int pricePerMonth;
    private String createdById;
    private String modifiedById;
    private Date createdDate;
    private Date modifiedDate;
    private Date startDate;
    private Date endDate;
    private String type;

    // Partition key
    @DynamoDBHashKey(attributeName = "Id")
    public String getId() {
        return id;
    }

    @DynamoDBAttribute(attributeName = "FromDate")
    Date getFromDate() {
        return fromDate;
    }

    @DynamoDBAttribute(attributeName = "ToDate")
    public Date getToDate() {
        return toDate;
    }

    @DynamoDBAttribute(attributeName = "MinimumPeriod")
    public int getMinimumPeriod() {
        return minimumPeriod;
    }

    @DynamoDBAttribute(attributeName = "PricePerMonth")
    public int getPricePerMonth() {
        return pricePerMonth;
    }

    @DynamoDBAttribute(attributeName = "CreatedById")
    public String getCreatedById() {
        return createdById;
    }

    @DynamoDBAttribute(attributeName = "ModifiedById")
    public String getModifiedById() {
        return modifiedById;
    }

    @DynamoDBAttribute(attributeName = "CreatedDate")
    public Date getCreatedDate() {
        return createdDate;
    }

    @DynamoDBAttribute(attributeName = "ModifiedDate")
    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Listing setId(String id) {
        this.id = id;
        return this;
    }

    public Listing setFromDate(Date fromDate) {
        this.fromDate = fromDate;
        return this;
    }

    public Listing setToDate(Date toDate) {
        this.toDate = toDate;
        return this;
    }

    public Listing setMinimumPeriod(int minimumPeriod) {
        this.minimumPeriod = minimumPeriod;
        return this;
    }

    public Listing setPricePerMonth(int pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
        return this;
    }

    public Listing setCreatedById(String createdById) {
        this.createdById = createdById;
        return this;
    }

    public Listing setModifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
        return this;
    }

    public Listing setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Listing setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    @DynamoDBAttribute(attributeName = "Type")
    public String getType() {
        return type;
    }

    public Listing setType(String type) {
        this.type = type;
        return this;
    }

    @DynamoDBAttribute(attributeName = "StartDate")
    public Date getStartDate() {
        return startDate;
    }

    @DynamoDBAttribute(attributeName = "EndDate")
    public Date getEndDate() {
        return endDate;
    }

    public Listing setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Listing setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    @DynamoDBAttribute(attributeName = "Address")
    public Address getAddress() {
        return address;
    }

    @DynamoDBAttribute(attributeName = "Size")
    public int getSize() {
        return size;
    }

    @DynamoDBAttribute(attributeName = "ImageUrls")
    public List<URL> getImageUrls() {
        return imageUrls;
    }

    public Listing setAddress(Address address) {
        this.address = address;
        return this;
    }

    public Listing setSize(int size) {
        this.size = size;
        return this;
    }

    public Listing setImageUrls(List<URL> imageUrls) {
        this.imageUrls = imageUrls;
        return this;
    }

}
