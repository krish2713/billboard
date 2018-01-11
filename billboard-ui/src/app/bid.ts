import { UserInfo } from './userinfo';
export class Bid {
  id: string;
  listingId: string;
  specialInstruction: string;
  priority: number;
  userInfo: UserInfo;
  createdById: string;
  createdDate: Date;
  modifiedById: string;
  modifiedDate: Date;
  bidAmount: number;
  type: string;
  title: string;    
}