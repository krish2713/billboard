import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { Listing } from './listing';
import { LISTINGS } from './mock-listings';
import { MessageService } from './message.service';

@Injectable()
export class ListingService {

  constructor(private http: HttpClient, private messageService: MessageService) { }
  
  private listingUrl = 'http://billboard-rest.us-east-1.elasticbeanstalk.com/api/listing/list';
  
  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add('ListingService: ' + message);
  }
  
  getListings():Observable<Listing[]> {
	  // Todo: send the message _after_ fetching the heroes
	  this.messageService.add('ListingService: fetched listings');
	  return this.http.get<Listing[]>(this.listingUrl)
  }
  
  getListingDetail(id: number): Observable<Listing> {
	  // Todo: send the message _after_ fetching the hero
	  this.messageService.add(`ListingService: fetched listing id=${id}`);
	  return of(LISTINGS.find(listing => listing.id === id));
	}
  
 

  

}
