import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddSodaComponent } from './components/add-soda/add-soda.component';
import { SodaDetailsComponent } from './components/soda-details/soda-details.component';
import { SodasListComponent } from './components/sodas-list/sodas-list.component';
import { CashListComponent } from './components/cash-list/cash-list.component';
import { VendingmachineListComponent } from './components/vendingmachine-list/vendingmachine-list.component';
import { VendingmachineDetailsComponent } from './components/vendingmachine-details/vendingmachine-details.component';

@NgModule({
  declarations: [
    AppComponent,
    AddSodaComponent,
    SodaDetailsComponent,
    SodasListComponent,
    CashListComponent,
    VendingmachineListComponent,
    VendingmachineDetailsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }