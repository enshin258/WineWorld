import {OrderPositionRequest} from "./order_position_request";

export interface OrderRequest {
  date: Date;
  address: string;
  city: string;
  postalCode: string;
  totalCost: number;
  orderPositionRequests: OrderPositionRequest[];
}
