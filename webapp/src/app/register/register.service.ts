import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Http} from '@angular/http';

import {MyUtil} from '../_util/my.util';
import {User} from '../_domain/user';

@Injectable()
export class RegisterService {

  path = 'register';

  constructor(private http: Http) {
  }

  register(user: User): Observable<any> {
    return this.http.post(MyUtil.url(this.path), JSON.stringify(user))
      .map(MyUtil.extrairDados)
      .catch(MyUtil.handleError);
  }


}
