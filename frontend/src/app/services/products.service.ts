import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';
import { ProductMiniature } from '../models/product_miniature';

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

  getProduct(productId: number) {
    return this.http.get(this.getProductUrl + productId.toString());
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
        imageUrl: 'http://placehold.it/400x250/000/fff',
      };
      productstMiniatures.push(productMiniature);
    }
    return productstMiniatures;
  }
}