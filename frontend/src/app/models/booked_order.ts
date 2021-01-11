import { BookedOrderPosition } from "./booked_order_position";

export interface BookedOrder{
  addressLineOne: string,
  addressLineTwo: string,
  city: string,
  countryCode: string,
  date: string,
  email: string,
  login: string,
  orderId: number,
  firstOrderPosition: BookedOrderPosition,
  orderPositionResponses: BookedOrderPosition[],
  phoneNumber: string,
  postalCode: string,
  status: string,
  totalCost: number,
  userId: number
}