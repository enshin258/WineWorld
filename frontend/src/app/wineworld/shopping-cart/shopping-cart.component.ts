import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { ShoppingCartPosition } from 'src/app/models/shoping_cart_position';
import { OrderService } from 'src/app/services/order.service';

declare let paypal: any;

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css'],
})
export class ShoppingCartComponent implements OnInit, AfterViewChecked {
  cartPositions: ShoppingCartPosition[];
  totalPrice: number;

  addScript: boolean = false;
  paypalLoad: boolean = true;

  paypalConfig = {
    env: 'sandbox',
    client: {
      sandbox: 'AdOhfeWD3g5jL89Fg-fUSkOCNR5ZlJwLbRBers55Yx3vUKbj2LdR1S-3vxemg-w2pgbJvb-Q7fPV2USK',
      production: 'production'
    },
    commit: true,
    payment: (data, actions) => {
      return actions.payment.create({
        payment: {
          // transaction: [
          //   {
          //     amount: {
          //       total: this.totalPrice, currency: 'USD'}
          //     }
          // ]
          transactions: [{
            amount: {
              total: this.totalPrice,
              currency: "PLN"
            }
        }]
        }
      });
    },
    onAuthorize: (data, actions) => {
      return actions.payment.execute().then((payment) => {
        //on payment success
        console.log('Pay');
      })
    }
  };

  constructor(private orderService: OrderService) {}

  ngAfterViewChecked(): void{
    if(!this.addScript){
      this.addPaypalScript().then(() => {
        paypal.Button.render(this.paypalConfig, '#paypal-checkout-btn');
        this.paypalLoad = false;
      })
    }
  }

  addPaypalScript(){
    this.addScript = true;
    return new Promise((resolve, reject) => {
      let scriptTagElement = document.createElement('script');
      scriptTagElement.src = 'https://www.paypalobjects.com/api/checkout.js';
      scriptTagElement.onload = resolve;
      document.body.appendChild(scriptTagElement);
    })
  }

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