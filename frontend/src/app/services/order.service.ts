import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private getOrderUrl = 'http://localhost:8080/order/get/';
  private getUserOrdersUrl = 'http://localhost:8080/order/get/all/';
  private getOrderPositionsUrl =
    'http://localhost:8080/order/position/get/all/';

  // dictionary with productId as a key and quantity as a value
  private cart: { [productId: number]: number };

  constructor(private http: HttpClient) {
    this.cart = {};
  }

  getOrder(orderId: number) {
    return this.http.get(this.getOrderUrl + orderId.toString());
  }

  getUserOrders(userId: number) {
    return this.http.get(this.getUserOrdersUrl + userId.toString());
  }

  getOrderPositions(orderId: number) {
    return this.http.get(this.getOrderPositionsUrl + orderId.toString());
  }

  addProductToCart(productId: number, quantity: number) {
    this.cart[productId] = quantity;
  }

  incrementProductQuantity(productId: number, quantity: number) {
    this.cart[productId]++;
  }

  decrementProductQuantity(productId: number, quantity: number) {
    this.cart[productId]--;
  }

  getCart() {
    return this.cart;
  }
}
