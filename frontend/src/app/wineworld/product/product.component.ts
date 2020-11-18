import { Component, OnInit } from '@angular/core';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  constructor(private productsService: ProductsService) {}

  ngOnInit(): void {}

  saveProduct() {
    this.productsService.saveProduct().subscribe(() => {});
  }
}
