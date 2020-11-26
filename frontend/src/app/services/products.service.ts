import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  private getProductsUrl = 'http://localhost:8080/products/get/all';
  private getProductUrl = 'http://localhost:8080/products/get/';
  private addProductUrl = 'http://localhost:8080/products/add';
  private updateProductUrl = 'http://localhost:8080/products/update';
  private deleteProductUrl = 'http://localhost:8080/products/delete/';
  private getAllProductOpinionsUrl = 'http://localhost:8080/get/all/opinions/';

  constructor(private http: HttpClient) {}

  getAllProducts() {
    return this.http.get(this.getProductsUrl);
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
}
