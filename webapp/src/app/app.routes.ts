import {ModuleWithProviders} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BlankPageComponent} from './blank-page/blank-page.component';
import {AuthGuard} from './_guard/auth.guard';
import {TemplateComponent} from './template/template.component';
import {LoginComponent} from './login/login.component';


const routes: Routes = [
  // logged routes
  {
    canActivate: [AuthGuard],
    children: [
      {
        canActivate: [AuthGuard],
        component: BlankPageComponent,
        path: 'blank_page'
      }
    ],
    component: TemplateComponent,
    path: '',
  },
  // not logged routes
  {
    component: LoginComponent,
    path: 'login'
  }
];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes);
