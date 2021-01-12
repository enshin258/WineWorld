import { Component, OnInit } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {
  map: any;

  constructor() { }

  ngOnInit(): void {
    this.mapSetup();
  }

  mapSetup() {
    this.map = L.map('map').setView(
      [52.2531, 20.9004],
      13
    );

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution:
        '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors',
    }).addTo(this.map);

    var marker = L.marker([
      52.2531,
      20.9004,
    ]).addTo(this.map);


    
    marker
      .bindPopup(
        '<b>WineWorld shop :)</b> </br><span> gen. Sylwestra Kaliskiego 2, 00-908 Warszawa <span>'
      )
      .openPopup();


      L.Routing.control({
        waypoints: [
            L.latLng(57.74, 11.94),
            L.latLng(57.6792, 11.949)
        ]
    }).addTo(this.map);
  }

}
