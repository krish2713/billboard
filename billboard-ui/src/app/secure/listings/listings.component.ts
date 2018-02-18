import { Component, OnInit } from '@angular/core';
import { Listing } from '../../model/listing';
import { ListingService } from '../../service/listing.service';
import { CognitoUtil } from '../../service/cognito.service';


@Component({
  selector: 'app-listings',
  templateUrl: './listings.component.html',
  styleUrls: ['./listings.component.css']
})
export class ListingsComponent implements OnInit {

  listings: Listing[];

  constructor(private listingService: ListingService, private cognitoUtil: CognitoUtil) { }

  ngOnInit() {
	  this.getListings();
  }
  
 getListings(): void {
	  this.listingService.getListings().subscribe(listings => this.listings = listings);
	} 
 
 canEditOrDelete(userId: string){
	 let username = this.cognitoUtil.getCurrentUser().getUsername();
	 return this.cognitoUtil.getRole() =='admin'||( this.cognitoUtil.getRole() =='vendor' && userId==username);

	} 
 
 delete(listing: Listing): void {
	  this.listings = this.listings.filter(h => h !== listing);
	  this.listingService.deleteListing(listing).subscribe();
	}

}
