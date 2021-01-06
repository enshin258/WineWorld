import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { ShoppingCartPosition } from 'src/app/models/shoping_cart_position';
import { OrderService } from 'src/app/services/order.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UsersService} from "../../services/users.service";
import {Order} from "../../models/order";
import { DatePipe } from "@angular/common";
import {OrderPosition} from "../../models/order_position";

declare let paypal: any;

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css'],
  providers: [DatePipe]
})
export class ShoppingCartComponent implements OnInit, AfterViewChecked {
  cartPositions: ShoppingCartPosition[];
  totalPrice: number;
  order_details_form: FormGroup;
  isUserLoggedIn: boolean;

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
          application_context: {
            shipping_preference: "SET_PROVIDED_ADDRESS"
          },
          transactions: [{
            amount: {
              total: this.totalPrice,
              currency: "PLN"
            },
            item_list: {
              shipping_address: {
                recipient_name: "jacek jacek",
                line1: "4th Floor",
                line2: "Unit #34",
                city: "Wojeczna uniwersytet mechaniczny",
                country_code: "PL",
                postal_code: "95131",
                phone: "011862212345678",
                state: "CA"
              }
            }
          }],
        }
      });

    },
    onAuthorize: (data, actions) => {
      return actions.payment.execute().then((payment) => {
        //on payment success
        console.log('Pay');
        console.log(data)
        var orderPositions: OrderPosition[];
        this.cartPositions.forEach((position) => {
          var orderPosition: OrderPosition = {
            id: 0,
            productId: position.productMiniature.productId,
            qunatity: position.quantity
          };
          orderPositions.push(orderPosition)
        });
        var myDate = new Date();
        var order: Order = {
          id: 0,
          orderDate: this.datePipe.transform(myDate, 'dd.MM.yyyy'),
          orderAddress: this.order_details_form.get('address').value,
          orderCity: this.order_details_form.get('city').value,
          orderPostalCode: this.order_details_form.get('zip').value,
          orderTotalCost: this.totalPrice,
          orderPositions: orderPositions
        };
        this.orderService.addOrder(order).subscribe((data) => {
          console.log(data);
        })
      })
    }
  };

  constructor(private orderService: OrderService, private formBuilder: FormBuilder,
              private userService: UsersService, private datePipe: DatePipe) {
    this.isUserLoggedIn = (this.userService.loginData != null);
  }

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
    this.order_details_form = this.formBuilder.group({
      address: [null, Validators.required],
      city: [null, Validators.required],
      zip: [null, Validators.required]
    });
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
