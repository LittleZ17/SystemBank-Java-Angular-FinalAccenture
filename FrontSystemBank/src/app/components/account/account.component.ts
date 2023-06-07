import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AccountServiceService } from 'src/app/services/account-service.service';
import { UserServiceService } from 'src/app/services/user-service.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit{
  profileData: any;
  infoAccount: any;
  constructor(private router: Router, private route: ActivatedRoute, private requestService: AccountServiceService) {}

ngOnInit() {
  this.route.queryParams.subscribe(params => {
    if (params['profileData']) {
      this.profileData = JSON.parse(params['profileData']);
      // console.log(this.profileData);
      this.getInfoAccount();
    } else {
      // erorr
    }
  });
}

getInfoAccount(): void{
  this.requestService.getAccountByCostumer(this.profileData).subscribe(
    {
      next:(data) => {
        // console.log(data);
        this.infoAccount = data[0];
      }
    }
  )
}
createTransaction(): void{
  console.log(this.profileData);
  this.router.navigate(['/transaction', this.profileData], { queryParams: { profileData: JSON.stringify(this.profileData) } });
  
}

}
