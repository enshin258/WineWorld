import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';
import * as L from 'leaflet';
import { ActivatedRoute } from '@angular/router';
import { Product } from 'src/app/models/product';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  map: any;
  product: Product;
  readonly mapRadius: number = 5;

  constructor(
    private productsService: ProductsService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    var productId: number = Number.parseInt(
      this.route.snapshot.paramMap.get('id')
    );

    this.product = this.productsService.getProduct(productId);

    this.map = L.map('map').setView(
      [this.product.location.latitude, this.product.location.longitude],
      this.mapRadius
    );

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution:
        '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
    }).addTo(this.map);

    var marker = L.marker([
      this.product.location.latitude,
      this.product.location.longitude,
    ]).addTo(this.map);
    marker
      .bindPopup(
        '<b>' +
          this.product.location.name +
          '</b><br>' +
          this.product.location.description
      )
      .openPopup();
  }

  saveProduct() {
    // this.productsService.saveProduct().subscribe(() => {});
  }
}
