import { Component, OnInit } from '@angular/core';
import { Listing } from '../listing';
import { ListingService } from '../listing.service';
import { CognitoUtil } from '../service/cognito.service';

@Component({
  selector: 'app-my-listings',
  templateUrl: './my-listings.component.html',
  styleUrls: ['./my-listings.component.css']
})
export class MyListingsComponent implements OnInit {

	listings: Listing[];

constructor(private listingService: ListingService, private cognitoUtil: CognitoUtil) { }

ngOnInit() {
	  this.getMyListings();
}
  
  delete(listing: Listing): void {
	  this.listings = this.listings.filter(h => h !== listing);
	  this.listingService.deleteListing(listing).subscribe();
	}
  
  getMyListings(): void {
	  let email = this.cognitoUtil.getCurrentUser().getUsername();
	  this.listingService.getMyListings(email).subscribe(listings => this.listings = listings);
	}

}
