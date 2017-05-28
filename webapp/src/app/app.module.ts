import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {RouterModule} from '@angular/router';

import {AppComponent} from './app.component';
import {BlankPageComponent} from './blank-page/blank-page.component';
import {AppRoutes} from './app.routes';
import {LoginComponent} from './login/login.component';
import {TemplateComponent} from './template/template.component';
import {MenuComponent} from './template/menu/menu.component';
import {HeaderComponent} from './template/header/header.component';
import {FooterComponent} from './template/footer/footer.component';
import {AuthGuard} from './_guard/auth.guard';

@NgModule({
  declarations: [
    AppComponent,
    BlankPageComponent,
    TemplateComponent,
    MenuComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule,
    HttpModule,
    AppRoutes
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule {
}
