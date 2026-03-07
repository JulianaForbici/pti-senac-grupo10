import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { CurrentUserService } from '../state/current-user.service';

/**
 * Guard that ensures the user is authenticated.
 * If not authenticated, redirects to /onboarding.
 */
export const authGuard: CanActivateFn = () => {
  const currentUserService = inject(CurrentUserService);
  const router = inject(Router);

  if (currentUserService.isLoggedIn()) {
    return true;
  }

  router.navigate(['/onboarding']).catch(() => {});
  return false;
};
