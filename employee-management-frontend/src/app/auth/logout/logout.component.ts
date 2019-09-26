import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { AuthService }      from '../auth.service';
import { Login, LoginResponse } from '../login'
import { Router } from '@angular/router';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {
  message: string
  constructor(
    private title: Title,
    private auth: AuthService,
    private router: Router
  ) {
    title.setTitle("Logout")
  }

  ngOnInit() {
    this.auth.logout()
    .subscribe(
      val => {
        this.message = '';
        this.router.navigate(['/login']);
      },
      error => {
        this.message = JSON.stringify(error);
        console.log("logout error: " + this.message);
      }
    );
  }

}
