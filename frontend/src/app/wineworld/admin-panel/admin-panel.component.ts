import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

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
  deleteAdminForm: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

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

    this.deleteAdminForm = this.formBuilder.group({
      login: [null, Validators.required],
    });
  }

  onAddWine(){
    console.log(this.addWineForm);
    this.addWineForm.reset();
  }

  onDeleteWine(){
    console.log(this.deleteWineForm);
    this.deleteWineForm.reset();
  }

  onAddLocation(){
    console.log(this.addLocationForm);
    this.addLocationForm.reset();
  }

  onDeleteLocation(){
    console.log(this.deleteLocationForm);
    this.deleteLocationForm.reset();
  }

  onAddCategory(){
    console.log(this.addCategoryForm);
    this.addCategoryForm.reset();
  }

  onDeleteCategory(){
    console.log(this.deleteCategoryForm);
    this.deleteCategoryForm.reset();
  }

  onAddAdmin(){
    console.log(this.addAdminForm);
    this.addAdminForm.reset();
  }

  onDeleteAdmin(){
    console.log(this.deleteAdminForm);
    this.deleteAdminForm.reset();
  }

}
