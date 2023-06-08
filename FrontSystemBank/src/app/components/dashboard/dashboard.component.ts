import {  Component, OnInit } from '@angular/core';
import { AccountServiceService } from 'src/app/services/account-service.service';
import { TransferServiceService } from 'src/app/services/transfer-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  customerList: any;
  accountList: any;
  transferList: any;

  showCList: boolean = false;
  showAList: boolean = false;
  showTList: boolean = false;

  successDelete: boolean = false;

  constructor(
    private customerService: UserServiceService,
    private accountService: AccountServiceService,
    private transferService: TransferServiceService,
    private router: Router
  ) {
    this.customerList = customerService.getAllCustomers;
    this.accountList = accountService.getAllAccounts;
    this.transferList = transferService.getAllTransfers;
  }
  ngOnInit(): void {
    this.getCustomers();
    this.getAccounts();
    this.getTransfers();
  }
  getCustomers(): void {
    this.customerService.getAllCustomers().subscribe({
      next: (data) => {
        // console.log(data);
        this.customerList = data;
      },
    });
  }
  getAccounts(): void {
    this.accountService.getAllAccounts().subscribe({
      next: (data) => {
        // console.log(data);
        this.accountList = data;
      },
    });
  }
  getTransfers(): void {
    this.transferService.getAllTransfers().subscribe({
      next: (data) => {
        // console.log(data);
        this.transferList = data;
      },
    });
  }

  toggleList(element: string) {
    if (element === 'customer') {
      this.showCList = !this.showCList;
    } else if (element === 'account') {
      this.showAList = !this.showAList;
    } else if (element === 'transaction') {
      this.showTList = !this.showTList;
    }
  }

  deleteAccount(account: any) {
    this.accountService.deleteAccount(account).subscribe({
      next: (data) => {
        console.log(data);
        this.successDelete = true;
        setTimeout(() => {
          this.successDelete = false;
        }, 1800);
        this.getAccounts();
      },
    });
  }
}
