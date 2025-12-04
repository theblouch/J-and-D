import { Routes } from '@angular/router';
import { HomePage } from './page/home-page/home-page';
import { LoginPage } from './page/user/login-page/login-page';
import { InscriptionPage } from './page/user/register-page/register-page';
import { authGuard } from './guard/auth-guard';

import { CreateSession } from './page/create-session/create-session';
import { ToolsPage } from './page/gm/tools-page/tools-page';

import { CreateCharacterPage } from './page/player/create-character-page/create-character-page';
import { MyCharactersPage } from './page/player/my-characters-page/my-characters-page';
import { InProgress } from './page/in-progress/in-progress';
import { ItemPage } from './page/tools/item/item-page';
import { SpellPage } from './page/tools/spell-page/spell-page';
import { CharacterDetail } from './page/player/character-detail/character-detail';
import { ModifPerso } from './page/sessions-home/modif-perso/modif-perso';
import { GestionSession } from './page/sessions-home/gestion-session/gestion-session';
import { NPCPage } from './page/tools/npc-page/npc-page';

export const routes: Routes = [
  // HOME
  { path: '', component: HomePage, canActivate: [authGuard] },
  { path: 'home', title: 'Java & Dragons', component: HomePage, canActivate: [authGuard] },

  // PLAYER
  { path: 'createcharacter', title: 'Création de personnages', component: CreateCharacterPage },
  { path: 'mycharacters', title: 'Mes personnages', component: MyCharactersPage },
  { path: 'mycharacters/:id', component: CharacterDetail },

  // GM
  { path: 'home/session/:sessionId', title: 'Gestion de session', component: GestionSession },
  { path: 'home/character/:persoId', title: 'Modifier personnage', component: ModifPerso },
  { path: 'createsession', title: 'Création de session', component: CreateSession },
  { path: 'tools', title: 'Outils', component: ToolsPage },


  // TOOLS
  { path: 'item', title: 'Item', component: ItemPage },
  { path: 'spell', title: 'Sorts', component: SpellPage },
  { path: 'npc', title: 'NPC', component: NPCPage },

  // COMMON
  { path: 'login', title: 'Se Connecter', component: LoginPage },
  { path: 'register', title: 'Créer un compte', component: InscriptionPage },
  { path: 'marketplace', title: 'Boutique', component: InProgress },
  { path: 'community', title: 'Bienvenue', component: InProgress },
];
