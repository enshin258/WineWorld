import { Component, OnInit } from '@angular/core';
import { ProductMiniature } from 'src/app/models/product_miniature';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-assortment',
  templateUrl: './assortment.component.html',
  styleUrls: ['./assortment.component.css'],
})
export class AssortmentComponent implements OnInit {
  public productsMiniatures: ProductMiniature[];
  readonly pageSize: number = 30;
  currentPage: number;
  pagesCount: number;

  constructor(private productsService: ProductsService) {
    this.currentPage = 1;
    this.productsMiniatures = productsService.getAllProductMiniatures(
      this.pageSize,
      this.currentPage
    );
    this.getPagesCount();
  }

  ngOnInit(): void {}

  getPagesCount() {
    var allProductsCount: number;
    // this.productsService
    //   .getAllProductsCount()
    //   .subscribe((pagesCount) => (allProductsCount = pagesCount));
    allProductsCount = this.productsService.getAllProductsCount();
    this.pagesCount = Math.ceil(allProductsCount / this.pageSize);
  }

  goToNextPage() {
    this.currentPage++;
    this.productsMiniatures = this.productsService.getAllProductMiniatures(
      this.pageSize,
      this.currentPage
    );
    this.scrollToTop();
  }

  goToPreviousPage() {
    this.currentPage--;
    this.productsMiniatures = this.productsService.getAllProductMiniatures(
      this.pageSize,
      this.currentPage
    );
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
}
