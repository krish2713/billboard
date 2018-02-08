import { Component, OnInit, Input } from '@angular/core';
import { Bid } from '../../model/bid';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { BidService }  from '../../service/bid.service';

@Component({
  selector: 'app-bid-detail',
  templateUrl: './bid-detail.component.html',
  styleUrls: ['./bid-detail.component.css']
})
export class BidDetailComponent implements OnInit {
bid: Bid;
  constructor( private route: ActivatedRoute,
		  private bidService: BidService,
		  private location: Location) { }

  ngOnInit() {
	  this.getBidDetail();
	
  }
  getBidDetail(): void {
	  const id = this.route.snapshot.paramMap.get('id');
	  this.bidService.getBidDetail(id)
	    .subscribe(bid => this.bid = bid);
	}
  
  goBack(): void {
	  this.location.back();
	}
  
}
