import {ModuleWithProviders} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BlankPageComponent} from './blank-page/blank-page.component';
import {AuthGuard} from './_guard/auth.guard';
import {TemplateComponent} from './template/template.component';
import {LoginComponent} from './login/login.component';
import {UsuariosComponent} from './usuarios/usuarios.component';

import {RegisterComponent} from './register/register.component';

const routes: Routes = [
  // logged routes
  {
    canActivate: [AuthGuard],
    component: TemplateComponent,
    path: '',
    children: [
      {
        canActivate: [AuthGuard],
        component: BlankPageComponent,
        path: 'blank_page'
      },
      {
        canActivate: [AuthGuard],
        component: UsuariosComponent,
        path: 'users'
      }
    ]
  },
  // not logged routes
  {
    component: LoginComponent,
    path: 'login'
  },
  {
    component: RegisterComponent,
    path: 'register'
  }
];

export const AppRoutes: ModuleWithProviders = RouterModule.forRoot(routes);
