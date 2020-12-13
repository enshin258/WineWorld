import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductMiniature } from 'src/app/models/product_miniature';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit {
  categoryId: number;
  products: ProductMiniature[];
  readonly pageSize: number = 20;
  currentPage: number;
  pagesCount: number;

  constructor(
    private productsService: ProductsService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.currentPage = 1;
    this.products = this.productsService.getProductMiniaturesOfCategory(
      this.pageSize,
      this.currentPage,
      this.categoryId
    );
    this.getPagesCount();

    //to refresh router after every category change
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.categoryId = Number.parseInt(this.route.snapshot.paramMap.get('id'));
    this.scrollToTop();
  }

  getPagesCount() {
    var allProductsCount: number;
    // this.productsService
    //   .getAllProductsCount()
    //   .subscribe((pagesCount) => (allProductsCount = pagesCount));
    allProductsCount = this.productsService.getAllCategoryProductsCount(
      this.categoryId
    );
    this.pagesCount = Math.ceil(allProductsCount / this.pageSize);
  }

  goToNextPage() {
    this.currentPage++;
    this.products = this.productsService.getProductMiniaturesOfCategory(
      this.pageSize,
      this.currentPage,
      this.categoryId
    );
    this.scrollToTop();
  }

  goToPreviousPage() {
    this.currentPage--;
    this.products = this.productsService.getProductMiniaturesOfCategory(
      this.pageSize,
      this.currentPage,
      this.categoryId
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
