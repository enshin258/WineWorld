import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { ShoppingCartPosition } from 'src/app/models/shoping_cart_position';
import { OrderService } from 'src/app/services/order.service';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UsersService} from "../../services/users.service";
import {Order} from "../../models/order";
import { DatePipe } from "@angular/common";
import {OrderPosition} from "../../models/order_position";
import {User} from "../../models/user";
import { Router } from '@angular/router';

declare let paypal: any;

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.css'],
  providers: [DatePipe]
})
export class ShoppingCartComponent implements OnInit, AfterViewChecked {
  user: User;
  cartPositions: ShoppingCartPosition[];
  totalPrice: number;
  order_details_form: FormGroup;
  isUserLoggedIn: boolean = false;

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
          payer: {
            payer_info: {
              email: this.user.email
            }
          },
          transactions: [{
            amount: {
              total: this.totalPrice,
              currency: "USD"
            },
            item_list: {
              shipping_address: {
                recipient_name: this.order_details_form.get('order_first_and_last_name').value,
                line1: this.order_details_form.get('order_adress_line_1').value,
                line2: this.order_details_form.get('order_adress_line_2').value,
                city: this.order_details_form.get('order_adress_city').value,
                country_code: this.order_details_form.get('order_adress_country_code').value,
                postal_code: this.order_details_form.get('zip').value,
                phone: this.order_details_form.get('order_adress_phone').value
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
        var orderPositions: OrderPosition[];
        orderPositions = [ ];
        this.cartPositions.forEach((position) => {
          var orderPosition: OrderPosition = {
            productId: position.productMiniature.productId,
            quantity: position.quantity
          };
          orderPositions.push(orderPosition)
        });
        var myDate = new Date();
        var order: Order = {
          id: 0,
          date: this.datePipe.transform(myDate, 'dd.MM.yyyy'),
          addressLineOne: this.order_details_form.get('order_adress_line_1').value,
          addressLineTwo: this.order_details_form.get('order_adress_line_2').value,
          city: this.order_details_form.get('order_adress_city').value,
          postalCode: this.order_details_form.get('zip').value,
          countryCode: this.order_details_form.get('order_adress_country_code').value,
          totalCost: this.totalPrice,
          phoneNumber: this.order_details_form.get('order_adress_phone').value,
          status: "Accepted",
          userId: this.userService.loginData.userId,
          orderPositions: orderPositions
        };
        console.log(order);
        this.orderService.addOrder(order).subscribe((addedData) => {
          console.log(addedData);
        });
        this.orderService.emptyCart();
        this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {
          this.router.navigate(['/']);
        });
      })
    }
  };

  constructor(private orderService: OrderService, private formBuilder: FormBuilder,
              private userService: UsersService, private datePipe: DatePipe,
              private router: Router) {
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
    if(this.isUserLoggedIn){
        this.userService.getUserData(this.userService.loginData.userId).subscribe((data)=> {
        this.user = data;
      });
    }

    this.refreshCart();
    this.order_details_form = this.formBuilder.group({
      order_first_and_last_name: [null, Validators.required],
      order_adress_line_1: [null, Validators.required],
      order_adress_line_2: [null, Validators.required],
      order_adress_city: [null, Validators.required],
      order_adress_country_code: [null, Validators.required],
      zip: [null, Validators.required],
      order_adress_phone: [null, Validators.required],
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
