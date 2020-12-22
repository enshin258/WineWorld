import { Component, OnInit } from '@angular/core';
import { ShoppingCartPosition } from 'src/app/models/shoping_cart_position';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css'],
})
export class ShoppingCartComponent implements OnInit {
  cartPositions: ShoppingCartPosition[];
  totalPrice: number;

  constructor(private orderService: OrderService) {}

  ngOnInit(): void {
    console.log(this.orderService.getCart());
    this.refreshCart();
  }

  removePosition(productId: number) {
    this.orderService.removeProduct(productId);
    this.refreshCart();
  }

  refreshCart() {
    this.cartPositions = this.orderService.getCart();
    this.totalPrice = this.orderService.getTotalPrice();
  }

  setPositionQuantity(productId: number, event) {
    var quantity = event.target.valueAsNumber;
    this.orderService.setProductQuantity(productId, quantity);
    this.refreshCart();
  }
}
