import {Injectable} from '@angular/core';
import {ConnectionBackend, RequestOptions, Request, RequestOptionsArgs, Response, Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Rx';

import {MyUtil} from './my.util';
import {AuthService} from '../_guard/auth.service';


@Injectable()
export class InterceptedHttp extends Http {
  constructor(backend: ConnectionBackend, defaultOptions: RequestOptions, private auth: AuthService) {
    super(backend, defaultOptions);
  }

  request(url: string | Request, options?: RequestOptionsArgs): Observable<Response> {
    return super.request(url, options).catch(this.catchErrors());
  }

  private catchErrors() {

    return (res: Response) => {
      if (res.status === 401 || res.status === 403) {
        console.log('The authentication session expired or the user is not authorized');
        console.log(res.status);
        this.auth.logout();
      }
      return Observable.throw(res);
    };
  }

  get(url: string, options?: RequestOptionsArgs): Observable<Response> {
    return super.get(url, this.getRequestOptionArgs(options));
  }

  post(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
    return super.post(url, body, this.getRequestOptionArgs(options));
  }

  put(url: string, body: string, options?: RequestOptionsArgs): Observable<Response> {
    return super.put(url, body, this.getRequestOptionArgs(options));
  }

  delete(url: string, options?: RequestOptionsArgs): Observable<Response> {
    return super.delete(url, this.getRequestOptionArgs(options));
  }


  private getRequestOptionArgs(options?: RequestOptionsArgs): RequestOptionsArgs {

    if (options == null) {
      options = new RequestOptions();
    }
    if (options.headers == null) {
      options.headers = new Headers();
    }

    options.headers.append('Content-Type', 'application/json');
    options.headers.append(MyUtil.TOKEN_HEADER, this.auth.getToken());

    return options;
  }
}
