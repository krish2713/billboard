import { Component, OnInit, Input } from '@angular/core';
import { Listing } from '../listing';
import { Address } from '../address';
import { Location } from '@angular/common';
import { ListingService } from '../listing.service';

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
			  private location: Location) { }

  ngOnInit() {
	  this.listing = new Listing();
	  this.listing.address = new Address();
	  this.listing.createdById = 'kck';
	    }
  
  create(): void {
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
