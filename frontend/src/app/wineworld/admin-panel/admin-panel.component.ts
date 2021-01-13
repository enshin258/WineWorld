import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Category } from 'src/app/models/category';
import { Location } from 'src/app/models/location';
import { Product } from 'src/app/models/product';
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

  //update wine
  isUpdateWineEditable: boolean = false;
  updateWineButtonText: string = "Edit";
  updateWineForm: FormGroup;
  updateWineFormChanged= false;
  updateWineId: number;
  uploadUpdateFile: File;
  updatedWineData: Product = {
    productId: 0,
    name: 'Name',
    price: 0,
    pictureUrl: '',
    genreId: 0,
    genreName: 'Genre',
    productDescription: 'Description',
    locationId: 0,
    latitude: 0,
    longitude: 0,
    description: '',
    country: '',
    producer: 'Producer',
    alcoholLevel: 0,
    year: 0,
    volume: 0
  };
  updatedProductLocation: Location = {
    locationId: 0,
    latitude: 0,
    longitude: 0,
    description: 'Description',
    country: 'Country'
  };
  updatedProductCategory: Category = {
    genreId: 0,
    name: 'Category'
  };

  //update location
  isUpdateLocationEditable: boolean = false;
  updateLocationButtonText: string = "Edit";
  updateLocationForm: FormGroup;
  updateLocationFormChanged= false;
  updateLocationId: number;
  updatedLocationData: Location = {
    locationId: 0,
    latitude: 0,
    longitude: 0,
    description: 'Description',
    country: 'Country'
  };

  //update category
  isUpdateCategoryEditable: boolean = false;
  updateCategoryButtonText: string = "Edit";
  updateCategoryForm: FormGroup;
  updateCategoryFormChanged= false;
  updateCategoryId: number;

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
  isAdmin = false;

  constructor(private categoryService: CategoryService, 
    private locationService: LocationsService,
    private userService: UsersService,
    private formBuilder: FormBuilder, private productService: ProductsService) {
      
      this.isAdmin = (this.userService.loginData.roleId == 2);

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

    this.updateWineForm = this.formBuilder.group({
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

    this.updateLocationForm = this.formBuilder.group({
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

    this.updateCategoryForm = this.formBuilder.group({
      name: [null, Validators.required],
    });
    this.updateCategoryForm.reset();

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

  onWineUpdateFormEdit() {
    this.isUpdateWineEditable = !this.isUpdateWineEditable;
    if(this.isUpdateWineEditable) {
      this.updateWineButtonText = "Save";
    }
    else {
      if(this.updateWineForm.touched && this.updateWineFormChanged){

        var locationId: number;
        var genreId: number;
        try {
          locationId = this.addWineForm.get('location').value.match(/\d+/)[0];
        } catch (error) {
          locationId = this.updatedWineData.locationId;
        }

        try {
          genreId = this.addWineForm.get('category').value.match(/\d+/)[0];
        } catch (error) {
          genreId = this.updatedWineData.genreId;
        }

        var modifiedProduct: Add_product = {
          name: this.updatedWineData.name,
          price: this.updatedWineData.price,
          picture: null,
          genreId: this.updatedWineData.genreId,
          productDescription: this.updatedWineData.productDescription,
          locationId: this.updatedWineData.locationId,
          producer: this.updatedWineData.producer,
          alcoholLevel: this.updatedWineData.alcoholLevel,
          year: this.updatedWineData.year,
          volume: this.updatedWineData.volume
        };
        
        if(this.updateWineForm.get('name').value != null) modifiedProduct.name = this.updateWineForm.get('name').value;
        if(this.updateWineForm.get('price').value != null) modifiedProduct.price = this.updateWineForm.get('price').value;
        if(this.updateWineForm.get('description').value != null) modifiedProduct.productDescription = this.updateWineForm.get('description').value;
        if(this.updateWineForm.get('producer').value != null) modifiedProduct.producer = this.updateWineForm.get('producer').value;
        if(this.updateWineForm.get('alcohol_level').value != null) modifiedProduct.alcoholLevel = this.updateWineForm.get('alcohol_level').value;
        if(this.updateWineForm.get('year').value != null) modifiedProduct.year = this.updateWineForm.get('year').value;
        if(this.updateWineForm.get('volume').value != null) modifiedProduct.volume = this.updateWineForm.get('volume').value;
        if(this.uploadUpdateFile != null) modifiedProduct.picture = this.uploadUpdateFile;
        modifiedProduct.genreId = genreId;
        modifiedProduct.locationId =locationId;


        console.log(modifiedProduct);

        this.productService.updateProduct(modifiedProduct, this.updatedWineData.productId).subscribe((data) => {
          console.log(data);
        })
      }
      this.updateWineButtonText = "Edit";
      this.updateWineFormChanged = false;
      this.updateWineForm.reset();
    }
  }

  onLocationUpdateFormEdit() {
    this.isUpdateLocationEditable = !this.isUpdateLocationEditable;
    if(this.isUpdateLocationEditable) {
      this.updateLocationButtonText = "Save";
    }
    else {
      if(this.updateLocationForm.touched && this.updateLocationFormChanged){
        console.log('time to post changes in locations');
        this.updatedLocationData.locationId = this.updateLocationId;
        if(this.updateLocationForm.get('latitude').value != null) this.updatedLocationData.latitude = this.updateLocationForm.get('latitude').value;
        if(this.updateLocationForm.get('longitude').value != null) this.updatedLocationData.longitude = this.updateLocationForm.get('longitude').value;
        if(this.updateLocationForm.get('location_description').value != null) this.updatedLocationData.description = this.updateLocationForm.get('location_description').value;
        if(this.updateLocationForm.get('country').value != null) this.updatedLocationData.country = this.updateLocationForm.get('country').value;
        console.log(this.updatedLocationData);
        this.locationService.updateLocation(this.updatedLocationData).subscribe((data) => {
          console.log(data);
        });
      }
      this.updateLocationButtonText = "Edit";
      this.updateLocationFormChanged = false;
      this.updateLocationForm.reset();
    }
  }

  onCategoryUpdateFormEdit() {
    this.isUpdateCategoryEditable = !this.isUpdateCategoryEditable;
    if(this.isUpdateCategoryEditable) {
      this.updateCategoryButtonText = "Save";
    }
    else {
      if(this.updateCategoryForm.touched && this.updateCategoryFormChanged){
        console.log('time to post changes in categories');
        this.categoryService.updateCategory(this.updateCategoryId, this.updateCategoryForm.get('name').value)
        .subscribe((data) => {
          console.log(data);
        })
      }
      this.updateCategoryButtonText = "Edit";
      this.updateCategoryFormChanged = false;
      this.updateCategoryForm.reset();
    }
  }

  onWineUpdateReject() {
    this.isUpdateWineEditable = false;
    this.updateWineForm.reset();
    this.updateWineButtonText = "Edit";
    this.updateWineFormChanged = false;
  }

  onLocationUpdateReject() {
    this.isUpdateLocationEditable = false;
    this.updateLocationForm.reset();
    this.updateLocationButtonText = "Edit";
    this.updateLocationFormChanged = false;
  }

  onCategoryUpdateReject() {
    this.isUpdateCategoryEditable = false;
    this.updateCategoryForm.reset();
    this.updateCategoryButtonText = "Edit";
    this.updateWineFormChanged = false;
  }

  onUpdateWineChange(event){
    this.updateWineFormChanged = true;
  }

  onUpdateCategoryChange(event){
    this.updateCategoryFormChanged = true;
  }

  onUpdateLoactionChange(event){
    this.updateLocationFormChanged = true;
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
    this.productService.deleteProduct(this.deleteWineForm.get('id').value).subscribe((data) => {
      console.log(data);
    });
    this.deleteWineForm.reset();   //po tym rzuca nullem ???
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
      login: this.addAdminForm.get('login').value,
      email: this.addAdminForm.get('email').value,
      password: this.addAdminForm.get('password').value,
      roleName: ''
    };
    this.userService.addAdmin(user).subscribe((data) => {
      console.log(data);
    });
    this.addAdminForm.reset();
  }

  onDeleteUser(){
    console.log(this.deleteUserForm);
    this.userService.deleteUser(this.deleteUserForm.get('login').value).
    subscribe((data) => {
      console.log(data);
    });
    this.deleteUserForm.reset();
  }

  handleFile(files: FileList){
    this.uploadFile = files.item(0);
  }

  handleUploadFile(files: FileList){
    this.updateWineFormChanged = true;
    this.uploadUpdateFile = files.item(0);
  }

  onUpdateCategoryIdChange(value: string){
    this.updateCategoryId = +value.match(/\d+/)[0];
    console.log(this.updateCategoryId);
  }

  onUpdateLocationIdChange(value: string){
    this.updateLocationId = +value.match(/\d+/)[0];
    console.log(this.updateLocationId);
    this.locationService.getLocation(this.updateLocationId).subscribe((data) => {
      this.updatedLocationData = data;
      console.log(this.updatedLocationData);
    });
  }

  onUpdateWineIdChange(value: string){
    console.log(value);
    this.updateWineId = +value;
    this.productService.getProduct(this.updateWineId).subscribe((data) => {
      this.updatedWineData = data;
      console.log(this.updatedWineData);
      this.categories.forEach((category) => {
        if(category.genreId == this.updatedWineData.genreId){
          this.updatedProductCategory = category;
        }   
      });
      this.locations.forEach((location) => {
        if(location.locationId == this.updatedWineData.locationId){
          this.updatedProductLocation = location;
        }
      });
    })
  }

}
