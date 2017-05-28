import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  private password: string;
  private email: string;

  constructor(private router: Router) {
  }

  public ngOnInit() {
    window.dispatchEvent(new Event('resize'));
  }

  private login() {
    this.router.navigate(['blanck_page']);

  }

}
