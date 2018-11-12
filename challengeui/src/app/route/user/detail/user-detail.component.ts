import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Subscription} from 'rxjs/Subscription';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {GenderEnum, UserResponse} from '../../../serviceObjects/userResponse';
import { UserService } from '../../../service/userService';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit, OnDestroy {
  private subscriptions: Subscription[] = [];

  public userId: string = null;
  public userForm: FormGroup;
  
  constructor(private router: Router, private activatedRoute: ActivatedRoute, private userservice: UserService) {
  }

  ngOnInit() {
    this.subscriptions.push(
      this.activatedRoute.params.subscribe(newParams => this.userId = newParams['id'])
    );
    this.createForm();
  }

  ngOnDestroy() {
    this.subscriptions.forEach(sub => sub.unsubscribe());
  }

  createForm() {
    this.userForm = new FormGroup({
      username: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      age: new FormControl(),
      gender: new FormControl('UNKNOWN')
    });

    if (this.userId !== 'new') {
      this.userservice.getUser(this.userId).then((user: UserResponse) => {
        this.userForm.get('username').patchValue(user.name);
        this.userForm.get('email').patchValue(user.mail);
        this.userForm.get('age').patchValue(user.age);
        this.userForm.get('gender').patchValue(user.gender);
      })
      .catch((error) => {
        console.log(error);
        alert("Catched exception by retrieving user details");
      })
    }
  }

  submitForm() {
    const value = this.userForm.getRawValue();

    if (this.userId === 'new') {
      // this.userservice.createuser
    } else {
      const user = {
        userId: this.userId,
        name: value.username,
        gender: value.gender,
        age: value.age,
        mail: value.email
      } as UserResponse;
      
      this.userservice.updateUser(user).then(() => {
        this.router.navigateByUrl('/users');
      })
      .catch((error) => {
        console.log(error);
        alert("Catched exception by updating user details");
      });
    }
  }

}
