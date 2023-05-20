// import { HttpClient } from '@angular/common/http';
// import { ScheduleExam } from './../../../../model/model/ScheduleExam';
// import { Component, OnInit } from '@angular/core';
 // @Component({
//   selector: 'app-schedule-exam',
//   templateUrl: './schedule-exam.component.html',
//   styleUrls: ['./schedule-exam.component.css']
// })
// export class ScheduleExamComponent implements OnInit {

//   scheduleExam:ScheduleExam=new ScheduleExam();
//   exams:ScheduleExam[]=[];
//   addexam:boolean=true;
//   constructor(private http:HttpClient) { }

//   ngOnInit() {

//    this.fetchExam();
//   }
//   loadAddExampage(flag:boolean){

//     this.addexam=flag;
//   }

//   fetchExam(){
//     this.http.get<ScheduleExam[]>(`http://34.138.180.59:8089/api/getallexams`).subscribe(data=>{
//     this.exams=data;
//   });
//   }

//   deleteexam(id:any){
//         this.http.delete(`http://34.138.180.59:8089/api/deleteExam/${id}`).subscribe(data=>{
//         //   console.log(data);
//         //   this.ngOnInit();
//         // })
//   }
//   deleteexam(id:any){

//     swal({
//       title: "Are you sure you want to Delete? ",
//       icon: "warning",
//       buttons: ['Cancel', 'Yes, Delete'],
//       dangerMode: true,
//     })
//     .then((deleteConfirmed: any) => {
//       if (deleteConfirmed) {
//         this.deleteexam(id,id2).subscribe(
//       reponse=>{
//         console.log(reponse);
//         swal("Deleted successfully", '', "success");
//         console.log(id);
//         console.log(id2);
//         this.ngOnInit();
//       }
//       );
//       } else {
//       }
//        });

//     }

// }

import { HttpClient } from '@angular/common/http';
import { ScheduleExam } from './../../../../model/model/ScheduleExam';
import { Component, OnInit, ViewChild } from '@angular/core';
import swal from 'sweetalert2';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
@Component({
  selector: 'app-schedule-exam',
  templateUrl: './schedule-exam.component.html',
  styleUrls: ['./schedule-exam.component.css']
})
export class ScheduleExamComponent implements OnInit {

  scheduleExam:ScheduleExam=new ScheduleExam();
  exams:ScheduleExam[]=[];
  addexam:boolean=true;
  dataSource = new MatTableDataSource<ScheduleExam>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(private http:HttpClient) { }

  ngOnInit() {

   this.fetchExam();
  }
  loadAddExampage(flag:boolean){

    this.addexam=flag;
  }

  fetchExam(){
    this.http.get<ScheduleExam[]>(`http://34.138.180.59:8089/api/getallexams`).subscribe(data=>{
    this.exams=data;
    this.dataSource.data=this.exams
    this.dataSource.paginator = this.paginator;
  });
  }
  deleteexam(id:any){
       return this.http.delete(`http://34.138.180.59:8089/api/deleteExam/${id}`);

  }
  delete(id:any){

    swal.fire({
      



      title: 'Are you sure?',
      text: 'This process is irreversible.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',

      
     })
    .then((deleteConfirmed: any) => {
      if (deleteConfirmed) {
        this.deleteexam(id).subscribe(
      reponse=>{
        console.log(reponse);
        swal.fire("Deleted successfully", '', "success");
        console.log(id);
       
        this.ngOnInit();
      }
      );
      } else {
      }
       });

    }

}
