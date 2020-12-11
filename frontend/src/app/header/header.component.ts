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

  constructor(private router: Router, private formBuilder: FormBuilder) {
    this.searchText = '';
    //to refresh router after every category change
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      searchText: [null, Validators.required],
    });
  }

  onSearch() {
    this.router.navigate(['/search', this.form.get('searchText').value]);
  }
}
