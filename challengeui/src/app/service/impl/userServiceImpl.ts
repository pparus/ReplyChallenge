import {Injectable} from '@angular/core';
import {UserService} from '../userService';
import {HttpClient} from '@angular/common/http';
import {UserResponse} from '../../serviceObjects/userResponse';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';

@Injectable()
export class UserServiceImpl implements UserService {
  private allUsersSubject: BehaviorSubject<UserResponse[]> = new BehaviorSubject<UserResponse[]>(null);
  public allUser$ = this.allUsersSubject.asObservable();


  constructor(private _httpService: HttpClient) { }

  public getAllUsers(): void {
    this._httpService.get<UserResponse[]>('http://localhost:8080/user/v1/users').toPromise()
      .then((users: UserResponse[]) => {
        console.log(users);
        this.allUsersSubject.next(users);
      })
      .catch((error) => {
        console.log(error);
        alert('an error occured while getting users, check logs for details');
      });
  }

  public getUser(userId: String): Promise<UserResponse> {
    return this._httpService.get<UserResponse>('http://localhost:8080/user/v1/users/'+ userId).toPromise();

  }

  public removeUser(userId: string): Promise<any> {
    return this._httpService.delete('http://localhost:8080/user/v1/users/'+ userId).toPromise()
    .then(() => this.getAllUsers());
  }

  public updateUser(user: UserResponse): Promise<UserResponse> {
    return this._httpService.put<UserResponse>('http://localhost:8080/user/v1/users/'+ user.userId, user).toPromise();
  }

}
