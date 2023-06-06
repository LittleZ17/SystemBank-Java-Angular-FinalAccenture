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

  customersList: any;
  loginForm: FormGroup;
  emailInput: FormControl;
  passwordInput: FormControl;

  formSubmitted: boolean = false;
  profileData!: number;
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


  getCustomers(): void {
    this.requestService.getCustomers().subscribe(
      {
        next: (data) => {
          console.log(data);
          this.customersList = data;       
        }
      }
    )
  }

  postLogin():void{
    const newLogin: any = {
      email: this.emailInput.value,
      password: this.passwordInput.value
    }
    this.requestService.postLogin(newLogin).subscribe({
  
      next:(data) =>{
        console.log(data.id);
        this.profileData = data.id;
        this.successLogin = true;
        setTimeout(() => {
          this.router.navigate(['/account-info', data.id], { queryParams: { profileData: JSON.stringify(data.id) } });
        }, 1300);        
      },
      error: (error) => {
        console.error(error)
        this.loginForm.reset();
        this.errorLogin = true;
        setTimeout(() => {
          this.errorLogin = false;
        }, 1300); 
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
