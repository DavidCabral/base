import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Http, HttpModule, RequestOptions, XHRBackend} from '@angular/http';
import {RouterModule} from '@angular/router';
import {HashLocationStrategy, LocationStrategy} from '@angular/common';
import 'rxjs/add/operator/toPromise';

import {
  ButtonModule,
  InputTextModule,
  MessagesModule,
  PanelModule,
  DataTableModule,
  SharedModule,
  PasswordModule
} from 'primeng/primeng';

import {AppComponent} from './app.component';
import {BlankPageComponent} from './blank-page/blank-page.component';
import {AppRoutes} from './app.routes';
import {LoginComponent} from './login/login.component';
import {TemplateComponent} from './template/template.component';
import {MenuComponent} from './template/menu/menu.component';
import {HeaderComponent} from './template/header/header.component';
import {FooterComponent} from './template/footer/footer.component';
import {AuthGuard} from './_guard/auth.guard';
import {AuthService} from './_guard/auth.service';
import {InterceptedHttp} from './_util/interceptedHttp';
import {LoginService} from './login/login.service';
import {UsuariosComponent} from './usuarios/usuarios.component';
import {UsuariosService} from './usuarios/usuarios.service';
import {RegisterComponent} from './register/register.component';
import {RegisterService} from './register/register.service';


export function httpFactory(xhrBackend: XHRBackend, requestOptions: RequestOptions, auth: AuthService): Http {
  return new InterceptedHttp(xhrBackend, requestOptions, auth);
}


@NgModule({
  declarations: [
    AppComponent,
    BlankPageComponent,
    TemplateComponent,
    MenuComponent,
    HeaderComponent,
    FooterComponent,
    LoginComponent,
    UsuariosComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    RouterModule,
    HttpModule,
    AppRoutes,
    MessagesModule,
    PanelModule,
    InputTextModule,
    ButtonModule,
    DataTableModule,
    SharedModule,
    PasswordModule
  ],
  providers: [AuthGuard, LoginService, AuthService, UsuariosService, RegisterService,
    {
      provide: Http,
      useFactory: httpFactory,
      deps: [XHRBackend, RequestOptions, AuthService]
    },
    {provide: LocationStrategy, useClass: HashLocationStrategy}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
