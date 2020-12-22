import { ProductMiniature } from './product_miniature';

export interface ShoppingCartPosition {
  productMiniature: ProductMiniature;
  quantity: number;
}
