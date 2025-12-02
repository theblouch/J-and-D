import { Routes } from '@angular/router';
//import { AuteurPage } from './page/auteur/auteur-page/auteur-page';
import { HomePage } from './page/home-page/home-page';
import { authGuard } from './guard/auth-guard';
import { LoginPage } from './page/login-page/login-page';
//import { InscriptionPage } from './page/user/inscription-page/inscription-page';

export const routes: Routes = [
    { path: '', component: HomePage, canActivate: [ authGuard ] },
    { path: 'home', component: HomePage, canActivate: [ authGuard ] },
    { path: 'login', component: LoginPage },
   // { path: 'inscription', component: InscriptionPage },
    //{ path: 'livre', component: LivrePage, canActivate: [ authGuard ] },
];
