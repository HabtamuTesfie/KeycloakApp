import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
@Injectable({
  providedIn: 'root'
})
export class SharedService {
    data: any;
    url = "http://mockbin.org/bin/9dfa9380-20c9-45c5-bb02-6d62af8d3902";


    constructor( private http: HttpClient){}

    getPost() : any {
        return this.http.get(this.url);
    }

     setData( value:any ) : void{
         this.data = value;
     }


      getData(): any{
          return this.data;
      }



}
