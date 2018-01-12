import { Component, OnInit, Input } from '@angular/core';
import { Listing } from '../listing';
import { Bid } from '../bid';
import { UserInfo } from '../userinfo';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { ListingService }  from '../listing.service';

import { BidService }  from '../bid.service';

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
		  private location: Location) { }

  ngOnInit() {
	  this.getListingDetail();
	  this.bid=new Bid();
	  this.bid.userInfo=new UserInfo();
  }
  
  getListingDetail(): void {
	  const id = this.route.snapshot.paramMap.get('id');
	  this.listingService.getListingDetail(id)
	    .subscribe(listing => this.listing = listing);
	}
  
  goBack(): void {
	  this.location.back();
	}
  
  createBid(): void {
	  this.bid.listingId = this.listing.id;
	  this.bid.createdById = 'kck';
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
