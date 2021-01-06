import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Role } from '../models/role';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root',
})
export class UsersService {
  private addUserUrl = 'http://localhost:8080/user/register/false';
  private addAdminUrl = 'http://localhost:8080/user/register/true';
  private loginUserUrl = 'http://localhost:8080/login';
  private logoutUrl = 'http://localhost:8080/logout';
  private changeUserInfoUrl = 'http://localhost:8080/user/add';
  private deleteUserUrl = 'http://localhost:8080/user/delete/';

  role: Role;

  constructor(private http: HttpClient) {}

  register(user: User) {
    return this.http.post(this.addUserUrl, {
      email: user.email,
      login: user.username,
      password: user.password,
    });
  }

  addAdmin(user: User){
    return this.http.post(this.addAdminUrl, {
      email: user.email,
      login: user.username,
      password: user.password,
    }, {withCredentials: true});
  }

  login(user: User): Observable<HttpResponse<Role>>{
    const formData = new FormData();
    formData.append('username', user.username);
    formData.append('password', user.password);
    return this.http.post<Role>(this.loginUserUrl, formData, {
      observe: 'response',
      withCredentials: true,
    });
  }

  logout() {
    return this.http.get(this.logoutUrl, {
      observe: 'response',
      withCredentials: true,
    });
  }

  deleteUser(login: string){
    return this.http.delete(this.deleteUserUrl + login,
      {
        observe: 'response', withCredentials: true
    });
  }

  changeUserInfo(user: User) {
    return this.http.patch(this.changeUserInfoUrl, user);
  }

}
