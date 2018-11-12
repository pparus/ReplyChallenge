import {Observable} from 'rxjs/Observable';
import { VehicleResponse } from '../serviceObjects/vehicleReponse';

export abstract class VehicleService {
  abstract allVehicles$: Observable<VehicleResponse[]>;
  
  abstract getAllVehicles(): void;

  abstract getVehicle(vehicleId: string): Promise<VehicleResponse>;

  abstract startPolling(): void;
  abstract stopPolling(): void;
}
