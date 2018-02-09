import { Component, OnInit, Input } from '@angular/core';
import { Listing } from '../../model/listing';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { ListingService }  from '../../service/listing.service';

import { CognitoUtil } from '../../service/cognito.service';

@Component({
  selector: 'app-listing-edit',
  templateUrl: './listing-edit.component.html',
  styleUrls: ['./listing-edit.component.css']
})
export class ListingEditComponent implements OnInit {
	 listing: Listing;
     images: FileList;

	  constructor( private route: ActivatedRoute,
			  private listingService: ListingService,
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
	  
	  save(): void {
		  let username = this.cognitoUtil.getCurrentUser().getUsername();
		  this.listing.modifiedById = username;
		  this.listing.modifiedDate = new Date();
		   this.listingService.updateListing(this.listing,this.images)
		     .subscribe(() => this.goBack());
		 }
	  
	  fileChangeEvent(fileInput: any){
	      this.images = fileInput.target.files;        
	   }
		 
	  
}
