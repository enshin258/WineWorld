import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../models/category';

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private getAllCategoriesUrl = 'http://localhost:8080/products/categories/get';

  constructor(private http: HttpClient) {}

  getAllCategories() {
    // return this.http.get(this.getAllCategoriesUrl);
    let categories: Category[] = [];
    for (var i = 0; i < 8; i++) {
      let category = {
        id: i,
        genre: 'Category',
      };
      categories.push(category);
    }
    return categories;
  }
}
