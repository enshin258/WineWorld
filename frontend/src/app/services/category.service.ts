import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../models/category';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private getAllCategoriesUrl = 'http://localhost:8080/genre/get/all';

  constructor(private http: HttpClient) {}

  getAllCategories() : Observable<Category[]> {
     return this.http.get<Category[]>(this.getAllCategoriesUrl);
  }
}
