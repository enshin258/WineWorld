import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Opinion } from '../models/opinion';

@Injectable({
  providedIn: 'root',
})
export class OpinionsService {
  private addOpinionUrl = 'http://localhost:8080/add/opinion/';
  private deleteOpinionUrl = 'http://localhost:8080/delete/opinion/';
  private getOpinionUrl = 'http://localhost:8080/get/opinion/';
  private updateOpinionUrl = 'http://localhost:8080/update/opinion/';

  constructor(private http: HttpClient) {}

  addOpinion(userId: number, productId: number, opinion: Opinion) {
    return this.http.post(
      this.addOpinionUrl + userId.toString() + '/' + productId.toString,
      opinion
    );
  }

  deleteOpinion(userId: number, productId: number) {
    return this.http.delete(
      this.deleteOpinionUrl + userId.toString() + '/' + productId.toString
    );
  }

  getOpinion(userId: number, productId: number) {
    return this.http.get(
      this.getOpinionUrl + userId.toString() + '/' + productId.toString
    );
  }

  updateOpinion(userId: number, productId: number, opinion: Opinion) {
    return this.http.patch(
      this.updateOpinionUrl + userId.toString() + '/' + productId.toString,
      opinion
    );
  }
}
