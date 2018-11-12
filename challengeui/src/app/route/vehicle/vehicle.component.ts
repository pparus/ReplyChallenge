import { Component, OnInit, OnDestroy } from '@angular/core';
import { VehicleService } from '../../service/vehicleService';
import { Subscription } from 'rxjs/Subscription';
import { VehicleResponse } from '../../serviceObjects/vehicleReponse';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css']
})
export class VehicleComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription[] = [];

  public allVehicles: VehicleResponse[];

  constructor(private vehicleService: VehicleService) { }

  ngOnInit() {
    this.vehicleService.getAllVehicles();
    this.subscriptions.push(
      this.vehicleService.allVehicles$.subscribe(allVehicles => this.allVehicles = allVehicles)
    );
    this.vehicleService.startPolling();
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
    this.vehicleService.stopPolling();
  }

}
