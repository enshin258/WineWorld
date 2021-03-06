import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BookedOrder } from 'src/app/models/booked_order';
import { BookedOrderPosition } from 'src/app/models/booked_order_position';
import { User } from 'src/app/models/user';
import { OrderService } from 'src/app/services/order.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.css']
})
export class UserPanelComponent implements OnInit {
  isEditable: boolean = false;
  buttonText: string = "Edit";
  account_information_form: FormGroup;
  isUserLoggedIn = false;
  formChanged= false;
  userId: number;
  user: User;
  bookedOrders: BookedOrder[];

  constructor(private userService: UsersService,
    private orderService: OrderService,
    private formBuilder: FormBuilder) {
      this.isUserLoggedIn = (this.userService.loginData != null);
    }

  ngOnInit(): void {
    this.account_information_form = this.formBuilder.group({
      e_mail: [null, Validators.required],
      username: [null, Validators.required],
      password: [null, Validators.required]
    })
    if(this.isUserLoggedIn){
      this.userId = this.userService.loginData.userId;
      this.userService.getUserData(this.userId).subscribe((data) => {
        console.log(data);
        this.user = data;
      });
      this.orderService.getUserOrders(this.userId).subscribe((data) => {
        this.bookedOrders = data;
        console.log(this.bookedOrders);
        this.bookedOrders.forEach((order) => {
          order.firstOrderPosition = order.orderPositionResponses[0];
          if(order.orderPositionResponses.length > 1){
            order.orderPositionResponses = order.orderPositionResponses.slice(1);
          }
          else if(order.orderPositionResponses.length == 1){
            order.orderPositionResponses = [];
          }
        });
        console.log(this.bookedOrders);
      });
    }
  }

  onEdit() {
    this.isEditable = !this.isEditable;
    if(this.isEditable) {
      this.buttonText = "Save";
    }
    else {
      if(this.account_information_form.touched && this.formChanged){
        console.log('time to post changes');
        var updatedUser: User = {
          id: this.userId,
          email: (this.account_information_form.get('e_mail').value),
          login: (this.account_information_form.get('username').value),
          password: (this.account_information_form.get('password').value),
          roleName: ''
        }
        this.userService.changeUserInfo(updatedUser).subscribe((data) => {
          console.log(data);
        })
      }
      this.buttonText = "Edit";
      this.formChanged = false;
      this.account_information_form.reset();
    }
  }

  onReject(){
    this.isEditable = false;
    this.account_information_form.reset();
    this.buttonText = "Edit";
    this.formChanged = false;
  }

  onChange(event){
    this.formChanged = true;
  }
}
