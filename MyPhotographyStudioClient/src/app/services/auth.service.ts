import { Injectable, NgZone } from '@angular/core';
import { GoogleAuthProvider } from 'firebase/auth';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';
import axios from 'axios';
import firebase from 'firebase/compat/app';
// import { User } from '../interfaces/user';

// import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // user$: Observable<User>;

  constructor(
    public router: Router,
    public ngZone: NgZone,
    public afAuth: AngularFireAuth // Inject Firebase auth service
  ) {
    // this.afAuth.authState.subscribe(user => {
    //   this.user = user;
    // })
  }

  async createUser() {
    const data = {
      email: firebase.auth().currentUser?.email,
      displayName: firebase.auth().currentUser?.displayName,
      photoUrl: firebase.auth().currentUser?.photoURL,
    };
    console.log(data);
    try {
      const response = await axios.post(
        'http://localhost:8080/api/users/create',
        data
      );
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  }

  // Sign in with Google
  GoogleAuth() {
    return this.AuthLogin(new GoogleAuthProvider())
      .then((res) => {
        this.ngZone.run(() => {
          this.createUser();
          this.router.navigate(['dashboard']);
        });
      })
      .catch((error) => {
        window.alert(error);
      });
  }

  // Auth logic to run auth providers
  AuthLogin(provider: any) {
    return this.afAuth
      .signInWithPopup(provider)
      .then((result) => {
        console.log('You have been successfully logged in!');
      })
      .catch((error) => {
        console.log(error);
      });
  }
}
