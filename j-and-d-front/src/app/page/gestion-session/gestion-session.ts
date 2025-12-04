import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-gestion-session',
  imports: [],
  templateUrl: './gestion-session.html',
  styleUrl: './gestion-session.css',
})
export class GestionSession implements OnInit {
  sessionId!: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    // Récupérer le paramètre 'sessionId'
    this.sessionId = this.route.snapshot.paramMap.get('sessionId')!;
    console.log('ID de la session :', this.sessionId);
  }
}
