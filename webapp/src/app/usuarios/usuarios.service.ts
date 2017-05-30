import {Injectable} from '@angular/core';
import {Http} from '@angular/http';

import {Observable} from 'rxjs/Observable';
import {User} from '../_domain/user';
import {MyUtil} from '../_util/my.util';

@Injectable()
export class UsuariosService {

  private path = 'users';

  constructor(private http: Http) {
  }

  getList(): Observable<User[]> {
    return this.http.get(MyUtil.url(this.path))
      .map(MyUtil.extrairDados)
      .catch(MyUtil.handleError);
  }


}
