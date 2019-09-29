import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { tap, delay } from 'rxjs/operators';

import { Login, LoginResponse } from './login'
import { Config } from '../config/config'

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  isLoggedIn = false;
  user = '';
  // store the URL so we can redirect after logging in
  redirectUrl: string = '';

  constructor(private http: HttpClient) {}

  login(username: string, password: string, captcha: string) {
    const httpOptions = {
      responseType: 'text' as 'json',
      headers: new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
      })
    };

    let body = new HttpParams()
      .set('username', username)
      .set('password', password)
      .set('captcha', captcha);

    return this.http.post(
      Config.API_BASE_HREF + '/login',
      body,
      httpOptions
    ).pipe(
      tap(
        event => {
          this.isLoggedIn = true;
        },
        error => {
          console.log('error: ' + JSON.stringify(error));
        }
      )
    );
  }

  check() {
    return this.http.get(Config.API_BASE_HREF + '/user/login-check', {responseType: 'text'})
    .pipe(
      tap(
        event => {
          this.isLoggedIn = true;
        },
        error => {
          console.log('error: ' + JSON.stringify(error));
        }
      )
    );
  }

  who() {
    return this.http.get(Config.API_BASE_HREF + '/who', {responseType: 'text'})
    .pipe(
      tap(
        event => {
          this.isLoggedIn = true;
        },
        error => {
          console.log('error: ' + JSON.stringify(error));
        }
      )
    );
  }


  logout(): Observable<string> {
    return this.http.get(Config.API_BASE_HREF + '/logout', {responseType: 'text'})
    .pipe(
      tap(val => {
        this.isLoggedIn = false;
        this.redirectUrl = '';
      })
    );
  }
}
