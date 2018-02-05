import { Component, OnInit } from '@angular/core';
import { Bid } from '../bid';
import { BidService } from '../bid.service';
import { CognitoUtil } from '../service/cognito.service';

@Component({
  selector: 'app-my-bids',
  templateUrl: './my-bids.component.html',
  styleUrls: ['./my-bids.component.css']
})
export class MyBidsComponent implements OnInit {
	bids: Bid[];

constructor(private bidService: BidService, private cognitoUtil: CognitoUtil) { }

ngOnInit() {
	  this.getMyBids();
}
  
getMyBids(): void {
	  let email = this.cognitoUtil.getCurrentUser().getUsername();
	  this.bidService.getMyBids(email).subscribe(bids => this.bids = bids);
	}

}
