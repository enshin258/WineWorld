import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GridViewComponent } from './grid-view/grid-view.component';
import { ProductComponent } from './wineworld/product/product.component';

const routes: Routes = [
  {path: '', component: GridViewComponent},
  {path: 'products', component: ProductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

export const routingComponents = [ProductComponent]