import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { FormGroup, FormControl } from '@angular/forms';
import { AuthService } from '../auth.service'
import { Login, LoginResponse } from '../login'
import { Config } from '../../config/config'
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private title: Title,
    private auth: AuthService,
    private router: Router
  ) {
    title.setTitle("Login")
  }

  ngOnInit() {
  }
  newCaptcha(): string {
    return Config.API_BASE_HREF + '/captcha?_ts=' + new Date().getTime();
  }

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
    captcha: new FormControl(''),
    rememberMe: new FormControl('')
  });

  message: string = null;
  captchaUrl: string = this.newCaptcha();

  submit(login: Login): boolean {
    if(login && login.username) {
      this.auth.login(
        login.username,
        login.password,
        login.captcha
      ).subscribe(
        data => {
          this.message = null;
          this.router.navigate([this.auth.redirectUrl]);
        },
        error => {
          console.log('error: ' + JSON.stringify(error));
          this.captchaUrl = this.newCaptcha();
          this.message = "登录出现错误(" + error.status + ")。";
        }
      );
    } else {
      this.message = "请输入您的用户名和密码。";
    }
    return false;
  };

  empty(message: String) : boolean {
    if(message) return true;
    return false;
  };
}
