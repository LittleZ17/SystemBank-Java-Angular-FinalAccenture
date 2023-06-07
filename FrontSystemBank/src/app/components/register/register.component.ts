import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  registerForm: FormGroup;
  emailInput: FormControl;
  passwordInput: FormControl;
  passwordConfirmInput: FormControl;
  nameInput: FormControl;
  lastnameInput: FormControl;
  idNationalInput: FormControl;
  phoneInput: FormControl;
  fullStreetInput: FormControl;
  cityInput: FormControl;
  postalCodeInput: FormControl;

  formSubmitted: boolean = false;
  successRegister: boolean = false;
  errorRegister: boolean= false;

  constructor(private requestService: UserServiceService, private router: Router){
    this.emailInput = new FormControl("",[Validators.required, Validators.email]);
    this.passwordInput = new FormControl("", [Validators.required, Validators.minLength(8)]);
    this.passwordConfirmInput = new FormControl("", [Validators.required, Validators.minLength(8)]);
    this.nameInput = new FormControl("", [Validators.required, Validators.minLength(4)]);
    this.lastnameInput = new FormControl("", [Validators.required, Validators.minLength(4)]);
    this.idNationalInput = new FormControl("", [Validators.required, Validators.minLength(9)]);
    this.phoneInput = new FormControl("", [Validators.required]);
    this.fullStreetInput = new FormControl("", [Validators.required]);
    this.cityInput = new FormControl("", [Validators.required]);
    this.postalCodeInput = new FormControl("", [Validators.required, Validators.minLength(4)]);
    this.registerForm = new FormGroup({
      email: this.emailInput,
      password: this.passwordInput,
      passwordConfirm: this.passwordConfirmInput,
      name: this.nameInput,
      lastname: this.lastnameInput,
      id: this.idNationalInput,
      phone: this.phoneInput,
      street: this.fullStreetInput,
      city: this.cityInput,
      postal: this.postalCodeInput
    })
  }
  passwordMatchValidator(form: FormGroup) {
    const password = form.get('password')?.value;
    const passwordConfirm = form.get('passwordConfirm')?.value;
    if (password !== passwordConfirm) {
      form.get('passwordConfirm')?.setErrors({ mismatch: true });
    } else {
      form.get('passwordConfirm')?.setErrors(null);
    }
    return null;
  }

  postRegister():void{
    const newRegister: any = {
      email: this.emailInput.value,
      password: this.passwordInput.value,
      name: this.nameInput.value,
      lastname: this.lastnameInput.value,
      idNational: this.idNationalInput.value,
      phone: this.phoneInput.value,
      homeAddress:{
        fullStreet: this.fullStreetInput.value,
        city: this.cityInput.value,
        postalCode: this.postalCodeInput.value
      }
    }
    console.log(newRegister);
    

    this.requestService.postRegister(newRegister).subscribe({
      next:(data) => {
        // console.log(data)
        this.successRegister = true;
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 1300);        
      },
      error: (error) => {
        console.error(error)
        this.registerForm.reset();
        this.errorRegister = true;
        setTimeout(() => {
          this.errorRegister = false;
        }, 1800); 
      }
    })
  }

  onSubmit(): void {
    if (this.registerForm.invalid) {
      return;
    }
    this.formSubmitted = true;
    this.postRegister();
  }
}
