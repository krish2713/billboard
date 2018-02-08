import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListingsComponent }      from './secure/listings/listings.component';
import { MyListingsComponent }  from './secure/my-listings/my-listings.component';
import { MyBidsComponent }  from './secure/my-bids/my-bids.component';
import { ListingDetailComponent }  from './secure/listing-detail/listing-detail.component';
import { BidDetailComponent }  from './secure/bid-detail/bid-detail.component';
import { ListingCreateComponent }  from './secure/listing-create/listing-create.component';
import { ListingEditComponent }  from './secure/listing-edit/listing-edit.component';
import {AboutComponent, HomeComponent, HomeLandingComponent} from "./public/home.component";
import {SecureHomeComponent} from "./secure/landing/securehome.component";
import {MyProfileComponent} from "./secure/profile/myprofile.component";
import {JwtComponent} from "./secure/jwttokens/jwt.component";
import {UseractivityComponent} from "./secure/useractivity/useractivity.component";
import {LoginComponent} from "./public/auth/login/login.component";
import {RegisterComponent} from "./public/auth/register/registration.component";
import {ForgotPassword2Component, ForgotPasswordStep1Component} from "./public/auth/forgot/forgotPassword.component";
import {LogoutComponent, RegistrationConfirmationComponent} from "./public/auth/confirm/confirmRegistration.component";
import {ResendCodeComponent} from "./public/auth/resend/resendCode.component";
import {NewPasswordComponent} from "./public/auth/newpassword/newpassword.component";

const homeRoutes: Routes = [
                            {
                                path: '',
                                redirectTo: '/home',
                                pathMatch: 'full'
                            },
                            {
                                path: 'home',
                                component: HomeComponent,
                                children: [
                                    {path: 'about', component: AboutComponent},
                                    {path: 'login/:role', component: LoginComponent},
                                    {path: 'register', component: RegisterComponent},
                                    {path: 'confirmRegistration/:username', component: RegistrationConfirmationComponent},
                                    {path: 'resendCode', component: ResendCodeComponent},
                                    {path: 'forgotPassword/:email', component: ForgotPassword2Component},
                                    {path: 'forgotPassword', component: ForgotPasswordStep1Component},
                                    {path: 'newPassword', component: NewPasswordComponent},
                                    {path: '', component: HomeLandingComponent}
                                ]
                            },
                        ];


const secureHomeRoutes: Routes = [
                                  {

                                      path: '',
                                      redirectTo: '/securehome',
                                      pathMatch: 'full'
                                  },
                                  {
                                      path: 'securehome', component: SecureHomeComponent, children: [
                                      {path: 'logout', component: LogoutComponent},
                                      {path: 'jwttokens', component: JwtComponent},
                                      {path: 'myprofile', component: MyProfileComponent},
                                      {path: 'useractivity', component: UseractivityComponent},
                                      { path: 'listings', component: ListingsComponent },
                                      { path: 'listing-detail/:id', component: ListingDetailComponent },
                                      { path: 'mylistings', component: MyListingsComponent },
                                      { path: 'createlisting', component: ListingCreateComponent },
                                      { path: 'editListing/:id', component: ListingEditComponent },
                                      { path: 'mybids', component: MyBidsComponent },
                                      { path: 'bid-detail/:id', component: BidDetailComponent },
                                      {path: '', component: ListingsComponent}]
                                  }
                              ];


const routes: Routes = [
                        {
                            path: '',
                            children: [
                                ...homeRoutes,
                                ...secureHomeRoutes,
                                {
                                    path: '',
                                    component: HomeComponent
                                }
                            ]
                        },


                    ];



@NgModule({
	imports: [ RouterModule.forRoot(routes) ],
	  exports: [ RouterModule ]
	})
	export class AppRoutingModule {}


