import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; 
import { HttpClientModule } from '@angular/common/http'; 


import { AppComponent } from './app.component';
import { ListingsComponent } from './secure/listings/listings.component';
import { ListingDetailComponent } from './secure/listing-detail/listing-detail.component';
import { ListingService } from './service/listing.service';
import { MessagesComponent } from './secure/messages/messages.component';
import { MessageService } from './service/message.service';
import { AppRoutingModule } from './app-routing.module';
import { ListingSearchComponent } from './secure/listing-search/listing-search.component';
import { ListingCreateComponent } from './secure/listing-create/listing-create.component';
import { MyListingsComponent } from './secure/my-listings/my-listings.component';
import { ListingEditComponent } from './secure/listing-edit/listing-edit.component';
import { BidService } from './service/bid.service';
import { BidDetailComponent } from './secure/bid-detail/bid-detail.component';
import { MyBidsComponent } from './secure/my-bids/my-bids.component';

import {AboutComponent, HomeComponent, HomeLandingComponent} from "./public/home.component";
import {AwsUtil} from "./service/aws.service";
import {UseractivityComponent} from "./secure/useractivity/useractivity.component";
import {MyProfileComponent} from "./secure/profile/myprofile.component";
import {SecureHomeComponent} from "./secure/landing/securehome.component";
import {JwtComponent} from "./secure/jwttokens/jwt.component";
import {DynamoDBService} from "./service/ddb.service";
import {LoginComponent} from "./public/auth/login/login.component";
import {RegisterComponent} from "./public/auth/register/registration.component";
import {ForgotPassword2Component, ForgotPasswordStep1Component} from "./public/auth/forgot/forgotPassword.component";
import {LogoutComponent, RegistrationConfirmationComponent} from "./public/auth/confirm/confirmRegistration.component";
import {ResendCodeComponent} from "./public/auth/resend/resendCode.component";
import {NewPasswordComponent} from "./public/auth/newpassword/newpassword.component";


import {UserRegistrationService} from "./service/user-registration.service";
import {UserParametersService} from "./service/user-parameters.service";
import {UserLoginService} from "./service/user-login.service";
import {CognitoUtil} from "./service/cognito.service";


@NgModule({
  declarations: [
    AppComponent,
    ListingsComponent,
    ListingDetailComponent,
    MessagesComponent,
    ListingSearchComponent,
    ListingCreateComponent,
    MyListingsComponent,
    ListingEditComponent,
    BidDetailComponent,
    MyBidsComponent,
     NewPasswordComponent,
        LoginComponent,
        LogoutComponent,
        RegistrationConfirmationComponent,
        ResendCodeComponent,
        ForgotPasswordStep1Component,
        ForgotPassword2Component,
        RegisterComponent,
        AboutComponent,
        HomeLandingComponent,
        HomeComponent,
        UseractivityComponent,
        MyProfileComponent,
        SecureHomeComponent,
        JwtComponent,
        AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
    
  ],
  providers: [ListingService, MessageService, BidService,CognitoUtil,
        AwsUtil,
        DynamoDBService,
        UserRegistrationService,
        UserLoginService,
        UserParametersService],
  bootstrap: [AppComponent]
})
export class AppModule { }
