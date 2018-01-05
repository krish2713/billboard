import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListingsComponent }      from './listings/listings.component';
import { DashboardComponent }   from './dashboard/dashboard.component';
import { ListingDetailComponent }  from './listing-detail/listing-detail.component';

const routes: Routes = [
                        { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
                        { path: 'dashboard', component: DashboardComponent },
                        { path: 'listings', component: ListingsComponent },
                        { path: 'detail/:id', component: ListingDetailComponent },
                      ];

@NgModule({
	imports: [ RouterModule.forRoot(routes) ],
	  exports: [ RouterModule ]
	})
	export class AppRoutingModule {}


