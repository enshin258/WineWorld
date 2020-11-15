import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductsServiceService {

  private productsGETUrl = "http://localhost:8080/products/get";
  private productsPOSTUrl = "http://localhost:8080/products/add";

  constructor(private http: HttpClient){
  }

  getAllProducts() {
    return this.http.get(this.productsGETUrl);
  }

  saveProduct(){
    return this.http.get(this.productsPOSTUrl);
  }

}
