import { Component, OnInit } from '@angular/core';
import { Category } from '../models/category';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css'],
})
export class SidebarComponent implements OnInit {
  categories: Category[];

  constructor(private categoryService: CategoryService) {
    categoryService.getAllCategories()
      .subscribe((data) => {this.categories = data});
  }

  ngOnInit(): void {}
}
