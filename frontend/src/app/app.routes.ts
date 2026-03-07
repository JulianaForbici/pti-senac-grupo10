import { Routes } from '@angular/router';

import { HomePageComponent } from './features/home/home.page';
import { OnboardingPageComponent } from './features/onboarding/onboarding.page';
import { MyShelfPageComponent } from './features/my-shelf/my-shelf.page';
import { BookSearchPageComponent } from './features/book-search/book-search.page';
import { AdminPageComponent } from './features/admin/admin.page';

export const routes: Routes = [
  {
    path: '',
    component: HomePageComponent
  },
  {
    path: 'onboarding',
    component: OnboardingPageComponent
  },
  {
    path: 'my-shelf',
    component: MyShelfPageComponent
  },
  {
    path: 'books',
    component: BookSearchPageComponent
  },
  {
    path: 'admin',
    component: AdminPageComponent
  },
  {
    path: '**',
    redirectTo: ''
  }
];

