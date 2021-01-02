import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Location } from 'src/app/models/location';

@Injectable({
  providedIn: 'root',
})
export class LocationsService {
  private getLocationsUrl = 'http://localhost:8080/locations/get/all';
  private getLocationUrl = 'http://localhost:8080/locations/get/';
  private addLocationUrl = 'http://localhost:8080/locations/add';
  private updateLocationUrl = 'http://localhost:8080/locations/update/';
  private deleteLocationUrl = 'http://localhost:8080/locations/delete/';

  constructor(private http: HttpClient) {}

  getAllLocations(): Observable<Location[]>{
    return this.http.get<Location[]>(this.getLocationsUrl);
  }

  getLocation(locationId: number) {
    return this.http.get(this.getLocationUrl + locationId.toString());
  }

  addLocation(location: Location) {
    return this.http.post(this.addLocationUrl, location, {withCredentials: true});
  }

  updateLocation(location: Location) {
    return this.http.patch(this.updateLocationUrl, location);
  }

  deleteLocation(locationId: number) {
    return this.http.delete(this.deleteLocationUrl + locationId.toString(), {withCredentials: true, observe: 'response'});
  }
}
