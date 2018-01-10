import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListingsComponent }      from './listings/listings.component';
import { MyListingsComponent }  from './my-listings/my-listings.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { ListingDetailComponent }  from './listing-detail/listing-detail.component';
import { ListingCreateComponent }  from './listing-create/listing-create.component';
import { ListingEditComponent }  from './listing-edit/listing-edit.component';



const routes: Routes = [
                        { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
                        { path: 'dashboard', component: DashboardComponent },
                        { path: 'listings', component: ListingsComponent },
                        { path: 'detail/:id', component: ListingDetailComponent },
                        { path: 'mylistings', component: MyListingsComponent },
                        { path: 'createlisting', component: ListingCreateComponent },
                        { path: 'editListing/:id', component: ListingEditComponent }
                      ];

@NgModule({
	imports: [ RouterModule.forRoot(routes) ],
	  exports: [ RouterModule ]
	})
	export class AppRoutingModule {}


