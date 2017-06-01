import {Component, OnDestroy, OnInit} from '@angular/core';
import {UsuariosService} from './usuarios.service';
import {User} from 'app/_domain/user';
import {Subject} from 'rxjs/Subject';
import {MyUtil} from '../_util/my.util';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit, OnDestroy {
  list: User[];
  private ngUnsubscribe: Subject<void>;

  constructor(private usuariosService: UsuariosService) {
  }

  ngOnInit() {
    this.list = [];
    this.ngUnsubscribe = new Subject<void>();
    this.usuariosService.getList()
      .takeUntil(this.ngUnsubscribe)
      .subscribe(
        obj => this.list = obj,
        e => MyUtil.error(e)
      );
  }

  confirm() {
    MyUtil.confirm('Deseja Realmente Salvar?', this);
  }

  _yes() {
    this.success();
  }

  _no() {
    this.error();
  }

  success() {
    MyUtil.success('success');
  }

  error() {
    MyUtil.error('Cancelado');
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }


}
