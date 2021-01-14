import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RecomendedWineRequest } from '../models/recomended_wine_request';
import { RecomendedWineResponse } from '../models/recomended_wine_response';
import { RecomendedWineResponseContainer } from '../models/recomended_wine_response_container';

@Injectable({
  providedIn: 'root'
})
export class RecomendedWineService {

  recomended_wines: RecomendedWineResponse[];


  private recomendedWineApiUrl = 'https://api.spoonacular.com/food/wine/recommendation?apiKey=fa60382788994dd49e12f80369e7b867';

  constructor(private http: HttpClient) {}

  getRecomendedWines(recomendedWineRequest: RecomendedWineRequest): Observable<RecomendedWineResponseContainer> {
    var finalRequest = this.recomendedWineApiUrl + "&wine=" + recomendedWineRequest.genre + "&maxPrice=" + recomendedWineRequest.price + "&minRating=" + recomendedWineRequest.rating + "&number=" + recomendedWineRequest.quantity;


    return this.http.get<RecomendedWineResponseContainer>(finalRequest);

  }
}
