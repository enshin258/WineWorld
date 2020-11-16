import { Component, OnInit } from '@angular/core';
import { ProductsServiceService } from 'src/app/services/products-service.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  constructor(private productsService: ProductsServiceService){}


  ngOnInit(): void {
  }

  
  saveProduct(){
    this.productsService.saveProduct().subscribe(()=>{});
}

}
