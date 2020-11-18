import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { WineworldComponent } from './wineworld/wineworld.component';
import { ProductComponent } from './wineworld/product/product.component';
import { LocationComponent } from './wineworld/location/location.component';
import { ProductsServiceService } from './services/products-service.service';
import { HttpClientModule } from '@angular/common/http';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { GridViewComponent } from './grid-view/grid-view.component';

@NgModule({
  declarations: [
    AppComponent,
    WineworldComponent,
    ProductComponent,
    LocationComponent,
    HeaderComponent,
    SidebarComponent,
    GridViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [ProductsServiceService],
  bootstrap: [AppComponent]
})
export class AppModule { }
