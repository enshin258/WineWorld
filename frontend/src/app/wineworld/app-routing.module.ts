import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AssortmentComponent } from './assortment/assortment.component';
import { CategoryComponent } from './assortment/category/category.component';
import { SearchedAssortmentComponent } from './assortment/searched-assortment/searched-assortment.component';
import { LocationComponent } from './location/location.component';
import { LoginComponent } from './login/login.component';
import { OrderComponent } from './order/order.component';
import { ProductComponent } from './product/product.component';
import { ProfileComponent } from './profile/profile.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  { path: '', component: AssortmentComponent },
  { path: 'assortment', component: AssortmentComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'product/:id', component: ProductComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'order', component: OrderComponent },
  { path: 'location', component: LocationComponent },
  { path: 'category/:id', component: CategoryComponent },
  { path: 'search/:searchText', component: SearchedAssortmentComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

export const routingComponents = [
  AssortmentComponent,
  LoginComponent,
  RegistrationComponent,
  ProductComponent,
  ProfileComponent,
  OrderComponent,
  LocationComponent,
];