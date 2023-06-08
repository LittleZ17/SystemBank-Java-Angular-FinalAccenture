import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { UserServiceService } from 'src/app/services/user-service.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  profileData!: number;
  profileEmail!: string;

  loginForm: FormGroup;
  emailInput: FormControl;
  passwordInput: FormControl;

  formSubmitted: boolean = false;
  successLogin: boolean = false;
  errorLogin: boolean= false;
  

  constructor(private requestService: UserServiceService, private router: Router){
    this.emailInput = new FormControl("",[Validators.required, Validators.email]);
    this.passwordInput = new FormControl("", [Validators.required, Validators.minLength(8)]);
    this.loginForm = new FormGroup({
      email: this.emailInput,
      password: this.passwordInput
    })
  }

  postLogin():void{
    const newLogin: any = {
      email: this.emailInput.value,
      password: this.passwordInput.value
    }

    this.requestService.postLogin(newLogin).subscribe({
      next:(data) =>{
        // console.log(data.id);
        this.profileEmail = data.email;
        this.profileData = data.id;
        this.successLogin = true;
        setTimeout(() => {
          if(this.profileEmail.includes("admin")){
            this.router.navigate(['/dashboard']);
          }else{
            this.router.navigate(['/account-info', this.profileData], { queryParams: { profileData: JSON.stringify(this.profileData) } });
          }
        }, 1300);        
      },
      error: (error) => {
        console.error(error)
        this.loginForm.reset();
        this.errorLogin = true;
        setTimeout(() => {
          this.errorLogin = false;
        }, 1800); 
      }
    })
  }
  onSubmit(): void {
    if(this.loginForm.invalid){
      return;
    }
    this.formSubmitted = true;
    this.postLogin();
  }
}
