import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RecomendedWineRequest } from 'src/app/models/recomended_wine_request';
import { RecomendedWineResponse } from 'src/app/models/recomended_wine_response';
import { RecomendedWineService } from 'src/app/services/recomended-wine.service';

@Component({
  selector: 'app-recomended-wine',
  templateUrl: './recomended-wine.component.html',
  styleUrls: ['./recomended-wine.component.css']
})
export class RecomendedWineComponent implements OnInit {
  recomend_wines_form: FormGroup;
  recomended_wines: RecomendedWineResponse[];

  constructor(
    private formBuilder: FormBuilder,
    private wineRecomendationService: RecomendedWineService,
    private router: Router,


  ) {
    this.recomend_wines_form = this.formBuilder.group({
      wine_genre: [null, Validators.required],
      wine_max_price: [null, Validators.required],
      wine_min_rating: [null, Validators.required],
      number_of_wines: [null, Validators.required]
    });

    this.recomended_wines = this.wineRecomendationService.recomended_wines;
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
      this.wineRecomendationService.recomended_wines = data.recommendedWines;
      
      console.log(data);
      var url = this.router.url;
      this.router.navigateByUrl(url, { skipLocationChange: true }).then(() => {
        this.router.navigate([url]);
      });
    });    
  }
}
