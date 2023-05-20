import {Injectable} from '@angular/core';

import {HttpRequest,HttpHandler,HttpEvent,HttpInterceptor} from '@angular/common/http';

import { AuthService } from './authservice';
import { Observable } from 'rxjs';





@Injectable()
export class TokenInterceptor implements   HttpInterceptor{
    // constructor(public auth: AuthService) {}


    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
        // request = request.clone({
        //   setHeaders: {
        //     Authorization: `Bearer ${this.auth.getToken()}`
        //   }
        // });
        // console.log("header set")
        // return next.handle(request);
    

const idToken = localStorage.getItem("token");

if (idToken) {
    const cloned = request.clone({
        headers: request.headers.set("Authorization",
            "Bearer " + idToken)
    });

    return next.handle(cloned);
}
else {
    return next.handle(request);
}


}
}