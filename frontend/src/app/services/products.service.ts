import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Product } from '../models/product';
import { ProductMiniature } from '../models/product_miniature';
import { Location } from '../models/location';
import { Observable } from 'rxjs';
import { Count } from '../models/count';

@Injectable({
  providedIn: 'root',
})
export class ProductsService {
  private getProductsUrl = 'http://localhost:8080/products/get/all';
  private getProductsCountUrl = 'http://localhost:8080/products/get/count/';
  private getProductUrl = 'http://localhost:8080/products/get/';
  private addProductUrl = 'http://localhost:8080/products/add';
  private updateProductUrl = 'http://localhost:8080/products/update';
  private deleteProductUrl = 'http://localhost:8080/products/delete/';
  private getAllProductOpinionsUrl = 'http://localhost:8080/get/all/opinions/';
  private getProductsMiniaturesUrl = 'http://localhost:8080/products/get/mini/';
  private getCountByCategoryUrl = 'http://localhost:8080/products/get/count/';
  private getProductByNameUrl = 'http://localhost:8080/products/get/mini/by/name/';
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
