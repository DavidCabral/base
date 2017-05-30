import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Http} from '@angular/http';

import {MyUtil} from '../_util/my.util';
import {User} from '../_domain/user';

@Injectable()
export class LoginService {

  path = 'login';

  constructor(private http: Http) {
  }

  entrar(usuario: User): Observable<any> {
    return this.http.post(MyUtil.url(this.path), JSON.stringify(usuario))
      .map(MyUtil.extrairDados)
      .catch(MyUtil.handleError);
  }

}
