import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { CreatePaper } from 'src/app/model/model/CreatePaper';
import swal from 'sweetalert2';
import { MatPaginator } from '@angular/material/paginator';
@Component({
  selector: 'app-createPaper',
  templateUrl: './createPaper.component.html',
  styleUrls: ['./createPaper.component.css']
})
export class CreatePaperComponent implements OnInit {

  createPaper:CreatePaper=new CreatePaper();
  papers:CreatePaper[]=[];
  addpaper:boolean=true;
  viewpaper:boolean=true;
  paperid?:number;
  dataSource = new MatTableDataSource<CreatePaper>([]);
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  constructor(private http:HttpClient) { }

  ngOnInit() {
  this.fetchPaper();
  }
  loadAddPaperpage(flag:boolean){

    this.addpaper=flag;
  }

  fetchPaper(){
    this.http.get<CreatePaper[]>(`http://34.138.180.59:8089/api/getpaper`).subscribe(data=>{

    console.log(data)
    this.papers=data;
    this.dataSource.data=this.papers;
    this.dataSource.paginator = this.paginator;
  });
  }
  viewPaper(flag:boolean,id:any){
  this.viewpaper=flag;
  this.paperid=id;
  console.log(id)
  console.log(this.paperid)
  }
  delete(id:any)
  {
    return this.http.delete(`http://34.138.180.59:8089/api/deletePaper/${id}`);
    // .subscribe(data=>{
    //  this.ngOnInit();
    // console.log(data)})

  }

  deletePaper(id:any){
    this.paperid=id;
    console.log(id)
    console.log(this.paperid)
    swal.fire({
      title: "Are you sure you want to Delete? ",
      text: "Are you sure you want to Delete? ",
      showCancelButton: true,
      confirmButtonText: 'Yes, go ahead.',
      cancelButtonText: 'No, let me think',
    })
    .then((deleteConfirmed: any) => {
      if (deleteConfirmed) {
        this.delete(this.paperid).subscribe(
      reponse=>{
        swal.fire("Deleted successfully", '', "success");
        console.log(reponse);
        console.log(id);
        this.ngOnInit();
      }
      );
      } else {
      }
       });

    }

}
