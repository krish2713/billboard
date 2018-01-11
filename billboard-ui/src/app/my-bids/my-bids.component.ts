import { Component, OnInit } from '@angular/core';
import { Bid } from '../bid';
import { BidService } from '../bid.service';

@Component({
  selector: 'app-my-bids',
  templateUrl: './my-bids.component.html',
  styleUrls: ['./my-bids.component.css']
})
export class MyBidsComponent implements OnInit {
	bids: Bid[];

constructor(private bidService: BidService) { }

ngOnInit() {
	  this.getMyBids();
}
  
getMyBids(): void {
	  this.bidService.getMyBids().subscribe(bids => this.bids = bids);
	}

}
