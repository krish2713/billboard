import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListingsComponent }      from './listings/listings.component';
import { MyListingsComponent }  from './my-listings/my-listings.component';
import { MyBidsComponent }  from './my-bids/my-bids.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { ListingDetailComponent }  from './listing-detail/listing-detail.component';
import { BidDetailComponent }  from './bid-detail/bid-detail.component';
import { ListingCreateComponent }  from './listing-create/listing-create.component';
import { ListingEditComponent }  from './listing-edit/listing-edit.component';
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
                                    {path: 'register/:role', component: RegisterComponent},
                                    {path: 'confirmRegistration/:username/:role', component: RegistrationConfirmationComponent},
                                    {path: 'resendCode/:role', component: ResendCodeComponent},
                                    {path: 'forgotPassword/:email/:role', component: ForgotPassword2Component},
                                    {path: 'forgotPassword/:role', component: ForgotPasswordStep1Component},
                                    {path: 'newPassword/:role', component: NewPasswordComponent},
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
                                      {path: '', component: MyProfileComponent}]
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


