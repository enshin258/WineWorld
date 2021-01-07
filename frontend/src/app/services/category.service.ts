import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from '../models/category';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root',
})
export class CategoryService {
  private getAllCategoriesUrl = 'http://localhost:8080/genre/get/all';
  private addCategoryUrl = 'http://localhost:8080/genre/add';
  private deleteCategoryUrl = 'http://localhost:8080/genre/delete/';
  private updateCategoryUrl = 'http://localhost:8080/genre/update/';

  constructor(private http: HttpClient) {}

  getAllCategories() : Observable<Category[]> {
     return this.http.get<Category[]>(this.getAllCategoriesUrl);
  }

  addCategory(categoryName: string){
    return this.http.post(this.addCategoryUrl,
      {name: categoryName},
      {withCredentials: true});
  }

  deleteCategory(genreId: number){
    return this.http.delete(this.deleteCategoryUrl + genreId,
      {withCredentials: true});
  }

  updateCategory(id: number, name: string){
    return this.http.put(this.updateCategoryUrl + id.toString(), 
    {name: name},
    {withCredentials: true}
    );
  }
}
