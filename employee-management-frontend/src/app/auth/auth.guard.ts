import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

import { Login, LoginResponse } from './login'
import { AuthService }      from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    let url: string = state.url;
    return this.checkLogin(url);
  }

  checkLogin(url: string): boolean {
    // Store the attempted URL for redirecting
    if (this.authService.isLoggedIn) { return true; }
    this.authService.redirectUrl = url;
    this.authService.who()
    .subscribe(
      (response: string) => {
        // Navigate to the login page with extras
        console.log(response);
        this.authService.user = response;
        if('anonymousUser' == (response.trim())) {
          this.router.navigate(['/login']);
        } else {
          this.authService.isLoggedIn = true;
          this.router.navigate([this.authService.redirectUrl]);
        }
      },
      error => {
        this.router.navigate(['/login']);
      }
    );
    return false;
  }
}
