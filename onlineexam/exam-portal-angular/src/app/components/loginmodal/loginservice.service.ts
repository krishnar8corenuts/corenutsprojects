import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class LoginserviceService {
  isLoggedIn = false;
  constructor(private router: Router) {
    this.isLoggedIn = !!localStorage.getItem('is_logged_in');
  }

  login() {
    localStorage.setItem('is_logged_in', 'true');
    this.isLoggedIn = true;
  }

  logout() {
    swal.fire({
      title: "Are you sure you want to logout?",
      text: "Are you sure you want to logout?",

      icon: "warning",
      showCancelButton: true,
      confirmButtonText: 'Yes, logout.',
      cancelButtonText: 'No, let me think',

 
    })
    .then((logOutConfirmed) => {
      if (logOutConfirmed) {
        localStorage.removeItem('is_logged_in');
        this.isLoggedIn = false;
        this.router.navigate(['/login']);
      } else {
        localStorage.setItem('is_logged_in', 'true');
        this.isLoggedIn = true;
      }
    });
}
}
