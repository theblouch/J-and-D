import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { LoginPage } from './page/user/login-page/login-page';
import { InscriptionPage } from './page/user/register-page/register-page';
import { authGuard } from './guard/auth-guard';

// TODO: importer les composants manquants
import { CreateSession } from './page/create-session/create-session';
import { ToolsPage } from './page/gm/tools-page/tools-page';

import { CreateCharacterPage } from './page/player/create-character-page/create-character-page';
import { MyCharactersPage } from './page/player/my-characters-page/my-characters-page';
import { InProgress } from './page/in-progress/in-progress';
import { ItemPage } from './page/tools/item/item-page';
import { SpellPage } from './page/tools/spell-page/spell-page';
import { CharacterDetail } from './page/player/character-detail/character-detail';
import { GestionSession } from './page/gestion-session/gestion-session';

export const routes: Routes = [
  // HOME
  { path: '', component: HomePage, canActivate: [authGuard] },
  { path: 'home', title: 'Java & Dragons', component: HomePage, canActivate: [authGuard] },

  // PAGES PLAYER
  { path: 'createcharacter', title: 'Création de personnages', component: CreateCharacterPage },
  { path: 'mycharacters', title: 'Mes personnages', component: MyCharactersPage },

  // PAGES GM
  { path: 'home/:sessionId', title: 'Gestion de session', component: GestionSession },
  { path: 'createsession', title: 'Création de session', component: CreateSession },
  { path: 'tools', title: 'Outils', component: ToolsPage },
  { path: 'item', title: 'Item', component: ItemPage },
  { path: 'spell', title: 'Sorts', component: SpellPage },

  // COMMON
  { path: 'login', title: 'Se Connecter', component: LoginPage },
  { path: 'register', title: 'Créer un compte', component: InscriptionPage },
  { path: 'marketplace', title: 'Boutique', component: InProgress },
  { path: 'community', title: 'Bienvenue', component: InProgress },
  { path: 'mycharacters/:id', component: CharacterDetail }
];
