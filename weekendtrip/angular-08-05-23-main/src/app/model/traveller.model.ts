import { Login } from "./login.model";
import { Passengers } from "./passengers.model";

export class Traveller{
  constructor(
    public travellerId:number,
    public travellerEmail:string,
    public journeyStartingDate:Date,
    public journeyEndingDate:Date,
    public packageName:string,
    public packagePrice:number,
    public passenger: Passengers[],
    public purchaseDate:Date,
    public paymentStatus:string,
    public cityName:string
  ){}

}
