import { Address } from './address';

export class Listing {
  id: string;
  title: string;
  size: number;
  minimumPeriod: string;
  address: Address;
  fromDate: Date;
  toDate: Date;
  startDate: Date;
  endDate: Date;
  type:string;
  createdById:string;
  createdDate:Date;
  modifiedById:string;
  modifiedDate:Date;
  imageUrls:string[];
}