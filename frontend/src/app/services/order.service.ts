import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {ProductMiniature} from '../models/product_miniature';
import {ShoppingCartPosition} from '../models/shoping_cart_position';
import {Order} from "../models/order";
import { Observable } from 'rxjs';
import { BookedOrder } from '../models/booked_order';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private getOrderUrl = 'http://localhost:8080/order/get/';
  private getUserOrdersUrl = 'http://localhost:8080/user/get/orders/';
  private getOrderPositionsUrl =
    'http://localhost:8080/order/position/get/all/';
  private addOrderUrl = 'http://localhost:8080/order/make';

  private cart: ShoppingCartPosition[];

  public numberOfItemsInCartObservable: Observable<number>;
  public numberOfItemsInCart : number = 0;

  constructor(private http: HttpClient) {
    this.cart = [];

    this.numberOfItemsInCartObservable = new Observable((observer) =>{
      observer.next(this.numberOfItemsInCart);
    });
  }

  getOrder(orderId: number) {
    return this.http.get(this.getOrderUrl + orderId.toString());
  }

  getUserOrders(userId: number): Observable<BookedOrder[]> {
    return this.http.get<BookedOrder[]>(this.getUserOrdersUrl + userId.toString(),
    {withCredentials: true});
  }

  getOrderPositions(orderId: number) {
    return this.http.get(this.getOrderPositionsUrl + orderId.toString());
  }

  addOrder(order: Order){
    return this.http.post(this.addOrderUrl,{
        date: order.date,
        addressLineOne: order.addressLineOne,
        addressLineTwo: order.addressLineTwo,
        city: order.city,
        postalCode: order.postalCode,
        countryCode: order.countryCode,
        totalCost: order.totalCost,
        phoneNumber: order.phoneNumber,
        status: order.status,
        userId: order.userId,
        orderPositionRequests: order.orderPositions,
    }, {withCredentials: true});
  }

  addProductToCart(product: ProductMiniature, quantity: number) {
    var position: ShoppingCartPosition = {
      productMiniature: product,
      quantity: quantity,
    };
    this.cart.push(position);

    this.numberOfItemsInCart++;
    this.numberOfItemsInCartObservable.
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
