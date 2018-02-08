import { Component, OnInit, Input } from '@angular/core';
import { Listing } from '../../model/listing';
import { Address } from '../../model/address';
import { Location } from '@angular/common';
import { ListingService } from '../../service/listing.service';
import { CognitoUtil } from '../../service/cognito.service';

@Component({
  selector: 'app-listing-create',
  templateUrl: './listing-create.component.html',
  styleUrls: ['./listing-create.component.css']
})
export class ListingCreateComponent implements OnInit {
	listing: Listing;
    images: FileList;

	constructor( 
			  private listingService: ListingService,
			  private location: Location, private cognitoUtil: CognitoUtil) { }

  ngOnInit() {
	  this.listing = new Listing();
	  this.listing.address = new Address();
	  
	    }
  
  create(): void {
	  let username = this.cognitoUtil.getCurrentUser().getUsername();
	  this.listing.createdById = username;
	   this.listingService.addListing(this.listing,this.images)
	     .subscribe(() => this.goBack());
	 }
  
  goBack(): void {
	  this.location.back();
	}
  
  fileChangeEvent(fileInput: any){
      this.images = fileInput.target.files;        
   }

}
