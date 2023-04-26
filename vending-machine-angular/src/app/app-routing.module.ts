import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SodasListComponent } from './components/sodas-list/sodas-list.component';
import { CashListComponent } from './components/cash-list/cash-list.component';
import { SodaDetailsComponent } from './components/soda-details/soda-details.component';
import { SodaPurchaseComponent } from './components/soda-purchase/soda-purchase.component';
import { AddSodaComponent } from './components/add-soda/add-soda.component';

const routes: Routes = [
  { path: '', redirectTo: 'sodas', pathMatch: 'full' },
  { path: 'sodas', component: SodasListComponent },
  { path: 'cash', component: CashListComponent },
  { path: 'sodas/:id', component: SodaDetailsComponent },
  { path: 'sodas/purchase/:id', component: SodaPurchaseComponent },
  { path: 'add', component: AddSodaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }