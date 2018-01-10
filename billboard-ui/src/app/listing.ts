import { Address } from './address';

export class Listing {
  id: string;
  title: string;
  size: number;
  address: Address;
  fromDate: Date;
  toDate: Date;
  startDate: Date;
  endDate: Date;
  type:string;
  imageUrls:string[];
}