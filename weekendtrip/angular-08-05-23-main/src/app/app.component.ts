import { Component } from '@angular/core';
import { WeekendRepository } from './model/weekend.repository';
import {OnInit} from '@angular/core'
@Component({
  selector: 'city-app',
  templateUrl:'app.component.html',
  styleUrls:['app.component.css']
})
export class AppComponent implements OnInit{
  title = 'WeekEndTrips';
  isLoggedin:boolean=false
  constructor(public repo:WeekendRepository){
    this.isLoggedin = this.repo.isloggedin()
    console.log(this.isLoggedin)
  }
  ngOnInit():void{
    this.isLoggedin = this.repo.isloggedin()
    this.repo.loginStatusSubject.asObservable().subscribe(data=>{
      console.log(this.repo.isloggedin())
       this.isLoggedin = this.repo.isloggedin()
       
    })
    }
}
  // template:"<div>placeholder root</div>"
