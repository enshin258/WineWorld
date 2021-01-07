import { OrderPosition } from './order_position';

export interface Order {
  id: number;
  date: string;
  addressLineOne: string;
  addressLineTwo: string;
  city: string;
  postalCode: string;
  countryCode: string;
  totalCost: number;
  phoneNumber: number;
  status: string;
  userId: number;
  orderPositions: OrderPosition[];
}
