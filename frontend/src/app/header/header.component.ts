import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  searchText: string;
  form: FormGroup;
  loginForm: FormGroup;

  constructor(private router: Router, private formBuilder: FormBuilder) {
    this.searchText = '';
    //to refresh router after every category change
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      searchText: [null, Validators.required],
    });
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
}
