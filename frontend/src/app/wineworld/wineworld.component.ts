import { Component, OnInit, ViewChild } from '@angular/core';
import { ProductsServiceService } from '../services/products-service.service';
import { ProductComponent } from './product/product.component';

@Component({
  selector: 'app-wineworld',
  templateUrl: './wineworld.component.html',
  styleUrls: ['./wineworld.component.css']
})
export class WineworldComponent implements OnInit {

  collapsed = true;
  orderFinished = false;

  constructor(private productsService: ProductsServiceService){}

  ngOnInit(): void {
  }

  @ViewChild('productsC')
  productsC: ProductComponent;
  
  toggleCollapsed(): void {
      this.collapsed = !this.collapsed;
  }
 
  finishOrder(orderFinished: boolean) {
      this.orderFinished = orderFinished;
  }
 
  reset() {
      this.orderFinished = false;
      // this.productsC.reset();
  }

  saveProduct(){
      this.productsService.saveProduct().subscribe(()=>{});
  }
}
