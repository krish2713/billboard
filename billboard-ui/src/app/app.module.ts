import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { HttpClientModule } from '@angular/common/http'; 


import { AppComponent } from './app.component';
import { ListingsComponent } from './listings/listings.component';
import { ListingDetailComponent } from './listing-detail/listing-detail.component';
import { ListingService } from './listing.service';
import { MessagesComponent } from './messages/messages.component';
import { MessageService } from './message.service';
import { AppRoutingModule } from './/app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';


@NgModule({
  declarations: [
    AppComponent,
    ListingsComponent,
    ListingDetailComponent,
    MessagesComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
    
  ],
  providers: [ListingService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
