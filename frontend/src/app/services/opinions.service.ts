import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Opinion } from '../models/opinion';

@Injectable({
  providedIn: 'root',
})
export class OpinionsService {
  private addOpinionUrl = 'http://localhost:8080/opinion/post';
  private deleteOpinionUrl = 'http://localhost:8080/delete/opinion/';
  private getProductOpinionsUrl = 'http://localhost:8080/opinion/get/products/';
  private updateOpinionUrl = 'http://localhost:8080/update/opinion/';

  constructor(private http: HttpClient) {}

  addOpinion(userId: number, opinion: Opinion) {
    return this.http.post(
      this.addOpinionUrl, opinion,
      {withCredentials: true}
    );
  }

  deleteOpinion(userId: number, productId: number) {
    return this.http.delete(
      this.deleteOpinionUrl + userId.toString() + '/' + productId.toString
    );
  }

  getProductOpinions(productId: number): Observable<Opinion[]> {
    return this.http.get<Opinion[]>(this.getProductOpinionsUrl + productId.toString());
  }

  updateOpinion(userId: number, productId: number, opinion: Opinion) {
    return this.http.patch(
      this.updateOpinionUrl + userId.toString() + '/' + productId.toString,
      opinion
    );
  }
}
