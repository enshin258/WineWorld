import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductMiniature } from '../models/product_miniature';
import { ShoppingCartPosition } from '../models/shoping_cart_position';
import { ShoppingCartComponent } from '../wineworld/shopping-cart/shopping-cart.component';
import {OrderRequest} from "../models/order_request";
import {Order} from "../models/order";
import {observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private getOrderUrl = 'http://localhost:8080/order/get/';
  private getUserOrdersUrl = 'http://localhost:8080/order/get/all/';
  private getOrderPositionsUrl =
    'http://localhost:8080/order/position/get/all/';
  private addOrderUrl = 'http://localhost:8080/order/make'

  private cart: ShoppingCartPosition[];

  constructor(private http: HttpClient) {
    this.cart = [];
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

  addOrder(order: Order){
    return this.http.post(this.addOrderUrl,{
        date: order.orderDate,
        address: order.orderAddress,
        city: order.orderCity,
        postalCode: order.orderPostalCode,
        totalCost: order.orderTotalCost,
        orderPositions: order.orderPositions,
    }, {observe: 'response'});
  }

  addProductToCart(product: ProductMiniature, quantity: number) {
    var position: ShoppingCartPosition = {
      productMiniature: product,
      quantity: quantity,
    };
    this.cart.push(position);
  }

  getCart() {
    return this.cart;
  }

  getCartPosition(productId: number) {
    for (var i = 0; i < this.cart.length; i++) {
      if (this.cart[i].productMiniature.productId == productId) {
        return this.cart[i];
      }
    }
    return null;
  }

  removeProduct(productId: number) {
    for (var i = 0; i < this.cart.length; i++) {
      if (this.cart[i].productMiniature.productId == productId) {
        this.cart.splice(i, 1);
      }
    }
  }

  setProductQuantity(productId: number, quantity: number) {
    for (var i = 0; i < this.cart.length; i++) {
      var position = this.cart[i];
      if (position.productMiniature.productId == productId) {
        position.quantity = quantity;
      }
    }
  }

  getTotalPrice() {
    var total = 0;
    this.cart.forEach((position) => {
      total += position.productMiniature.price * position.quantity;
    });
    return total;
  }
}
