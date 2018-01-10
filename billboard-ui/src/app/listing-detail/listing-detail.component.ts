import { Component, OnInit, Input } from '@angular/core';
import { Listing } from '../listing';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { ListingService }  from '../listing.service';

@Component({
  selector: 'app-listing-detail',
  templateUrl: './listing-detail.component.html',
  styleUrls: ['./listing-detail.component.css']
})
export class ListingDetailComponent implements OnInit {
@Input() listing: Listing;

  constructor( private route: ActivatedRoute,
		  private listingService: ListingService,
		  private location: Location) { }

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
  
  


}
