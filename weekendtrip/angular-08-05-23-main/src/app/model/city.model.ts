import { Packages } from './packages.model';

export class City{
  constructor(
    public cityId :number,
    public cityName:string,
    public status:string,
    public cImageUrl:string,
    public packages:Packages[],
  ){}

}
