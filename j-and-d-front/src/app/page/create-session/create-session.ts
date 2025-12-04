import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { map, Observable, of } from 'rxjs';
import { SessionDto } from '../../dto/session-dto';
import { InscriptionDto } from '../../dto/inscription-dto';
import { NPCDto } from '../../dto/npc-dto';
import { SessionService } from '../../service/session-service';
import { InscriptionService } from '../../service/inscription-service';
import { NPCService } from '../../service/npc-service';
import { AuthService } from '../../service/auth-service';
import { CharacterDto } from '../../dto/character-dto';
import { CharacterService } from '../../service/character-service';

@Component({
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './create-session.html',
  styleUrls: ['./create-session.css'],
})
export class CreateSession implements OnInit {
  protected sessions$!: Observable<SessionDto[]>;
  protected inscriptions$!: Observable<InscriptionDto[]>;
  protected npcs$!: Observable<NPCDto[]>;

  protected sessionForm!: FormGroup;
  protected editingSession!: SessionDto | null;
  protected showForm: boolean = false;

  protected inscriptionsCtrl!: FormControl;
  protected npcsCtrl!: FormControl;
  protected nameCtrl!: FormControl;
  protected characters$!: Observable<CharacterDto[]>;

  constructor(
    private sessionService: SessionService,
    private inscriptionService: InscriptionService,
    private characterService: CharacterService,
    private npcService: NPCService,
    private formBuilder: FormBuilder,
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    const gmLogin = this.authService.getUserLogin();
    this.sessions$ = this.sessionService.findAll().pipe(
      map(sessions => sessions.filter(session => session.gmLogin === gmLogin))
    );
    this.inscriptionService.findAll().subscribe(data => {
      console.log('inscriptions$', data);
      this.inscriptions$ = of(data);
    });
    this.npcs$ = this.npcService.findAll();
    this.characters$ = this.characterService.getAll();
    this.inscriptionsCtrl = this.formBuilder.control([]);
    this.npcsCtrl = this.formBuilder.control([]);
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

  public creer() {
    const gmLogin = this.authService.getUserLogin();
    if (!gmLogin) {
      console.error("Utilisateur non connecté !");
      return;
    }

    const newSession = new SessionDto(
      0,
      gmLogin,
      this.sessionForm.get('name')?.value,
      Array.isArray(this.npcsCtrl.value) ? this.npcsCtrl.value : [],       // assure tableau
      Array.isArray(this.inscriptionsCtrl.value) ? this.inscriptionsCtrl.value : [] // assure tableau
    );

    this.sessionService.save(newSession);

    this.showForm = false;
    this.editingSession = null;
    this.sessionForm.reset();
  }

  public editer(session: SessionDto) {
    this.editingSession = session;
    this.showForm = true;
    this.sessionForm.patchValue({
      name: session.name,
    });
    this.inscriptionsCtrl.setValue(session.inscriptionCharacters);
    this.npcsCtrl.setValue(session.npcNames);
  }

  public annulerEditer() {
    this.showForm = false;
    this.editingSession = null;
    this.sessionForm.reset();
  }

  public delete(id: number) {
    this.sessionService.deleteById(id);
  }

  public displayList(list?: string[]): string {
    if (!list || list.length === 0) {
      return 'vide :(';
    }
    return list.join(', ');
  }
}
