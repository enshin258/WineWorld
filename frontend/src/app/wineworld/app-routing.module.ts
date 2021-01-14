import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';

import { AssortmentComponent } from './assortment/assortment.component';
import { CategoryComponent } from './assortment/category/category.component';
import { SearchedAssortmentComponent } from './assortment/searched-assortment/searched-assortment.component';
import { ProductComponent } from './product/product.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { UserPanelComponent } from './user-panel/user-panel.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactComponent } from './contact/contact.component';
import { HistoryOfWineComponent } from './history-of-wine/history-of-wine.component';
import { RecomendedWineComponent } from './recomended-wine/recomended-wine.component';

const routes: Routes = [
  { path: '', component: AssortmentComponent },
  { path: 'assortment', component: AssortmentComponent },
  { path: 'product/:id', component: ProductComponent },
  { path: 'category/:id', component: CategoryComponent },
  { path: 'search/:searchText', component: SearchedAssortmentComponent },
  { path: 'shopping-cart', component: ShoppingCartComponent},
  { path: 'admin-panel', component: AdminPanelComponent},
  { path: 'user-panel', component: UserPanelComponent},
  { path: 'about-us', component: AboutUsComponent},
  { path: 'contact', component: ContactComponent},
  { path: 'history-of-wine', component: HistoryOfWineComponent},
  { path: 'recomended-wine', component: RecomendedWineComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {onSameUrlNavigation: 'reload'})],
  exports: [RouterModule],
})
export class AppRoutingModule {}

export const routingComponents = [
  AssortmentComponent,
  ProductComponent,
];
