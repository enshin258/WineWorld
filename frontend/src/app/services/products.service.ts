import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';
import { ProductMiniature } from '../models/product_miniature';
import { Location } from '../models/location';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  private getProductsUrl = 'http://localhost:8080/products/get/all';
  private getProductsCountUrl = 'http://localhost:8080/products/get/all/count';
  private getProductUrl = 'http://localhost:8080/products/get/';
  private addProductUrl = 'http://localhost:8080/products/add';
  private updateProductUrl = 'http://localhost:8080/products/update';
  private deleteProductUrl = 'http://localhost:8080/products/delete/';
  private getAllProductOpinionsUrl = 'http://localhost:8080/get/all/opinions/';

  constructor(private http: HttpClient) {}

  getAllProducts() {
    return this.http.get(this.getProductsUrl);
  }

  getAllProductsCount() {
    // return this.http.get<number>(this.getProductsCountUrl);
    return 96;
  }

  getAllCategoryProductsCount(categoryId: number) {
    return 36;
  }

  getAllSearchProductsCount(searchText: string) {
    return 29;
  }

  getProduct(productId: number) {
    // return this.http.get(this.getProductUrl + productId.toString());
    var location: Location = {
      id: productId,
      latitude: 52.63,
      longitude: 20.35,
      description: 'Super winnica',
      country: 'Poland',
      name: 'Winnica Lidla',
    };
    var product: Product = {
      id: productId,
      name: 'DummyName',
      price: 19.99,
      pictureUrl: 'https://picsum.photos/400/400?random=' + productId,
      genre: 'Szato de Jabol',
      location: location,
      producer: 'Winniczanka sp. z.o o',
      alcoholLevel: 20,
      year: 2020,
      volume: 0.5,
      opinions: [],
      description:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec pulvinar' +
        'est sit amet turpis ultricies vehicula. Sed interdum posuere' +
        'consectetur. Sed eget malesuada nibh. Morbi malesuada semper justo,' +
        'semper rutrum risus scelerisque et. Fusce facilisis mauris facilisis' +
        'sapien sollicitudin, rutrum tristique elit tincidunt.',
    };
    return product;
  }

  saveProduct(product: Product) {
    return this.http.post(this.addProductUrl, product);
  }

  updateProduct(product: Product) {
    return this.http.patch(this.updateProductUrl, product);
  }

  deleteProduct(productId: number) {
    return this.http.delete(this.deleteProductUrl + productId.toString);
  }

  getAllProductOpinions(productId: number) {
    return this.http.get(this.getAllProductOpinionsUrl + productId.toString());
  }

  getAllProductMiniatures(pageSize: number, pageNumber: number) {
    const shortDescr =
      'Product description... Lorem ipsum dolor sit amet, consectetuer' +
      'adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet' +
      'dolore magna aliquam erat volutpat.';

    let productstMiniatures: ProductMiniature[] = [];
    for (var i = (pageNumber - 1) * pageSize; i < pageSize * pageNumber; i++) {
      let productMiniature = {
        id: i,
        name: 'DummyName',
        shortDescription: shortDescr,
        price: 19.99,
        imageUrl: 'https://picsum.photos/400/400?random=' + i,
      };
      productstMiniatures.push(productMiniature);
    }
    return productstMiniatures;
  }

  getProductMiniaturesOfCategory(
    pageSize: number,
    pageNumber: number,
    categoryId: number
  ) {
    return this.getAllProductMiniatures(pageSize, pageNumber);
  }

  getProductMiniaturesFromSearch(
    pageSize: number,
    pageNumber: number,
    searchText: string
  ) {
    return this.getAllProductMiniatures(pageSize, pageNumber);
  }
}
