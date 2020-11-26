import { OrderPosition } from './order_position';
import { Product } from './product';

export interface Order {
  id: number;
  orders: OrderPosition[];
}
