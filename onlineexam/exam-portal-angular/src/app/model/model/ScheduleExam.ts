import { CreatePaper } from "./CreatePaper";

export class ScheduleExam{
  id?:number;
  name?:string;
  code?:string;
  startTime?:string;
  endTime?:string;
  examDuration?:number;
  createPaper?:CreatePaper;


}
