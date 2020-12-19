import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';
import * as L from 'leaflet';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product';
import { OrderService } from 'src/app/services/order.service';
import { ProductMiniature } from 'src/app/models/product_miniature';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  map: any;
  product: Product;
  readonly mapRadius: number = 5;
  productId: number;

  constructor(
    private productsService: ProductsService,
    private orderService: OrderService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.productId = Number.parseInt(this.route.snapshot.paramMap.get('id'));
    this.productsService.getProduct(this.productId).subscribe((data) => {
      this.product = data;
      this.mapSetup();
    });
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
}
