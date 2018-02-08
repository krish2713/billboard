import { Component, OnInit, Input } from '@angular/core';
import { Listing } from '../../model/listing';
import { Bid } from '../../model/bid';
import { UserInfo } from '../../model/userinfo';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { ListingService }  from '../../service/listing.service';

import { BidService }  from '../../service/bid.service';
import { CognitoUtil } from '../../service/cognito.service';

@Component({
  selector: 'app-listing-detail',
  templateUrl: './listing-detail.component.html',
  styleUrls: ['./listing-detail.component.css']
})
export class ListingDetailComponent implements OnInit {
listing: Listing;
bid: Bid;
currentBids: Bid[];
showPopover= false;
showBidsPopover = false;

  constructor( private route: ActivatedRoute,
		  private listingService: ListingService, private bidService: BidService,
		  private location: Location, private cognitoUtil: CognitoUtil) { }

  ngOnInit() {
	  this.getListingDetail();  
  }
  
  getListingDetail(): void {
	  const id = this.route.snapshot.paramMap.get('id');
	  this.listingService.getListingDetail(id)
	    .subscribe(listing => this.listing = listing);
	}
  
  goBack(): void {
	  this.location.back();
	}
  createNewBid(): void {
	  this.showPopover = !this.showPopover;
	  this.bid=new Bid();
	  this.bid.userInfo=new UserInfo();
	  this.bid.listingId = this.listing.id;
	  let username = this.cognitoUtil.getCurrentUser().getUsername();
	  this.bid.createdById = username;
	  
  }
  createBid(): void {
	  
	  if(this.listing.type=='Auction'){
		  this.bid.type='AuctionBid';
	  }
	  else {
		  this.bid.type='FixedBid';
	  }
	  this.bidService.createBid(this.bid)
	    .subscribe(() => this.showPopover= false);
	  
	}
  
  getBidsForListing(): void {
	  this.showBidsPopover = !this.showBidsPopover;
	  if(this.showBidsPopover){
	  this.bidService.getBidsForListing(this.listing.id)
	    .subscribe( bids => this.currentBids = bids);
	  }
	  
	}
  
 
 
}
