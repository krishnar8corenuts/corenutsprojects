import { HttpClient } from '@angular/common/http';
import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {  FormBuilder,FormGroup, Validators } from '@angular/forms';
import { Subject } from 'src/app/model/model/Subject';
import swal from 'sweetalert2';

@Component({
  selector: 'app-AddSubject',
  templateUrl: './AddSubject.component.html',
  styleUrls: ['./AddSubject.component.css']
})
export class AddSubjectComponent implements OnInit {
  myForm:any=FormGroup;
  submitted=false;
  objectId!: string;
  objectData: any = {};
  isAddOperation?: boolean;
  subject:Subject=new Subject();
  inputvalue?:string;
@Input() id:any;
@Output("loadAddSubjectPage") loadAddSubjectPage=new EventEmitter
Subjects:Subject = new Subject();
  constructor(private http:HttpClient, private router:Router,private formbBuilder:FormBuilder,private route: ActivatedRoute) {}

  get f(){
   return this.myForm.controls;
  }

  ngOnInit() {
    console.log(this.id);
   this.isAddOperation = this.route.snapshot.queryParams['action'] === 'add';
   this.subject.name='';
   console.log(this.isAddOperation)
   console.log("---")

   if (!this.isAddOperation && this.id) {
   this.http
   .get<Subject>(`http://34.138.180.59:8089/api/getSubjectById/${this.id}`)
   .subscribe((data) => {
     this.subject = data;
     console.log(this.subject);
   });}
   this.myForm=this.formbBuilder.group({
    name: ['', Validators.required],
    description:['', Validators.required],
   });
  }

  onSubmit(){
    if(this.id){
      this.submitted=true;
    if (this.myForm.invalid) {
      console.log(this.myForm);
      console.log("false");
      return;
    }
    if(this.submitted)
     {
      console.log(this.myForm);
      this.editSubjectInfo();
     }

    }
    else{
    this.submitted=true;
    if (this.myForm.invalid) {
      console.log(this.myForm);
      console.log("false");
      return;
    }
    if(this.submitted)
     {
      console.log(this.myForm);
      this.addSubjectInfo();
     }
    }

  }


//add Category info
addSubjectInfo()
{
  console.log(this.myForm.value.name);


  this.http.post<any>('http://34.138.180.59:8089/api/addsubject',this.myForm.value).subscribe(
    response=>{
      swal.fire("Subject added successfully","", "success");
      this.goBack();
    }
  );
}
//edit Category info
editSubjectInfo()
{
  console.log(this.myForm);
  this.http.put<any>(`http://34.138.180.59:8089/api/updatesubject/${this.id}`,this.myForm.value).subscribe(
    response=>{
      swal.fire("Subject updated successfully","", "success");
      this.goBack();
    }
  );
}

goBack(){
this.loadAddSubjectPage.emit(true);
location.reload();
}

}


