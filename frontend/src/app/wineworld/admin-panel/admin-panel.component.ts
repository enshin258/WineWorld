import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/models/category';
import { Location } from 'src/app/models/location';
import { User } from 'src/app/models/user';
import { CategoryService } from 'src/app/services/category.service';
import { LocationsService } from 'src/app/services/locations.service';
import { UsersService } from 'src/app/services/users.service';
import {Add_product} from "../../models/add_product";
import {ProductsService} from "../../services/products.service";

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  addWineForm: FormGroup;
  deleteWineForm: FormGroup;
  addLocationForm: FormGroup;
  deleteLocationForm: FormGroup;
  addCategoryForm: FormGroup;
  deleteCategoryForm: FormGroup;
  addAdminForm: FormGroup;
  deleteUserForm: FormGroup;
  categories: Category[];
  locations: Location[];
  uploadFile: File;

  constructor(private categoryService: CategoryService, 
    private locationService: LocationsService,
    private userService: UsersService,
    private formBuilder: FormBuilder, private productService: ProductsService) {
      
      categoryService.getAllCategories()
      .subscribe((data) => {this.categories = data});

      locationService.getAllLocations()
      .subscribe((data) => {this.locations = data});
     }

  ngOnInit(): void {
    
    this.addWineForm = this.formBuilder.group({
      name: [null, Validators.required],
      price: [null, Validators.required],
      image: [null, Validators.required],
      description: [null, Validators.required],
      category: [null, Validators.required],
      location: [null, Validators.required],
      producer: [null, Validators.required],
      alcohol_level: [null, Validators.required],
      year: [null, Validators.required],
      volume: [null, Validators.required],
    });

    this.deleteWineForm = this.formBuilder.group({
      id: [null, Validators.required],
    });

    this.addLocationForm = this.formBuilder.group({
      latitude: [null, Validators.required],
      longitude: [null, Validators.required],
      location_description: [null, Validators.required],
      country: [null, Validators.required],
    });

    this.deleteLocationForm = this.formBuilder.group({
      id: [null, Validators.required],
    });

    this.addCategoryForm = this.formBuilder.group({
      name: [null, Validators.required],
    });

    this.deleteCategoryForm = this.formBuilder.group({
      id: [null, Validators.required],
    });

    this.addAdminForm = this.formBuilder.group({
      email: [null, Validators.required],
      login: [null, Validators.required],
      password: [null, Validators.required],
    });

    this.deleteUserForm = this.formBuilder.group({
      login: [null, Validators.required],
    });
  }

  onAddWine(){
    console.log(this.addWineForm);
    var locationId: number = this.addWineForm.get('location').value.match(/\d+/)[0];
    var genreId: number = this.addWineForm.get('category').value.match(/\d+/)[0];
    var product: Add_product = {
      name: this.addWineForm.get('name').value,
      price: this.addWineForm.get('price').value,
      picture: this.uploadFile,
      genreId: genreId,
      productDescription: this.addWineForm.get('description').value,
      locationId: locationId,
      producer: this.addWineForm.get('producer').value,
      alcoholLevel: this.addWineForm.get('alcohol_level').value,
      year: this.addWineForm.get('year').value,
      volume: this.addWineForm.get('volume').value
    }
    this.productService.saveProduct(product).subscribe((data) => console.log(data));
    this.addWineForm.reset();
  }

  onDeleteWine(){
    console.log(this.deleteWineForm);
    this.deleteWineForm.reset();
  }

  onAddLocation(){
    console.log(this.addLocationForm);
    var location: Location = {
      locationId: 0,
      latitude: this.addLocationForm.get('latitude').value,
      longitude: this.addLocationForm.get('longitude').value,
      description: this.addLocationForm.get('location_description').value,
      country: this.addLocationForm.get('country').value,
    }
    this.locationService.addLocation(location)
    .subscribe((data) => {
      console.log(data);
    })
    this.addLocationForm.reset();
  }

  onDeleteLocation(){
    console.log(this.deleteLocationForm);
    var locationId: number = this.deleteLocationForm.get('id').value.match(/\d+/)[0];
    this.locationService.deleteLocation(locationId)
    .subscribe((data) => {
      console.log(data);
    });
    this.deleteLocationForm.reset();
  }

  onAddCategory(){
    console.log(this.addCategoryForm);
    this.categoryService.addCategory(this.addCategoryForm.get('name').value)
    .subscribe((data) => {
      console.log(data);
    });
    this.addCategoryForm.reset();
  }

  onDeleteCategory(){
    console.log(this.deleteCategoryForm);
    var genreId: number = this.deleteCategoryForm.get('id').value.match(/\d+/)[0];
    this.categoryService.deleteCategory(genreId)
    .subscribe((data) => {
      console.log(data);
    });
    this.deleteCategoryForm.reset();
  }

  onAddAdmin(){
    console.log(this.addAdminForm);
    var user: User = {
      id: 0,
      username: this.addAdminForm.get('login').value,
      email: this.addAdminForm.get('email').value,
      password: this.addAdminForm.get('password').value
    };
    this.userService.addAdmin(user).subscribe((data) => {
      console.log(data);
    });
    this.addAdminForm.reset();
  }

  onDeleteUser(){
    console.log(this.deleteUserForm);
    this.deleteUserForm.reset();
  }

  handleFile(files: FileList){
    this.uploadFile = files.item(0);
  }

}
