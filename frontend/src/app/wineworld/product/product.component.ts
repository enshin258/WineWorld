import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';
import * as L from 'leaflet';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product';
import { OrderService } from 'src/app/services/order.service';
import { ProductMiniature } from 'src/app/models/product_miniature';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsersService } from 'src/app/services/users.service';
import { Opinion } from 'src/app/models/opinion';
import { OpinionsService } from 'src/app/services/opinions.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
  providers: [DatePipe]
})
export class ProductComponent implements OnInit {
  map: any;
  product: Product;
  readonly mapRadius: number = 5;
  productId: number;
  addCommentButtonEnabled = false;
  isUserLoggedIn: boolean;
  loggedInUserId: number;
  add_comment_form: FormGroup;
  selectedRating: number = 1;
  didUserAlreadyCommented: boolean;
  opinions: Opinion[];
  avarageRating: number = 1;


  constructor(
    private productsService: ProductsService,
    private orderService: OrderService,
    private route: ActivatedRoute,
    private formBuilder: FormBuilder,
    private userService: UsersService,
    private opinionService: OpinionsService,
    private datePipe: DatePipe
  ) {
    this.isUserLoggedIn = (this.userService.loginData != null);
    if(this.isUserLoggedIn == true){
      this.loggedInUserId = this.userService.loginData.userId;
    }
    this.add_comment_form = this.formBuilder.group({
      title: [null, Validators.required],
      description: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    this.productId = Number.parseInt(this.route.snapshot.paramMap.get('id'));
    this.productsService.getProduct(this.productId).subscribe((data) => {
      this.product = data;
      this.mapSetup();
    });
    this.opinionService.getProductOpinions(this.productId).subscribe((data) => {
      this.opinions = data;
      console.log(this.opinions);
    });
    this.didUserAlreadyCommented = false;
    var ratingSum = 0;
    this.opinions.forEach((opinion) => {
      ratingSum += opinion.rating;
      if(opinion.userId == this.loggedInUserId){
        this.didUserAlreadyCommented = true;
      }
    });
    try {
      this.avarageRating = Math.floor(ratingSum / this.opinions.length);
    } catch (error) {
      this.avarageRating = 0;
    }
  }

  mapSetup() {
    this.map = L.map('map').setView(
      [this.product.latitude, this.product.longitude],
      this.mapRadius
    );

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution:
        '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
    }).addTo(this.map);

    var marker = L.marker([
      this.product.latitude,
      this.product.longitude,
    ]).addTo(this.map);
    marker
      .bindPopup(
        '<b>' + this.product.name + '</b><br>' + this.product.description
      )
      .openPopup();
  }

  addProductToCart() {
    var cartPosition = this.orderService.getCartPosition(this.productId);
    if (cartPosition == null) {
      var productMiniature: ProductMiniature = {
        productId: this.productId,
        name: this.product.name,
        productDescription: this.product.productDescription,
        price: this.product.price,
        pictureUrl: this.product.pictureUrl,
      };
      this.orderService.addProductToCart(productMiniature, 1);
    }
  }

  onAddComment() {
    if(this.didUserAlreadyCommented){
      var date = new Date();
      var dateString: string = this.datePipe.transform(date, 'dd.MM.yyyy');
      var opinionToAdd: Opinion = {
        opinionId: 0,
        rating: this.selectedRating,
        comment: this.add_comment_form.get('description').value,
        userId: this.userService.loginData.userId,
        productId: this.productId,
        login: '',
        title: this.add_comment_form.get('title').value,
        date: dateString
      }
      console.log(opinionToAdd);
      this.opinionService.addOpinion(opinionToAdd).subscribe((data) => {
        console.log(data);
        this.ngOnInit();
      });
    }
    else{
      console.log('user laready commented');
    }
  }

  onStarClick(value: string){
    this.selectedRating = Number.parseInt(value);
    console.log(this.selectedRating);
  }

  onOpinionDelete(opinion: Opinion){
    this.opinionService.deleteOpinion(this.loggedInUserId, this.productId).subscribe((data) => {
      console.log(data);
      this.ngOnInit();
    });
  }
}
