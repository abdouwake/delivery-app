import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { DragScrollModule } from 'ngx-drag-scroll';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './core/public/components/login/login.component';
import { MatIconModule } from '@angular/material/icon';
import { SignUpComponent } from './core/public/components/sign-up/sign-up.component';
import { SelectSignInPicturePipe } from './core/public/pipes/select-sign-in-picture.pipe';
import { FormComponent } from './core/public/components/sign-up/form/form.component';
import { LandingPageComponent } from './core/public/components/landing-page/landing-page.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthorizeGuardService } from './core/public/guards/authorize-guard.service';
import { UniversalAppInterceptor } from './core/public/interceptor/UniversalApp.interceptor';
import { HeaderComponent } from './core/public/components/landing-page/header/header.component';
import { FiltersComponent } from './core/public/components/landing-page/filters/filters.component';
import { RestaurantCardComponent } from './core/public/components/landing-page/restaurant-card/restaurant-card.component';
import { SliderComponent } from './core/public/components/landing-page/slider/slider.component';
import { RestaurantPageComponent } from './core/public/components/restaurant-page/restaurant-page.component';
import { FoodSliderComponent } from './core/public/components/restaurant-page/food-slider/food-slider.component';

const appRoutes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  { path: 'login', component: LoginComponent },
  { path: 'singnup', component: SignUpComponent },
  { path: 'restaurant/:id', component: RestaurantPageComponent },
  {
    path: 'landing',
    component: LandingPageComponent,
    canActivate: [AuthorizeGuardService],
  },
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignUpComponent,
    SelectSignInPicturePipe,
    FormComponent,
    LandingPageComponent,
    HeaderComponent,
    FiltersComponent,
    RestaurantCardComponent,
    SliderComponent,
    RestaurantPageComponent,
    FoodSliderComponent,
  ],
  imports: [
    FormsModule,
    HttpClientModule,
    BrowserModule,
    DragScrollModule,
    AppRoutingModule,
    MatIconModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
  ],

  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: UniversalAppInterceptor,
      multi: true,
    },
  ],

  bootstrap: [AppComponent],
})
export class AppModule {}
