import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './service/auth-service';
import { Navigation } from './component/navigation';

@Component({
  standalone: true,
  selector: 'app-root',
  imports: [RouterOutlet,
            Navigation],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  //protected readonly title = signal('j-and-d-front');
  constructor(protected authService: AuthService) { }
}
