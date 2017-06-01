import {Component, ElementRef, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import {Message} from 'primeng/primeng';
import {User} from '../_domain/user';
import {LoginService} from './login.service';
import {AuthService} from '../_guard/auth.service';
import {MyUtil} from '../_util/my.util';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {

  @ViewChild('username') username: ElementRef;
  @ViewChild('password') password: ElementRef;
  user: User;
  msgs: Message[];
  private ngUnsubscribe: Subject<void>;

  constructor(private auth: AuthService, private loginService: LoginService) {
  }

  public ngOnInit() {
    this.user = new User();
    this.msgs = [];
    this.ngUnsubscribe = new Subject<void>();
  }

  login() {
    this.loginService.entrar(this.user)
      .takeUntil(this.ngUnsubscribe)
      .subscribe(
        token => this.auth.logar(token.token),
        e => this.erroLogin()
      );
  }

  erroLogin() {
    this.user.password = '';
    MyUtil.focus(this.password);
    this.msgs = [];
    this.msgs.push({severity: 'error', summary: '', detail: 'Login/Senha inválidos'});
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

}
