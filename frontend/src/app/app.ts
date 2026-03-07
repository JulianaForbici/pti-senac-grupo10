import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

import { CurrentUserService } from './state/current-user.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, RouterLinkActive, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  private readonly currentUserService = inject(CurrentUserService);
  private readonly router = inject(Router);

  currentUser = this.currentUserService.currentUser.bind(this.currentUserService);

  logout(): void {
    this.currentUserService.clearCurrentUser();
    this.router.navigate(['/']).catch(() => {});
  }
}
