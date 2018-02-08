import { Injectable } from '@angular/core';

import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { Bid } from '../model/bid';
import { MessageService } from './message.service';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable()
export class BidService {

  constructor(private http: HttpClient, private messageService: MessageService) { }
  
  private bidUrl = 'http://localhost:8080/api/bid';
  
  private log(message: string) {
    this.messageService.add('BidService: ' + message);
  }
  
  getBidsForListing(listingId: string):Observable<Bid[]> {
	  const url = `${this.bidUrl}/listing/${listingId}`;
	  this.messageService.add('BidService: fetched bids');
	  return this.http.get<Bid[]>(url).pipe( tap(bids => this.log(`fetched bids for listing "${listingId}"`)),
		      catchError(this.handleError('getBidsForListing', []))
	    );
  }
  
  getMyBids(userId: string):Observable<Bid[]> {
	  console.log(userId);
	  const url = `${this.bidUrl}/user/${userId}`;
	  this.messageService.add('BidService: fetched myBids');
	  return this.http.get<Bid[]>(url).pipe( tap(bids => this.log(`fetched mybids`)),
		      catchError(this.handleError('getMyBids', []))
	    );
  }
  
  getBidDetail(id: string): Observable<Bid> {
	  const url = `${this.bidUrl}/${id}`;
	  return this.http.get<Bid>(url).pipe(
			    tap(_ => this.log(`fetched bid id=${id}`)),
			    catchError(this.handleError<Bid>(`getBidDetail id=${id}`))
			  );
	}
  
  
 createBid (bid: Bid): Observable<Bid> {
	return this.http.post<Bid>(this.bidUrl, bid).pipe(
	    tap((bid: Bid) => this.log(`successfully created bid`)),
	    catchError(this.handleError<Bid>('createBid'))
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
