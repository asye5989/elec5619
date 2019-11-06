import { Injectable } from '@angular/core';
import { User } from '../domain/user';

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    constructor() {}

    public login(username: string, pass: string): boolean {
        let user: User = new User();
        user.username = username;
        user.fullname = 'test user';
        localStorage.setItem('user', JSON.stringify(user));
        return true;
    }

    public logout() {
        localStorage.removeItem('user');
    }

    public isLoginValid() {
        // verify token
        if (localStorage.getItem('user')) {
            console.log('verified login');
            return true;
        } else {
            console.log('login verification failed');
        }
    }
}
