import {UserResponse} from '../serviceObjects/userResponse';
import {Observable} from 'rxjs/Observable';

export abstract class UserService {
  abstract allUser$: Observable<UserResponse[]>;
  
  abstract getAllUsers(): void;

  abstract getUser(userId: string): Promise<UserResponse>;

  abstract removeUser(userId: string): Promise<any> ;

  abstract updateUser(user: UserResponse): Promise<UserResponse>;
}
