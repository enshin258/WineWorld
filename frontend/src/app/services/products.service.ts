import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Product } from '../models/product';
import { ProductMiniature } from '../models/product_miniature';
import { Location } from '../models/location';
import { Observable } from 'rxjs';
import { Count } from '../models/count';
import {Add_product} from "../models/add_product";

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  private getProductsUrl = 'http://localhost:8080/products/get/all';
  private getProductsCountUrl = 'http://localhost:8080/products/get/count/';
  private getProductUrl = 'http://localhost:8080/products/get/';
  private addProductUrl = 'http://localhost:8080/products/add';
  private updateProductUrl = 'http://localhost:8080/products/update/';
  private deleteProductUrl = 'http://localhost:8080/products/delete/';
  private getAllProductOpinionsUrl = 'http://localhost:8080/get/all/opinions/';
  private getProductsMiniaturesUrl = 'http://localhost:8080/mini/get/';
  private getCountByCategoryUrl = 'http://localhost:8080/products/get/count/';
  private getProductByNameUrl = 'http://localhost:8080/mini/get/by/name/';
  private getProductCountByNameUrl = 'http://localhost:8080/products/get/count/by/name/';

  constructor(private http: HttpClient) {}

  getAllProducts() {
    return this.http.get(this.getProductsUrl);
  }

  getAllProductsCount() : Observable<Count> {
    return this.http.get<Count>(this.getProductsCountUrl);
  }

  getAllCategoryProductsCount(categoryId: number) {
    return this.http.get<Count>(this.getCountByCategoryUrl + categoryId);
  }

  getAllSearchProductsCount(searchText: string) {
    return this.http.get<Count>(this.getProductCountByNameUrl + searchText);
  }

  getProduct(productId: number): Observable<Product>{
    return this.http.get<Product>(this.getProductUrl + productId);
  }

  saveProduct(product: Add_product) {
    var formData = new FormData();
    formData.append("name", product.name);
    formData.append("price", product.price.toString());
    formData.append("picture", product.picture, product.picture.name);
    formData.append("genreId", product.genreId.toString());
    formData.append("productDescription", product.productDescription);
    formData.append("locationId", product.locationId.toString());
    formData.append("producer", product.producer);
    formData.append("alcoholLevel", product.alcoholLevel.toString());
    formData.append("year", product.year.toString());
    formData.append("volume", product.volume.toString());
    return this.http.post(this.addProductUrl, formData, {
      withCredentials: true});
  }

  updateProduct(product: Add_product, productId: number) {
    return this.http.put(this.updateProductUrl + productId.toString(), product,
    {withCredentials: true});
  }

  deleteProduct(productId: number) {
    return this.http.delete(this.deleteProductUrl + productId.toString(),
      {observe: 'response', withCredentials: true}
      );
  }

  getAllProductOpinions(productId: number) {
    return this.http.get(this.getAllProductOpinionsUrl + productId.toString());
  }

  getAllProductMiniatures(pageSize: number, pageNumber: number) : Observable<ProductMiniature[]>{
    return this.http.get<ProductMiniature[]>(this.getProductsMiniaturesUrl + pageSize + "/" + pageNumber);
  }

  getProductMiniaturesOfCategory(
    pageSize: number,
    pageNumber: number,
    categoryId: number
  ) {
    return this.http.get<ProductMiniature[]>(this.getProductsMiniaturesUrl + pageSize + "/" + pageNumber + "/" + categoryId);
  }

  getProductMiniaturesFromSearch(
    pageSize: number,
    pageNumber: number,
    searchText: string
  ) {
    return this.http.get<ProductMiniature[]>(this.getProductByNameUrl + pageSize + "/" + pageNumber + "/" + searchText);
  }
}
