import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AssortmentComponent } from './wineworld/assortment/assortment.component';
import { LocationComponent } from './wineworld/location/location.component';
import { LoginComponent } from './wineworld/login/login.component';
import { OrderComponent } from './wineworld/order/order.component';
import { ProductComponent } from './wineworld/product/product.component';
import { ProfileComponent } from './wineworld/profile/profile.component';
import { RegistrationComponent } from './wineworld/registration/registration.component';

const routes: Routes = [
  { path: '', component: AssortmentComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'product', component: ProductComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'order', component: OrderComponent },
  { path: 'location', component: LocationComponent },
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
