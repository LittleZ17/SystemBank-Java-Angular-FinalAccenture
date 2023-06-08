import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from '../../services/user-service.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  profileData: any;
  infoCustomer: any;
//Cambiar a false
  showForm = true;

  updateForm: FormGroup;
  emailInput: FormControl;
  passwordInput: FormControl;
  // passwordConfirmInput: FormControl;
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

  constructor( private router: Router, private route: ActivatedRoute, private userRequestService: UserServiceService){
    this.emailInput = new FormControl("" ,[Validators.required, Validators.email]);
    this.passwordInput = new FormControl("", [Validators.required, Validators.minLength(8)]);
    // this.passwordConfirmInput = new FormControl("", [Validators.required, Validators.minLength(8)]);
    this.nameInput = new FormControl("");
    this.lastnameInput = new FormControl("");
    this.idNationalInput = new FormControl("");
    this.phoneInput = new FormControl("", [Validators.required]);
    this.fullStreetInput = new FormControl("", [Validators.required]);
    this.cityInput = new FormControl("", [Validators.required]);
    this.postalCodeInput = new FormControl("", [Validators.required, Validators.minLength(4)]);
    this.updateForm = new FormGroup({
      email: this.emailInput,
      password: this.passwordInput,
      // passwordConfirm: this.passwordConfirmInput,
      name: this.nameInput,
      lastname: this.lastnameInput,
      id: this.idNationalInput,
      phone: this.phoneInput,
      street: this.fullStreetInput,
      city: this.cityInput,
      postal: this.postalCodeInput
    })
  }

getInfoCustomer(): void{
  this.userRequestService.getCustomerById(this.profileData).subscribe({
    next:(data) => {
      console.log(data);
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
  console.log(updateInfo);
  

  this.userRequestService.upldateProfile(updateInfo).subscribe({
    next:(data) => {
      console.log(data)
      this.successRegister = true;
      setTimeout(() => {
        this.router.navigate(['/login']);
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
toggleForm() {
  this.showForm = !this.showForm;
}

onSubmit(): void {
  if (this.updateForm.invalid) {
    return;
  }
  this.formSubmitted = true;
  this.uploadProfile();
}

}
