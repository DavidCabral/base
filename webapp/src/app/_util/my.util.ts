import {Response} from '@angular/http';
import {isUndefined} from 'util';
import {environment} from '../../environments/environment';
import {Observable} from 'rxjs/Rx';


declare var swal: any;


export class MyUtil {

  static TOKEN_NAME = 'TOKEN';
  static TOKEN_HEADER = 'X-Auth-Token';

  static url(path: string): string {
    return environment.API_BASE_URL + path;
  }

  static extrairDados(response: Response) {
    return response.json() || {};
  }

  static handleError(error: Response | any) {
    let errMsg;
    if (error instanceof Response) {
      const body = error.json();
      if (error.status === 401 || error.status === 403) {
        body.error = 'não autorizado, acesso negado devido a credenciais inválidas ou expiradas';
      }
      if (body.errors) {
        errMsg = body.errors[0];
      } else {
        errMsg = body.error;
      }
    }
    return Observable.throw(errMsg);
  }

  static focus(elem) {
    setTimeout(() => elem.nativeElement.focus(), 200);
  }

  static messagem(titulo: string, tipo: string, msg: string) {
    swal(titulo, msg, tipo);
  }

  static success(msg: string) {
    this.messagem('Atenção', 'success', msg);
  }

  /**
   * para usar o callbacks da confirmação
   * crie a funcão _yes
   * e da negação _no
   * @param msg
   * @param obj passa o this..
   */
  static confirm(msg, obj) {
    const self = obj;
    swal({
      title: 'Tem Certeza?',
      text: msg,
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sim',
      cancelButtonText: 'Não'
    }).then(function () {
      if (!isUndefined(self._yes)) {
        self._yes();
      } else if (!environment.production) {
        console.warn('implemente a função _yes para callback');
      }
    }, function () {
      if (!isUndefined(self._no)) {
        self._no();
      }
    });
  }

  static error(error) {
    this.messagem('Oops', 'error', error);
  }

}
