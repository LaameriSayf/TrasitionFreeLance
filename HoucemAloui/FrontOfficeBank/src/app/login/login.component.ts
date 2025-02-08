
// TypeScript component for login and registration
import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/User.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  @ViewChild('container') container!: ElementRef;

  isActive = false;
  username: string = '';
  password: string = '';
  email: string = '';
  confirmPassword: string = '';
message: any;

  constructor(private authService: UserService,http:HttpClient,private router:Router) {}

  toggleForm() {
    this.isActive = !this.isActive;
    const containerElement = this.container.nativeElement;
    containerElement.classList.toggle('active', this.isActive);
  }

  onSubmitSignIn() {
    this.authService.login(this.username, this.password).subscribe(
      response => {
        console.log('Login successful', response);
        this.router.navigate(['/dashboard']); // Navigation après un login réussi
      },
      error => {
        console.error('Login failed', error);
      }
    );
  }
  

  onSubmitSignUp() {
    if (this.password !== this.confirmPassword) {
      console.error('Passwords do not match');
      return;
    }
    const user = { username: this.username, email: this.email, password: this.password };
    this.authService.register(user).subscribe(
      (      response: any) => console.log('Registration successful', response),
      (      error: any) => console.error('Registration failed', error)
    );
  }
}
