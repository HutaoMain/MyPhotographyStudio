import { Injectable, NgZone } from '@angular/core';
import { GoogleAuthProvider, FacebookAuthProvider } from 'firebase/auth';
import { AngularFireAuth } from '@angular/fire/compat/auth';
import { Router } from '@angular/router';
import axios from 'axios';
import firebase from 'firebase/compat/app';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(
    public router: Router,
    public ngZone: NgZone,
    public afAuth: AngularFireAuth // Inject Firebase auth service
  ) {}

  async createUser() {
    const data = {
      email: firebase.auth().currentUser?.email,
      displayName: firebase.auth().currentUser?.displayName,
      photoUrl: firebase.auth().currentUser?.photoURL,
    };
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
          this.router.navigate(['']);
        });
      })
      .catch((error) => {
        window.alert(error);
      });
  }

  // Sign in with Facebook
  FacebookAuth() {
    return this.AuthLogin(new FacebookAuthProvider())
      .then((res) => {
        this.ngZone.run(() => {
          this.createUser();
          this.router.navigate(['']);
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

  //  // Auth logic to run auth providers
  //  AuthLoginFacebook(provider: any) {
  //   return this.afAuth
  //     .signInWithPopup(provider)
  //     .then((result) => {
  //       console.log('You have been successfully logged in!');
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // }
}
