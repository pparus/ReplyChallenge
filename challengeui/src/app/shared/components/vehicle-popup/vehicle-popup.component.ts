import { Component, OnInit, Input } from '@angular/core';
import { VehicleResponse } from '../../../serviceObjects/vehicleReponse';

@Component({
  selector: 'app-vehicle-popup',
  templateUrl: './vehicle-popup.component.html',
  styleUrls: ['./vehicle-popup.component.css']
})
export class VehiclePopupComponent implements OnInit {
  @Input() vehicle: VehicleResponse;

  constructor() { }

  ngOnInit() {
  }

}
