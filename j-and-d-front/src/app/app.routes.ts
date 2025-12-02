import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { LoginPage } from './page/utilisateur/login-page/login-page';
import { authGuard } from './guard/auth-guard';
import { InscriptionPage } from './page/utilisateur/inscription-page/inscription-page';

export const routes: Routes = [
  { path: '', component: HomePage, canActivate: [authGuard] },
  { path: 'home', component: HomePage, canActivate: [authGuard] },
  { path: 'login', component: LoginPage },
  { path: 'inscription', component: InscriptionPage },
];
