import { OrderPosition } from './order_position';
import { Product } from './product';

export interface Order {
  id: number;
  orderDate: string;
  orderAddress: string;
  orderCity: string;
  orderPostalCode: string;
  orderTotalCost: number;
  orderPositions: OrderPosition[];
}
