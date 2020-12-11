import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductMiniature } from 'src/app/models/product_miniature';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-searched-assortment',
  templateUrl: './searched-assortment.component.html',
  styleUrls: ['./searched-assortment.component.css'],
})
export class SearchedAssortmentComponent implements OnInit {
  products: ProductMiniature[];
  readonly pageSize: number = 20;
  currentPage: number;
  pagesCount: number;
  searchText: string;

  constructor(
    private productsService: ProductsService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.currentPage = 1;
    this.products = this.productsService.getProductMiniaturesFromSearch(
      this.pageSize,
      this.currentPage,
      this.searchText
    );
    this.getPagesCount();

    //to refresh router after every category change
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.searchText = this.route.snapshot.paramMap.get('searchText');
    this.scrollToTop();
  }

  getPagesCount() {
    var allProductsCount: number;
    // this.productsService
    //   .getAllProductsCount()
    //   .subscribe((pagesCount) => (allProductsCount = pagesCount));
    allProductsCount = this.productsService.getAllSearchProductsCount(
      this.searchText
    );
    this.pagesCount = Math.ceil(allProductsCount / this.pageSize);
  }

  goToNextPage() {
    this.currentPage++;
    this.products = this.productsService.getProductMiniaturesFromSearch(
      this.pageSize,
      this.currentPage,
      this.searchText
    );
    this.scrollToTop();
  }

  goToPreviousPage() {
    this.currentPage--;
    this.products = this.productsService.getProductMiniaturesFromSearch(
      this.pageSize,
      this.currentPage,
      this.searchText
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
