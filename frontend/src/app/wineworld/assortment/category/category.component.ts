import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/models/category';
import { Count } from 'src/app/models/count';
import { ProductMiniature } from 'src/app/models/product_miniature';
import { CategoryService } from 'src/app/services/category.service';
import { OrderService } from 'src/app/services/order.service';
import { ProductsService } from 'src/app/services/products.service';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css'],
})
export class CategoryComponent implements OnInit {
  categoryId: number;
  categoryName: string = "";
  categories: Category[];
  products: ProductMiniature[];
  readonly pageSize: number = 20;
  currentPage: number;
  pagesCount: number;

  constructor(
    private productsService: ProductsService,
    private orderService: OrderService,
    private route: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService
  ) {
    this.currentPage = 1;
    //to refresh router after every category change
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.categoryId = Number.parseInt(this.route.snapshot.paramMap.get('id'));

    this.categoryService.getAllCategories().subscribe((data)=> {
      this.categories = data;
      this.categories.forEach((category)=> {
        if(category.genreId == this.categoryId) {
          this.categoryName = category.name;
        }
      });
    });



    this.getPagesCount();
    this.productsService
      .getProductMiniaturesOfCategory(
        this.pageSize,
        this.currentPage,
        this.categoryId
      )
      .subscribe((data) => {
        this.products = data;
      });
    this.scrollToTop();
  }

  getPagesCount() {
    this.productsService
      .getAllCategoryProductsCount(this.categoryId)
      .subscribe((data) => {
        this.pagesCount = Math.ceil(data.count / this.pageSize);
      });
  }

  goToNextPage() {
    this.currentPage++;
    this.productsService
      .getProductMiniaturesOfCategory(
        this.pageSize,
        this.currentPage,
        this.categoryId
      )
      .subscribe((data) => {
        this.products = data;
      });
    this.scrollToTop();
  }

  goToPreviousPage() {
    this.currentPage--;
    this.productsService
      .getProductMiniaturesOfCategory(
        this.pageSize,
        this.currentPage,
        this.categoryId
      )
      .subscribe((data) => {
        this.products = data;
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
