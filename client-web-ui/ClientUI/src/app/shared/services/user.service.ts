import { Injectable } from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class UserService {
    constructor() {}

    authenticate(username: string, password: string) {
        // assume success always
        //  user:user=new User();
    }
}
