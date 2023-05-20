import { Component, OnInit } from '@angular/core';
import { LoginserviceService } from './../loginmodal/loginservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  isLoginPage: boolean = false;

  constructor(public loginservice : LoginserviceService, public router: Router){}

  ngOnInit() {
    this.isLoginPage = this.router.url === '/login';
  }

  logOut(){
    localStorage.removeItem('is_logged_in');
    localStorage.clear();
    this.loginservice.logout();
  }
}

