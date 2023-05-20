import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-sidenav',
  templateUrl: './admin-sidenav.component.html',
  styleUrls: ['./admin-sidenav.component.css']
})
export class AdminSidenavComponent {
  constructor(private router:Router){}

  name:string='';
  value:string='';

  clickEvent(name:any){
    console.log(name);
    if(name=='result'){
      this.router.navigateByUrl("adminpage/result");
    }
    if(name=='questionbank'){
      this.router.navigate(["adminpage/questionbank"]);
    }
    if(name=='settings'){
      this.router.navigate(["adminpage/settings"]);
    }
    if(name=='createpaper'){
      this.router.navigate(["adminpage/createpaper"]);
    }
    if(name=='scheduleexam'){
      this.router.navigate(["adminpage/scheduleexam"]);
    }
    if(name=='users'){
      this.router.navigate(["adminpage/allusers"]);
    }
  }






}
