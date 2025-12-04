import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { Observable, of, switchMap } from 'rxjs';
import { CommonModule } from '@angular/common';
import { SessionService } from '../../../service/session-service';
import { CharacterService } from '../../../service/character-service';
import { SessionDto } from '../../../dto/session-dto';
import { CharacterDto } from '../../../dto/character-dto';

@Component({
  selector: 'app-gestion-session',
  imports: [CommonModule, RouterModule],
  templateUrl: './gestion-session.html',
  styleUrls: ['./gestion-session.css'],
})
export class GestionSession implements OnInit {
  sessionId!: string;
  session$!: Observable<SessionDto | null>;
  showCharactersSection: boolean = false;
  charactersList: { id: number, name: string }[] = [];
  showNPCsSection: boolean = false;
  npcList: string[] = [];

  allCharacters: CharacterDto[] = [];

  constructor(
    private route: ActivatedRoute,
    private sessionService: SessionService,
    private characterService: CharacterService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // Récupère l'ID depuis l'URL
    this.sessionId = this.route.snapshot.paramMap.get('sessionId')!;

    // Charger tous les personnages pour mapping réel
    this.characterService.getAll().subscribe(chars => {
      this.allCharacters = chars;
      this.loadSession();
    });
  }

  loadSession() {
    this.session$ = this.sessionService.findById(Number(this.sessionId))
      .pipe(
        switchMap(session => of(session))
      );
  }

  goHome() {
    this.router.navigate(['/home']);
  }

  goToCharacter(id: number): void {
    this.router.navigate(['/home', 'character', id]);
  }


  showCharacters(session: SessionDto) {
    // Mapping réel des personnages inscrits
    this.charactersList = session.inscriptionCharacters?.map(name => {
      const character = this.allCharacters.find(c => c.name === name);
      return character ? { id: character.id, name: character.name } : null;
    }).filter(c => c !== null) as { id: number, name: string }[];

    this.showCharactersSection = !this.showCharactersSection;
  }

  showNPCs(session: SessionDto) {
    this.npcList = session.npcNames || [];
    this.showNPCsSection = !this.showNPCsSection;
  }

  rollDice() {
    const result = Math.floor(Math.random() * 20) + 1; // D20
    alert('Résultat du dé : ' + result);
  }
}
