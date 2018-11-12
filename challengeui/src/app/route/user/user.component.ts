import {Component, OnDestroy, OnInit} from '@angular/core';
import {UserResponse} from '../../serviceObjects/userResponse';
import {UserService} from '../../service/userService';
import {Subscription} from 'rxjs/Subscription';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']

})

export class UserComponent implements OnInit, OnDestroy {
  public users: UserResponse[] = [];
  public subscriptions: Subscription[] = [];

  constructor(private userService: UserService) {}

  ngOnInit(): void {
    this.subscriptions.push(
      this.userService.allUser$.subscribe(newUsers => this.users = newUsers)
    );
    this.userService.getAllUsers();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  removeUser(userId: string) {
    if (confirm('Do you really want to remove the user?')) {
      this.userService.removeUser(userId)
    }
  }

}
