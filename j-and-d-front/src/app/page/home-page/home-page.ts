import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { RouterLink } from '@angular/router';
import { map, Observable, of } from 'rxjs';

import { SessionDto } from '../../dto/session-dto';
import { InscriptionDto } from '../../dto/inscription-dto';
import { NPCDto } from '../../dto/npc-dto';
import { SessionService } from '../../service/session-service';
import { InscriptionService } from '../../service/inscription-service';
import { NPCService } from '../../service/npc-service';
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'app-home-page',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './home-page.html',
  styleUrls: ['./home-page.css'],
})
export class HomePage implements OnInit {
  protected sessions$!: Observable<SessionDto[]>;
  protected inscriptions$!: Observable<InscriptionDto[]>;
  protected npcs$!: Observable<NPCDto[]>;

  protected sessionForm!: FormGroup;
  protected editingSession: SessionDto | null = null;
  protected showForm: boolean = false;

  protected inscriptionsCtrl!: FormControl;
  protected npcsCtrl!: FormControl;
  protected nameCtrl!: FormControl;

  constructor(
    private sessionService: SessionService,
    private inscriptionService: InscriptionService,
    private npcService: NPCService,
    private formBuilder: FormBuilder,
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    const gmLogin = this.authService.getUserLogin();

    // On récupère uniquement les sessions du GM connecté
    this.sessions$ = this.sessionService
      .findAll()
      .pipe(map((sessions) => sessions.filter((session) => session.gmLogin === gmLogin)));

    // Inscriptions
    this.inscriptionService.findAll().subscribe((data) => {
      this.inscriptions$ = of(data);
    });

    // NPCs
    this.npcs$ = this.npcService.findAll();

    // Form controls
    this.inscriptionsCtrl = this.formBuilder.control('', Validators.required);
    this.npcsCtrl = this.formBuilder.control('', Validators.required);
    this.nameCtrl = this.formBuilder.control('', Validators.required);

    this.sessionForm = this.formBuilder.group({
      name: this.nameCtrl,
      inscriptions: this.inscriptionsCtrl,
      npcs: this.npcsCtrl,
    });
  }

  public trackSession(index: number, value: SessionDto) {
    return value.id;
  }

  public creer(): void {
    const gmLogin = this.authService.getUserLogin();
    if (!gmLogin) {
      console.error('Utilisateur non connecté !');
      return;
    }

    const newSession = new SessionDto(
      0,
      gmLogin,
      this.sessionForm.value.name,
      Array.isArray(this.npcsCtrl.value) ? this.npcsCtrl.value : [],
      Array.isArray(this.inscriptionsCtrl.value) ? this.inscriptionsCtrl.value : []
    );

    this.sessionService.save(newSession);

    this.showForm = false;
    this.editingSession = null;
    this.sessionForm.reset();
  }

  public editer(session: SessionDto): void {
    this.editingSession = session;
    this.showForm = true;

    this.inscriptionsCtrl.setValue(session.inscriptionCharacters);
    this.npcsCtrl.setValue(session.npcNames);
  }

  public annulerEditer(): void {
    this.showForm = false;
    this.editingSession = null;
    this.sessionForm.reset();
  }

  public delete(id: number): void {
    this.sessionService.deleteById(id);
  }

  public displayList(list?: String[]): String {
    if (!list || list.length === 0) {
      return 'vide :(';
    }
    return list.join(', ');
  }
}
