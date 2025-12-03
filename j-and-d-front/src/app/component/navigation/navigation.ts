import { Component } from '@angular/core';
import { Router, NavigationEnd, RouterLink } from '@angular/router';
import { NgIf, NgFor } from '@angular/common';

@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [RouterLink, NgIf, NgFor],
  templateUrl: './navigation.html',
  styleUrls: ['./navigation.css'],
})
export class Navigation {
  role: 'Player' | 'GM' | null = null;
  showHeader = true;

  private hiddenRoutes = ['/login', '/inscription'];

  constructor(private router: Router) {
    // Récupération du rôle depuis ton AuthService ou sessionStorage
    this.role = sessionStorage.getItem('role') as any;

    // Cacher le header selon la route
    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.showHeader = !this.hiddenRoutes.some(path => event.url.startsWith(path));
      }
    });
  }
}
