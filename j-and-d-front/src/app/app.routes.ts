import { Routes } from '@angular/router';
//import { AuteurPage } from './page/auteur/auteur-page/auteur-page';
import { HomePage } from './page/home-page/home-page';
//import { LoginPage } from './page/utilisateur/login-page/login-page';
import { authGuard } from './guard/auth-guard';
//import { InscriptionPage } from './page/user/inscription-page/inscription-page';

export const routes: Routes = [
    { path: '', component: HomePage, canActivate: [ authGuard ] },
    { path: 'home', component: HomePage, canActivate: [ authGuard ] },
  //  { path: 'login', component: LoginPage },
   // { path: 'inscription', component: InscriptionPage },
    //{ path: 'livre', component: LivrePage, canActivate: [ authGuard ] },
];
