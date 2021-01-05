import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.css']
})
export class UserPanelComponent implements OnInit {
  isEditable: boolean = false;
  buttonText: String = "Edit";
  account_information_form: FormGroup;


  constructor(private formBuilder: FormBuilder) { 

  }

  ngOnInit(): void {
    this.account_information_form = this.formBuilder.group({
      e_mail: [null, Validators.required],
      username: [null, Validators.required]
    })
  }

  onEdit() {
    console.log("IS EDITABLE? : " + this.isEditable);
    this.isEditable = !this.isEditable;
    if(this.isEditable) {
      this.buttonText = "Save";
    }
    else {
      this.buttonText = "Edit";
    }
  }
}
