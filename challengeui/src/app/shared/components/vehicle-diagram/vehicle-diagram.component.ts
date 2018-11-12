import { Component, OnInit, Input, OnChanges } from '@angular/core';
import { VehicleResponse } from '../../../serviceObjects/vehicleReponse';

@Component({
  selector: 'app-vehicle-diagram',
  templateUrl: './vehicle-diagram.component.html',
  styleUrls: ['./vehicle-diagram.component.css']
})
export class VehicleDiagramComponent {
  @Input() vehicles: VehicleResponse[] = null;

  public mouseOver: string = null;

  constructor() { }

}
