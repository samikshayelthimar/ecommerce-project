import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  //these fields are gathered from the HTML form of the component
  email = '';
  password = '';
  confirmPassword = '';

  constructor(private authService: AuthService, private router: Router) {}

  //called when submit button is clicked
  register() {
    if (this.password != this.confirmPassword) {
      alert("Passwords does not match");
    }
    this.authService.register(this.email, this.password).subscribe({
      next: (response) => {
        alert("Registration successful!");
        console.log(response);
        this.router.navigate(['/login']);
      },
      error: (error) => {
        alert("Registration failed!");
        console.error(error);
      }
    });
  }
}


