import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import { VehicleService } from '../vehicleService';
import { VehicleResponse } from '../../serviceObjects/vehicleReponse';

@Injectable()
export class VehicleServiceImpl implements VehicleService {
  private pollingInterval: any // NodeJS.Timer;

  private allVehiclesSubject: BehaviorSubject<VehicleResponse[]> = new BehaviorSubject<VehicleResponse[]>(null);
  public allVehicles$ = this.allVehiclesSubject.asObservable();


  constructor(private _httpService: HttpClient) { }

  public getAllVehicles(): void {
    this._httpService.get<VehicleResponse[]>('http://localhost:8080/vehicle/v1/vehicles').toPromise()
      .then((users: VehicleResponse[]) => {
        console.log(users);
        this.allVehiclesSubject.next(users);
      })
      .catch((error) => {
        console.log(error);
        alert('an error occured while getting vehicles, check logs for details');
      });
  }

  public getVehicle(vehicleId: String): Promise<VehicleResponse> {
    return this._httpService.get<VehicleResponse>('http://localhost:8080/vehicle/v1/vehicles'+ vehicleId).toPromise();
  }

  public startPolling() {
    if (this.pollingInterval) {
      this.stopPolling();
    }
    this.pollingInterval = setInterval(() => {
      this.getAllVehicles();
    }, 2000);
  }

  public stopPolling() {
    if (this.pollingInterval) {
      clearInterval(this.pollingInterval);
    }
  }

}
