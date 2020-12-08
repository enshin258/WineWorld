import { OrderPosition } from './order_position';
import { Product } from './product';

export interface Order {
  id: number;
  orderDate: Date;
  orderAddress: string;
  orderCity: string;
  orderPostalCode: string;
  orderTotalCost: number;
  orderPositions: OrderPosition[];
}
