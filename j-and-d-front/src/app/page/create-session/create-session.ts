import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { SessionService } from '../../service/session-service';
import { Observable } from 'rxjs';
import { SessionDto } from '../../dto/session-dto';
import { GMDto } from '../../dto/gm-dto';
import { InscriptionDto } from '../../dto/inscription-dto';
import { NPCDto } from '../../dto/npc-dto';
import { InscriptionService } from '../../service/inscription-service';
import { GMService } from '../../service/gm-service';
import { NPCService } from '../../service/npc-service';

@Component({
  imports: [CommonModule, RouterLink, ReactiveFormsModule],
  templateUrl: './create-session.html',
  styleUrl: './create-session.css',
})
export class CreateSession implements OnInit {
  protected sessions$!: Observable<SessionDto[]>;
  protected gms$!: Observable<GMDto[]>;
  protected inscriptions$!: Observable<InscriptionDto[]>;
  protected npcs$!: Observable<NPCDto[]>;
  protected sessionForm!: FormGroup;
  protected editingSession!: SessionDto | null;

  protected showForm: boolean = false;
  protected inscriptionsCtrl!: FormControl;
  protected gmCtrl!: FormControl;
  protected npcsCtrl!: FormControl;

  constructor(
    private sessionService: SessionService,
    private inscriptionService: InscriptionService,
    private gmService: GMService,
    private npcService: NPCService,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.sessions$ = this.sessionService.findAll();
    this.inscriptions$ = this.inscriptionService.findAll();
    this.gms$ = this.gmService.findAll();
    this.npcs$ = this.npcService.findAll();

    this.inscriptionsCtrl = this.formBuilder.control('');
    this.gmCtrl = this.formBuilder.control('', Validators.required);
    this.npcsCtrl = this.formBuilder.control('');

    this.sessionForm = this.formBuilder.group({
      inscriptions: this.inscriptionsCtrl,
      gm: this.gmCtrl,
      npcs: this.npcsCtrl,
    });
  }

  public trackSession(index: number, value: SessionDto) {
    return value.id;
  }

  public creer() {
    this.sessionService.save(new SessionDto(0, this.inscriptionsCtrl.value, this.gmCtrl.value, this.npcsCtrl.value));

    this.showForm = false;
    this.editingSession = null;
    this.sessionForm.reset();
  }

  public editer(session: SessionDto) {
    this.editingSession = session;
    this.showForm = true;

    this.inscriptionsCtrl.setValue(session.inscriptionIds);
    this.gmCtrl.setValue(session.gmId);
    this.npcsCtrl.setValue(session.npcIds);
  }

  public annulerEditer() {
    this.showForm = false;
    this.editingSession = null;
    this.sessionForm.reset();
  }

  public delete(id: number) {
    this.sessionService.deleteById(id);
  }
}