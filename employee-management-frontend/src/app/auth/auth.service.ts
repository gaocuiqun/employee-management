import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { tap, delay } from 'rxjs/operators';

import { Login, LoginResponse } from './login'

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  isLoggedIn = false;
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
      '/login',
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
    return this.http.get('/user/login-check', {responseType: 'text'})
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
    return this.http.get('/logout', {responseType: 'text'})
    .pipe(
      tap(val => {
        this.isLoggedIn = false;
        this.redirectUrl = '';
      })
    );
  }
}
