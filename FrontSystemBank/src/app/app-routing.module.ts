import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';


const routes: Routes = [
  {
    path: "", // Default home page
    component: HomeComponent,
  },
  {
    path: "login",
    component: LoginComponent,
  },
  // {
  //   path: "register", // URL with params
  //   component: RegisterComponent,
  // },
  // {
  //   path: "info", // URL with params
  //   component: InfoComponent,
  // },
  // {
  //   path: "transaction", // URL with params
  //   component: TransactionComponent,
  // },
  {
    path: "profile/:id",
    component: ProfileComponent,
  },
  // {
  //   path: "**", // 404 Page
  //   component: PageNotFoundComponent,
  // },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
