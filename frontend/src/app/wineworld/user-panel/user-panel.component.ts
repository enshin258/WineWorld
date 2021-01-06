import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.css']
})
export class UserPanelComponent implements OnInit {
  isEditable: boolean = false;
  buttonText: string = "Edit";
  account_information_form: FormGroup;
  isUserLoggedIn = false;
  formChanged= false;

  constructor(private userService: UsersService,
    private formBuilder: FormBuilder) {
      this.isUserLoggedIn = (this.userService.role != null);
    }

  ngOnInit(): void {
    this.account_information_form = this.formBuilder.group({
      e_mail: [null, Validators.required],
      username: [null, Validators.required],
      password: [null, Validators.required]
    })
  }

  onEdit() {
    this.isEditable = !this.isEditable;
    if(this.isEditable) {
      this.buttonText = "Save";
    }
    else {
      if(this.account_information_form.touched && this.formChanged){
        console.log('time to post changes');
      }
      this.buttonText = "Edit";
      this.formChanged = false;
      this.account_information_form.reset();
    }
  }

  onReject(){
    this.isEditable = false;
    this.account_information_form.reset();
    this.buttonText = "Edit";
    this.formChanged = false;
  }

  onChange(event){
    this.formChanged = true;
  }
}
