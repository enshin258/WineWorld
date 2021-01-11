import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {
  AppRoutingModule,
  routingComponents,
} from './wineworld/app-routing.module';
import { AppComponent } from './app.component';
import { WineworldComponent } from './wineworld/wineworld.component';
import { ProductComponent } from './wineworld/product/product.component';
import { ProductsService } from './services/products.service';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { AssortmentComponent } from './wineworld/assortment/assortment.component';
import { UsersService } from './services/users.service';
import { LocationsService } from './services/locations.service';
import { CategoryComponent } from './wineworld/assortment/category/category.component';
import { CategoryService } from './services/category.service';
import { OpinionsService } from './services/opinions.service';
import { OrderService } from './services/order.service';
import { SidebarComponent } from './sidebar/sidebar.component';
import { SearchedAssortmentComponent } from './wineworld/assortment/searched-assortment/searched-assortment.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ShoppingCartComponent } from './wineworld/shopping-cart/shopping-cart.component';
import { AdminPanelComponent } from './wineworld/admin-panel/admin-panel.component';
import { UserPanelComponent } from './wineworld/user-panel/user-panel.component';
import { AboutUsComponent } from './wineworld/about-us/about-us.component';
import { ContactComponent } from './wineworld/contact/contact.component';
import { HistoryOfWineComponent } from './wineworld/history-of-wine/history-of-wine.component';

@NgModule({
  declarations: [
    AppComponent,
    WineworldComponent,
    ProductComponent,
    HeaderComponent,
    SidebarComponent,
    AssortmentComponent,
    CategoryComponent,
    SearchedAssortmentComponent,
    ShoppingCartComponent,
    AdminPanelComponent,
    UserPanelComponent,
    AboutUsComponent,
    ContactComponent,
    HistoryOfWineComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
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
