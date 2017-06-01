import {Component, OnDestroy, OnInit} from '@angular/core';
import {User} from '../_domain/user';
import {Subject} from 'rxjs/Subject';
import {MyUtil} from '../_util/my.util';
import {RegisterService} from './register.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {
  user: User;
  private ngUnsubscribe: Subject<void>;

  constructor(private registerService: RegisterService
    , private router: Router) {
  }

  ngOnInit() {
    this.user = new User();
    this.ngUnsubscribe = new Subject<void>();
  }

  register() {
    this.registerService
      .register(this.user)
      .takeUntil(this.ngUnsubscribe)
      .subscribe(
        ignore => {
          MyUtil.success('Usuário Cadastrado Com Sucesso');
          this.router.navigate(['/login']);
        },
        e => MyUtil.error(e)
      );
  }

  ngOnDestroy(): void {
    this.ngUnsubscribe.next();
    this.ngUnsubscribe.complete();
  }

}
