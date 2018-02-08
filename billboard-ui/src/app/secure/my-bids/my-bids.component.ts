import { Component, OnInit } from '@angular/core';
import { Bid } from '../../model/bid';
import { BidService } from '../../service/bid.service';
import { CognitoUtil } from '../../service/cognito.service';

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
	 let username = this.cognitoUtil.getCurrentUser().getUsername();
	  this.bidService.getMyBids(username).subscribe(bids => this.bids = bids);
	}

}
