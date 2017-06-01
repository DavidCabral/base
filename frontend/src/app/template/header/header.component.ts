import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../_guard/auth.service';
import {User} from '../../_domain/user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  user: User;

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
    this.user = this.authService.getUser();
  }

  getTokenExpirationDate() {
    return this.authService.getTokenExpirationDate();
  }

  logout() {
    this.authService.logout();
  }

}
