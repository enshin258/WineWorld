import { Opinion } from './opinion';

export interface Product {
  id: number;
  name: string;
  price: number;
  pictureUrl: string;
  genre: string;
  location: Location;
  producer: string;
  alcoholLevel: number;
  year: number;
  volume: number;
  opinions: Opinion[];
}
