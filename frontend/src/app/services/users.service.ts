import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private addUserUrl = 'http://localhost:8080/user/add';
  private loginUserUrl = 'http://localhost:8080/login';
  private changeUserInfoUrl = 'http://localhost:8080/user/add';

  constructor(private http: HttpClient) {}

  register(user: User) {
    return this.http.post(this.addUserUrl, {
      email: user.email,
      login: user.username,
      password: user.password,
    });
  }

  login(user: User) {
    const formData = new FormData();
    formData.append('username', user.username);
    formData.append('password', user.password);
    return this.http.post(this.loginUserUrl, formData);
  }

  changeUserInfo(user: User) {
    return this.http.patch(this.changeUserInfoUrl, user);
  }
}
