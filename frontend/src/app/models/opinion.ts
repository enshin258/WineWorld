import { Product } from './product';
import { User } from './user';

export interface Opinion {
  id: number;
  rating: number;
  comment: string;
  user: User;
  product: Product;
}
