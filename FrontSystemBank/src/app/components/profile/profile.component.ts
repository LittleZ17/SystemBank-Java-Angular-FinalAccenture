import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UserServiceService } from '../../services/user-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
  profileData: any;
  constructor(private router: Router, private route: ActivatedRoute) {}

ngOnInit() {
  this.route.queryParams.subscribe(params => {
    if (params['profileData']) {
      this.profileData = JSON.parse(params['profileData']);
    } else {
      // erorr
    }
  });
}
}
