import { Product } from './product';
import { User } from './user';

export interface Opinion {
  opinionId: number;
  rating: number;
  comment: string;
  login: string;
  userId: number;
  productId: number;
  title: string;
  date: string;
}
