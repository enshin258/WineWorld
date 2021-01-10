import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginData } from '../models/login_data';
import { User } from '../models/user';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  searchText: string;
  form: FormGroup;
  loginForm: FormGroup;
  registerForm: FormGroup;
  isUserLoggedIn = false;
  buttonText = 'Login';
  adminPanelEnabled = false;

  constructor(
    private userService: UsersService,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.searchText = '';
    //to refresh router after every category change
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      searchText: [null, Validators.required],
    });

    this.loginForm = this.formBuilder.group({
      loginText: [null, Validators.required],
      passwordText: [null, Validators.required],
    });

    this.registerForm = this.formBuilder.group({
      emailText: [null, Validators.required],
      loginText: [null, Validators.required],
      passwordText: [null, Validators.required],
    });

    this.preparePopup();
  }

  preparePopup() {
    // Get the modal
    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var btn = document.getElementById('loginBtn');

    // When the user clicks on the button, open the modal
    btn.onclick = function () {
      modal.style.display = 'block';
    };

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
      if (event.target == modal) {
        modal.style.display = 'none';
      }
    };
  }

  onSearch() {
    this.router.navigate(['/search', this.form.get('searchText').value]);
  }

  onLogin() {
    var user: User = {
      id: 0,
      email: '',
      login: this.loginForm.get('loginText').value,
      password: this.loginForm.get('passwordText').value,
      roleName: ''
    };
    this.loginForm.reset();
    this.userService.login(user).subscribe(
      (res) => {
        console.log(res);
        this.userService.loginData = res.body;
        console.log(this.userService.loginData);
        this.isUserLoggedIn = true;
        this.adminPanelEnabled = this.isUserLoggedIn && (this.userService.loginData.roleId == 2);
        var modal = document.getElementById('myModal');
        modal.style.display = 'none';
        var url = this.router.url;
        this.router.navigateByUrl(url, { skipLocationChange: true }).then(() => {
          this.router.navigate([url]);
        });
      },
      (err) => console.error(err)
    );
  }

  onRegister() {
    console.log(
      this.registerForm.get('emailText').value +
        '   ' +
        this.registerForm.get('loginText').value +
        '   ' +
        this.registerForm.get('passwordText').value
    );

    var user: User = {
      id: 0,
      email: this.registerForm.get('emailText').value,
      login: this.registerForm.get('loginText').value,
      password: this.registerForm.get('passwordText').value,
      roleName: ''
    };
    this.registerForm.reset();
    this.userService.register(user).subscribe((data) => {
      console.log(data);
    });
  }

  onLogout() {
    this.userService.logout().subscribe(
      (res) => {
        console.log(res);
        this.isUserLoggedIn = false;
        this.adminPanelEnabled = false;
        this.userService.loginData = null;
        var url = this.router.url;
        this.router.navigateByUrl(url, { skipLocationChange: true }).then(() => {
          this.router.navigate([url]);
        });
      },
      (err) => console.error(err)
    );
  }
}
