import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, of, switchMap } from 'rxjs';
import { SessionDto } from '../../dto/session-dto';
import { SessionService } from '../../service/session-service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-gestion-session',
  imports: [CommonModule],
  templateUrl: './gestion-session.html',
  styleUrl: './gestion-session.css',
})
export class GestionSession implements OnInit {
  sessionId!: string;
  session$!: Observable<SessionDto | null>;

  constructor(
    private route: ActivatedRoute,
    private sessionService: SessionService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // Récupère l'ID depuis l'URL
    this.sessionId = this.route.snapshot.paramMap.get('sessionId')!;

    // Charge la session depuis le service
    this.session$ = this.sessionService.findById(Number(this.sessionId))
      .pipe(
        switchMap(session => of(session)) // convertit en Observable si nécessaire
      );
  }

  goHome() {
    this.router.navigate(['/home']);
  }

  // Méthodes pour les boutons
  showNPCs(session: SessionDto) {
    alert('Monstres : ' + (session.npcNames?.join(', ') || 'Aucun Monstre'));
  }

  showCharacters(session: SessionDto) {
    alert('Characters : ' + (session.inscriptionCharacters?.join(', ') || 'Aucun personnage'));
  }

  rollDice() {
    const result = Math.floor(Math.random() * 20) + 1; // D20
    alert('Résultat du dé : ' + result);
  }
}
