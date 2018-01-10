import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { Listing } from './listing';
import { MessageService } from './message.service';
import { catchError, map, tap } from 'rxjs/operators';

const httpOptions = {
		  headers: new HttpHeaders({ 'Content-Type': 'multipart/form-data' })
};

@Injectable()
export class ListingService {

  constructor(private http: HttpClient, private messageService: MessageService) { }
  
  private listingUrl = 'http://localhost:8080/api/listing';
  
  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add('ListingService: ' + message);
  }
  
  getListings():Observable<Listing[]> {
	  const url = `${this.listingUrl}/list`;
	  this.messageService.add('ListingService: fetched listings');
	  return this.http.get<Listing[]>(url).pipe( tap(listings => this.log(`fetched listings`)),
		      catchError(this.handleError('getListings', []))
	    );
  }
  
  getMyListings():Observable<Listing[]> {
	  const url = `${this.listingUrl}/user/kck`;
	  this.messageService.add('ListingService: fetched mylistings');
	  return this.http.get<Listing[]>(url).pipe( tap(listings => this.log(`fetched mylistings`)),
		      catchError(this.handleError('getMyListings', []))
	    );
  }
  
  getListingDetail(id: string): Observable<Listing> {
	  const url = `${this.listingUrl}/${id}`;
	  return this.http.get<Listing>(url).pipe(
			    tap(_ => this.log(`fetched listing id=${id}`)),
			    catchError(this.handleError<Listing>(`getListing id=${id}`))
			  );
	}
  
  /** PUT: update the listing on the server */
  updateListing (listing: Listing): Observable<any> {
	  console.log(listing);
    return this.http.put(this.listingUrl, listing, httpOptions).pipe(
      tap(_ => this.log(`updated listing id=${listing.id}`)),
      catchError(this.handleError<any>('updateListing'))
    );
  }
  
  addListing (listing: Listing): Observable<Listing> {
	  return this.http.post<Listing>(this.listingUrl, listing, httpOptions).pipe(
	    tap((listing: Listing) => this.log(`added listing w/ id=${listing.id}`)),
	    catchError(this.handleError<Listing>('addListing'))
	  );
	}
  
  /** DELETE: delete the hero from the server */
  deleteListing (listing: Listing | number): Observable<Listing> {
    const id = typeof listing === 'number' ? listing : listing.id;
    const url = `${this.listingUrl}/${id}`;

    return this.http.delete<Listing>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted listing id=${id}`)),
      catchError(this.handleError<Listing>('deleteListing'))
    );
  }
  
  /* GET listings whose name contains search term */
  searchListings(term: string): Observable<Listing[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    const url = `${this.listingUrl}/?searchTerm=${term}`;
    return this.http.get<Listing[]>(url).pipe(
      tap(_ => this.log(`found listings matching "${term}"`)),
      catchError(this.handleError<Listing[]>('searchListings', []))
    );
  }
  
  
  /**
	 * Handle Http operation that failed. Let the app continue.
	 * 
	 * @param operation -
	 *            name of the operation that failed
	 * @param result -
	 *            optional value to return as the observable result
	 */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
   
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead
   
      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);
   
      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
  
  
  
 

  

}
