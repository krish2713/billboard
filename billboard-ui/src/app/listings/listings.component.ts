import { Component, OnInit } from '@angular/core';
import { Listing } from '../listing';
import { ListingService } from '../listing.service';


@Component({
  selector: 'app-listings',
  templateUrl: './listings.component.html',
  styleUrls: ['./listings.component.css']
})
export class ListingsComponent implements OnInit {

  listings: Listing[];

  constructor(private listingService: ListingService) { }

  ngOnInit() {
	  this.getListings();
  }
  
  

 getListings(): void {
	  this.listingService.getListings().subscribe(listings => this.listings = listings);
	}

}
