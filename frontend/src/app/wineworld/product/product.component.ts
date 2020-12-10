import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';
import * as L from "leaflet";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  constructor(private productsService: ProductsService) {}
  map;

  ngOnInit(): void {
    this.map = L.map("map").setView([66, 33], 5);

    L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
      attribution:
        '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);

    var marker = L.marker([66, 33]).addTo(this.map);
    marker.bindPopup("<b>Winnica super winko</b><br>Najlepsze winko u nas").openPopup();


  }

  saveProduct() {
    // this.productsService.saveProduct().subscribe(() => {});
  }
}
