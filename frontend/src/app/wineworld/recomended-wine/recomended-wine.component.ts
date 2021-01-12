import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RecomendedWineRequest } from 'src/app/models/recomended_wine_request';
import { RecomendedWineService } from 'src/app/services/recomended-wine.service';

@Component({
  selector: 'app-recomended-wine',
  templateUrl: './recomended-wine.component.html',
  styleUrls: ['./recomended-wine.component.css']
})
export class RecomendedWineComponent implements OnInit {
  recomend_wines_form: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private wineRecomendationService: RecomendedWineService

  ) {
    this.recomend_wines_form = this.formBuilder.group({
      wine_genre: [null, Validators.required],
      wine_max_price: [null, Validators.required],
      wine_min_rating: [null, Validators.required],
      number_of_wines: [null, Validators.required]
    })
   }

  ngOnInit(): void {
  }

  onRecomendWines() {
    var recomendedWineRequest: RecomendedWineRequest = {
      genre: this.recomend_wines_form.get('wine_genre').value,
      price: this.recomend_wines_form.get('wine_max_price').value,
      rating: this.recomend_wines_form.get('wine_min_rating').value,
      quantity: this.recomend_wines_form.get('number_of_wines').value
    }

    this.wineRecomendationService.getRecomendedWines(recomendedWineRequest).subscribe((data)=>{
      console.log(data);
    });
  }
}
