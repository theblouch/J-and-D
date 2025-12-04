import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-tools-page',
  imports: [CommonModule, RouterLink],
  templateUrl: './tools-page.html',
  styleUrl: './tools-page.css',
})
export class ToolsPage {}
