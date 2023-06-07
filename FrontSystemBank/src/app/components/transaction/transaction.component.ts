import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountServiceService } from 'src/app/services/account-service.service';
import { TransferServiceService } from 'src/app/services/transfer-service.service';

@Component({
  selector: 'app-transaction',
  templateUrl: './transaction.component.html',
  styleUrls: ['./transaction.component.css']
})
export class TransactionComponent implements OnInit{

  profileData: any;
  infoAccount: any;
  infoTransfer: any;

  // accountSource: any;

  transferForm: FormGroup;
  accountNumberInput: FormControl;
  nameInput: FormControl;
  amountInput: FormControl;
  subjectInput: FormControl;

  formSubmitted: boolean = false;
  successTransfer: boolean = false;
  errorTransfer: boolean= false;

  constructor(private router: ActivatedRoute,  private accountService: AccountServiceService, private transferService: TransferServiceService, private routerR: Router){
    // this.accountNumberInput = new FormControl("ES-", [Validators.required, Validators.maxLength(7)]);
    this.accountNumberInput = new FormControl("", [Validators.required, Validators.maxLength(7)]);

    this.nameInput = new FormControl("",Validators.required);
    this.amountInput = new FormControl("",Validators.required);
    this.subjectInput = new FormControl("",Validators.required);
    this.transferForm = new FormGroup({
      account: this.accountNumberInput,
      name: this.nameInput,
      amount: this.amountInput,
      subject: this.subjectInput
    })
  }

  ngOnInit(): void {
    this.router.queryParams.subscribe(params => {
      if(params['profileData']){
        this.profileData = JSON.parse(params['profileData']);
        this.getInfoAccount();
        // console.log(this.profileData);
      }
    })
  }

  getInfoAccount(): void{
    
    this.accountService.getAccountByCostumer(this.profileData).subscribe(
      {
        next:(data) => {
          this.infoAccount = data[0];
          // console.log(this.infoAccount.number);
          this.accountNumberInput.setValue("ES-");

        }
      }
    )
  }
  postTransfer(): void{
    const newTransfer: any = {
      sourceAccountNumber: this.infoAccount.number,
      destinationAccountNumber: this.accountNumberInput.value,
      amount:{
        amount: this.amountInput.value,
        currency: "USD"
      } 
    }

    this.transferService.postTransfer(newTransfer).subscribe({
      next:(data) => {
        // console.log(data);
        this.infoTransfer = {
          ...this.transferForm.value,
          sourceAccountNumber: this.infoAccount.number
        };
        // console.log(this.infoTransfer);
        this.successTransfer = true;
        setTimeout(() => {
          this.routerR.navigate(['/account-info', this.profileData], { queryParams: { profileData: JSON.stringify(this.profileData) } });
        }, 1300);
      },
      error: (error) => {
        console.error(error)
        this.transferForm.reset();
        this.errorTransfer = true;
        setTimeout(() => {
          this.errorTransfer = false;
        }, 1800); 
      }
    })

  }

  onSubmit(): void {
    if(this.transferForm.invalid){
      return;
    }
    this.formSubmitted = true;
    this.postTransfer();
  }

  

}
