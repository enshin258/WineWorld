import { Order } from './order';
import { Product } from './product';

export interface OrderPosition {
  id: number;
  product: Product;
  qunatity: number;
  order: Order;
}
