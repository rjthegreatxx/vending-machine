import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddSodaComponent } from './components/add-soda/add-soda.component';
import { SodaDetailsComponent } from './components/soda-details/soda-details.component';
import { SodasListComponent } from './components/sodas-list/sodas-list.component';

@NgModule({
  declarations: [
    AppComponent,
    AddSodaComponent,
    SodaDetailsComponent,
    SodasListComponent
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