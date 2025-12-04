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
  imports: [CommonModule, ReactiveFormsModule],
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

  protected sessionToDelete: number | null = null;
  protected showDeleteModal = false;

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

    this.inscriptionService.findAll().subscribe(data => this.inscriptions$ = of(data));
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
    if (!gmLogin) return;

    const newSession = new SessionDto(
      0,
      gmLogin,
      this.sessionForm.get('name')?.value,
      Array.isArray(this.npcsCtrl.value) ? this.npcsCtrl.value : [],
      Array.isArray(this.inscriptionsCtrl.value) ? this.inscriptionsCtrl.value : []
    );

    this.sessionService.save(newSession).subscribe(() => {
      this.showForm = false;
      this.editingSession = null;
      this.sessionForm.reset();
    });
  }
  public modifier() {
    if (!this.editingSession) return;

    const updatedSession = new SessionDto(
      this.editingSession.id, // on GARDER l’ID pour une mise à jour
      this.editingSession.gmLogin, // idem
      this.sessionForm.get('name')?.value,
      Array.isArray(this.npcsCtrl.value) ? this.npcsCtrl.value : [],
      Array.isArray(this.inscriptionsCtrl.value) ? this.inscriptionsCtrl.value : []
    );

    this.sessionService.save(updatedSession).subscribe(() => {
      this.showForm = false;
      this.editingSession = null;
      this.sessionForm.reset();
    });
  }


  public editer(session: SessionDto) {
    this.editingSession = session;
    this.showForm = true;

    this.sessionForm.patchValue({
      name: session.name,
    });

    const characters = Array.isArray(session.inscriptionCharacters)
      ? session.inscriptionCharacters
      : [];

    this.inscriptionsCtrl.setValue(characters);
    this.npcsCtrl.setValue(session.npcNames || []);
  }

  public annulerEditer() {
    this.showForm = false;
    this.editingSession = null;
    this.sessionForm.reset();
  }
  public askDelete(id: number) {
    this.sessionToDelete = id;
    this.showDeleteModal = true;
  }
  public confirmDelete() {
    if (!this.sessionToDelete) return;

    this.sessionService.deleteById(this.sessionToDelete).subscribe(() => {
      console.log(`Session ${this.sessionToDelete} supprimée`);
      this.showDeleteModal = false;
      this.sessionToDelete = null;
    });
  }

  public cancelDelete() {
    this.showDeleteModal = false;
    this.sessionToDelete = null;
  }

  public delete(id: number) {
    this.sessionService.deleteById(id).subscribe(() => {
      console.log(`Session ${id} supprimée`);
    });
  }
  toggleCharacter(event: Event) {
    const input = event.target as HTMLInputElement;
    const value = input.value;
    let selected: string[] = this.inscriptionsCtrl.value || [];

    if (input.checked) {
      selected.push(value);
    } else {
      selected = selected.filter(v => v !== value);
    }

    this.inscriptionsCtrl.setValue(selected);
  }
  toggleNpc(event: Event) {
    const input = event.target as HTMLInputElement;
    const value = input.value;
    let selected: string[] = this.npcsCtrl.value || [];

    if (input.checked) {
      // Ajouter le monstre sélectionné
      selected.push(value);
    } else {
      // Retirer le monstre désélectionné
      selected = selected.filter(v => v !== value);
    }

    this.npcsCtrl.setValue(selected);
  }


  public displayList(list?: string[]): string {
    if (!list || list.length === 0) {
      return 'vide :(';
    }
    return list.join(', ');
  }
}
