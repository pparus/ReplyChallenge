import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {UserComponent} from './route/user/user.component';
import {VehicleComponent} from './route/vehicle/vehicle.component';
import {UserService} from './service/userService';
import {UserServiceImpl} from './service/impl/userServiceImpl';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from './route/home/home.component';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import { UserDetailComponent } from './route/user/detail/user-detail.component';
import {ReactiveFormsModule} from '@angular/forms';
import { VehicleService } from './service/vehicleService';
import { VehicleServiceImpl } from './service/impl/vehicleServiceImpl';
import { VehicleDiagramComponent } from './shared/components/vehicle-diagram/vehicle-diagram.component';
import { VehiclePopupComponent } from './shared/components/vehicle-popup/vehicle-popup.component';

const appRoutes: Routes = [
  {
    path: 'users/:id',
    component: UserDetailComponent
  },
  {
    path: 'users',
    component: UserComponent
  },
  {
    path: 'vehicles',
    component: VehicleComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: '**',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    VehicleComponent,
    HomeComponent,
    UserDetailComponent,
    VehicleDiagramComponent,
    VehiclePopupComponent
  ],
  imports: [
    RouterModule.forRoot(
      appRoutes,
    ),
    HttpClientModule,
    ReactiveFormsModule,
    BrowserModule
  ],
  providers: [
    {
      provide: UserService,
      useClass: UserServiceImpl
    },
    {
      provide: VehicleService,
      useClass: VehicleServiceImpl
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
