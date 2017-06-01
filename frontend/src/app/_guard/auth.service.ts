import {EventEmitter, Injectable} from '@angular/core';
import {MyUtil} from '../_util/my.util';
import {JwtHelper} from 'angular2-jwt';
import {environment} from '../../environments/environment';
import {User} from '../_domain/user';
import {Router} from '@angular/router';
import {isUndefined} from 'util';

@Injectable()
export class AuthService {

  jwtHelper: JwtHelper;

  constructor(private router: Router) {
    this.jwtHelper = new JwtHelper();
  }

  logout() {
    this.getStorage().clear();
    this.router.navigate(['/login']);
  }

  logar(token: string) {
    this.setToken(token);
    this.router.navigate(['/']);
  }

  getTokenExpirationDate() {
    return this.jwtHelper.getTokenExpirationDate(this.getToken());
  }

  loggedIn(): boolean {
    const token = this.getToken();
    if (isUndefined(token)) {
      return false;
    }
    try {
      if (this.jwtHelper.isTokenExpired(token)) {
        return false;
      }
    } catch (e) {
      return false;
    }
    return true;
  }

  getUser(): User {
    try {
      const decoder = this.jwtHelper.decodeToken(this.getToken());
      const user = new User();
      user.username = decoder.sub;
      return user;
    } catch (ignore) {
      return null;
    }
  }


  setToken(token: string) {
    this.getStorage().setItem(MyUtil.TOKEN_NAME, token);
  }

  getToken(): string {
    return this.getStorage().getItem(MyUtil.TOKEN_NAME);
  }

  getStorage(): Storage {
    return localStorage;

  }


}
