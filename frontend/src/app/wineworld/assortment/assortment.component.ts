import { Component, OnInit } from '@angular/core';
import { Count } from 'src/app/models/count';
import { Product } from 'src/app/models/product';
import { ProductMiniature } from 'src/app/models/product_miniature';
import { OrderService } from 'src/app/services/order.service';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-assortment',
  templateUrl: './assortment.component.html',
  styleUrls: ['./assortment.component.css'],
})
export class AssortmentComponent implements OnInit {
  public productsMiniatures: ProductMiniature[];
  readonly pageSize: number = 20;
  currentPage: number;
  pagesCount: number;
  allProductsCount: number;

  constructor(
    private productsService: ProductsService,
    private orderService: OrderService
  ) {
    this.currentPage = 1;
    this.getPagesCount();
    productsService
      .getAllProductMiniatures(this.pageSize, this.currentPage)
      .subscribe((data) => {
        this.productsMiniatures = data;
      });
  }

  ngOnInit(): void {}

  getPagesCount() {
    this.productsService.getAllProductsCount().subscribe((data) => {
      this.pagesCount = Math.ceil(data.count / this.pageSize);
    });
  }

  goToNextPage() {
    this.currentPage++;
    this.productsService
      .getAllProductMiniatures(this.pageSize, this.currentPage)
      .subscribe((data) => {
        this.productsMiniatures = data;
      });
    this.scrollToTop();
  }

  goToPreviousPage() {
    this.currentPage--;
    this.productsService
      .getAllProductMiniatures(this.pageSize, this.currentPage)
      .subscribe((data) => {
        this.productsMiniatures = data;
      });
    this.scrollToTop();
  }

  private scrollToTop() {
    let scrollToTop = window.setInterval(() => {
      let pos = window.pageYOffset;
      if (pos > 0) {
        window.scrollTo(0, pos - 200);
      } else {
        window.clearInterval(scrollToTop);
      }
    }, 16);
  }

  addProductToCart(event, product: ProductMiniature) {
    event.stopPropagation();
    var cartPosition = this.orderService.getCartPosition(product.productId);
    if (cartPosition == null) {
      this.orderService.addProductToCart(product, 1);
    }
  }
}
