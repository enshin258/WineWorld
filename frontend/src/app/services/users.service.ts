import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private addUserUrl = 'http://localhost:8080/user/add';
  private loginUserUrl = '';
  private changeUserInfoUrl = '';

  constructor(private http: HttpClient) {}

  register(user: User) {
    return this.http.post(this.addUserUrl, user);
  }

  login(user: User) {
    return this.http.post(this.loginUserUrl, user);
  }

  changeUserInfo(user: User) {
    return this.http.patch(this.changeUserInfoUrl, user);
  }
}
