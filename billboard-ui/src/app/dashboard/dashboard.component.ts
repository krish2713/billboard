import { Component, OnInit } from '@angular/core';
import { Listing } from '../listing';
import { ListingService } from '../listing.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: [ './dashboard.component.css' ]
})
export class DashboardComponent implements OnInit {
  listings: Listing[] = [];

  constructor(private listingService: ListingService) { }

  ngOnInit() {
    this.getListings();
  }

  getListings(): void {
    this.listingService.getListings()
      .subscribe(listings => this.listings = listings.slice(-5));
    
  }
}