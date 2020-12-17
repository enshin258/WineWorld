import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {
  AppRoutingModule,
  routingComponents,
} from './wineworld/app-routing.module';
import { AppComponent } from './app.component';
import { WineworldComponent } from './wineworld/wineworld.component';
import { ProductComponent } from './wineworld/product/product.component';
import { LocationComponent } from './wineworld/location/location.component';
import { ProductsService } from './services/products.service';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { AssortmentComponent } from './wineworld/assortment/assortment.component';
import { LoginComponent } from './wineworld/login/login.component';
import { RegistrationComponent } from './wineworld/registration/registration.component';
import { ProfileComponent } from './wineworld/profile/profile.component';
import { OrderComponent } from './wineworld/order/order.component';
import { UsersService } from './services/users.service';
import { LocationsService } from './services/locations.service';
import { CategoryComponent } from './wineworld/assortment/category/category.component';
import { CategoryService } from './services/category.service';
import { OpinionsService } from './services/opinions.service';
import { OrderService } from './services/order.service';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SearchedAssortmentComponent } from './wineworld/assortment/searched-assortment/searched-assortment.component';
import { ReactiveFormsModule } from '@angular/forms';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';

@NgModule({
  declarations: [
    AppComponent,
    WineworldComponent,
    ProductComponent,
    LocationComponent,
    HeaderComponent,
    SidebarComponent,
    AssortmentComponent,
    LoginComponent,
    RegistrationComponent,
    ProfileComponent,
    OrderComponent,
    CategoryComponent,
    SearchedAssortmentComponent,
    ShoppingCartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
  ],
  providers: [
    ProductsService,
    UsersService,
    LocationsService,
    CategoryService,
    OpinionsService,
    OrderService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
