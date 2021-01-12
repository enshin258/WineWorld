import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-age-dialog',
  templateUrl: './age-dialog.component.html',
  styleUrls: ['./age-dialog.component.css']
})
export class AgeDialogComponent implements OnInit {
  age = {};
  month = 0;
  day = 0;
  year = 0;

  constructor() { }

  

  ngOnInit(): void {
    var modal = document.getElementById('age-modal')

    var submitButton = document.getElementById('age-submit')
    submitButton.onclick = function(event) {
      modal.style.display = 'none';
    }
  }


}
