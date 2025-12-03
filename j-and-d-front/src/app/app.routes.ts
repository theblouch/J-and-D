import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { LoginPage } from './page/user/login-page/login-page';
import { InscriptionPage } from './page/user/inscription-page/register-page';
import { authGuard } from './guard/auth-guard';

// TODO: importer les composants manquants
import { CreateSession } from './page/create-session/create-session';
import { ToolsPage } from './page/gm/tools-page/tools-page';

import { CreateCharacterPage } from './page/player/create-character-page/create-character-page';
import { MyCharactersPage } from './page/player/my-characters-page/my-characters-page';

export const routes: Routes = [
  // HOME
  { path: '', component: HomePage, canActivate: [authGuard] },
  { path: 'home', component: HomePage, canActivate: [authGuard] },

  // PAGES PLAYER
  { path: 'createcharacter', component: CreateCharacterPage, canActivate: [authGuard] },
  { path: 'mycharacters', component: MyCharactersPage, canActivate: [authGuard] },

  // PAGES GM
  { path: 'createsession', component: CreateSession},
  { path: 'tools', component: ToolsPage},

  // COMMON
  { path: 'login', component: LoginPage },
  { path: 'register', component: InscriptionPage },
  { path: 'marketplace', component: HomePage }, 
  { path: 'community', component: HomePage }, 
];
