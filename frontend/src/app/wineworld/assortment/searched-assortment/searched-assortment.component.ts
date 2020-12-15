import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Count } from 'src/app/models/count';
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
    //to refresh router after every category change
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.searchText = this.route.snapshot.paramMap.get('searchText');
    this.getPagesCount();
    this.productsService.getProductMiniaturesFromSearch(
      this.pageSize,
      this.currentPage,
      this.searchText
    ).subscribe((data) => {this.products = data});
    this.scrollToTop();
  }

  getPagesCount() {
    this.productsService
      .getAllSearchProductsCount(this.searchText)
      .subscribe((data) => {this.pagesCount = Math.ceil(data.count / this.pageSize);});
  }

  goToNextPage() {
    this.currentPage++;
    this.productsService.getProductMiniaturesFromSearch(
      this.pageSize,
      this.currentPage,
      this.searchText
    ).subscribe((data) => {this.products = data});
    this.scrollToTop();
  }

  goToPreviousPage() {
    this.currentPage--;
    this.productsService.getProductMiniaturesFromSearch(
      this.pageSize,
      this.currentPage,
      this.searchText
    ).subscribe((data) => {this.products = data});
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
