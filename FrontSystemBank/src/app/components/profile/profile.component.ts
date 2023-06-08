import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from '../../services/user-service.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
  profileData: any;
  infoCustomer: any;

  showForm = false;

  updateForm: FormGroup;
  emailInput: FormControl = new FormControl("", [Validators.required, Validators.email]);
  passwordConfirmInput = new FormControl("", [Validators.required, Validators.minLength(8)]);
  passwordInput: FormControl = new FormControl("", [Validators.required, Validators.minLength(8)]);
  nameInput: FormControl = new FormControl("");
  lastnameInput: FormControl = new FormControl("");
  idNationalInput: FormControl = new FormControl("");
  phoneInput: FormControl = new FormControl("", [Validators.required]);
  fullStreetInput: FormControl = new FormControl("", [Validators.required]);
  cityInput: FormControl = new FormControl("", [Validators.required]);
  postalCodeInput: FormControl = new FormControl("", [Validators.required, Validators.minLength(4)]);


  formSubmitted: boolean = false;
  successRegister: boolean = false;
  errorRegister: boolean= false;

  constructor( private router: Router, private route: ActivatedRoute, private userRequestService: UserServiceService){

    this.updateForm = new FormGroup({
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
    });
  }
  
  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['profileData']) {
        this.profileData = JSON.parse(params['profileData']);
        // console.log(this.profileData);
        this.getInfoCustomer();
      } else {
        // erorr
      }
    });
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

getInfoCustomer(): void{
  this.userRequestService.getCustomerById(this.profileData).subscribe({
    next:(data) => {
      // console.log(data);
      this.infoCustomer = data;
      
      this.updateForm.patchValue({
        email: this.infoCustomer.email,
        password: this.infoCustomer.password,
        name: this.infoCustomer.name,
        lastname: this.infoCustomer.lastname,
        id: this.infoCustomer.idNational,
        phone: this.infoCustomer.phone,
        street: this.infoCustomer.homeAddress.fullStreet,
        city: this.infoCustomer.homeAddress.city,
        postal: this.infoCustomer.homeAddress.postalCode
      });
    }
  })
}

uploadProfile():void{
  const updateInfo: any = {
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
  // console.log(updateInfo);
  

  this.userRequestService.upldateProfile(this.profileData, updateInfo).subscribe({
    next:(data) => {
      // console.log(data)
      this.successRegister = true;
      setTimeout(() => {
        this.router.navigate(['/account-info', this.profileData], { queryParams: { profileData: JSON.stringify(this.profileData) } });
      }, 1300);        
    },
    error: (error) => {
      console.error(error)
      this.updateForm.reset();
      this.errorRegister = true;
      setTimeout(() => {
        this.errorRegister = false;
      }, 1800); 
    }
  })
}
toggleForm(): void {
  this.showForm = !this.showForm;
}

onSubmit(): void {
  if (this.updateForm.invalid) {
    return;
  }
  this.formSubmitted = true;
  this.uploadProfile();
}
getProfile():void{
  // console.log(this.profileData);
  this.router.navigate(['/account-info', this.profileData], { queryParams: { profileData: JSON.stringify(this.profileData) } });
}

}
