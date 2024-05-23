import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AuthGuard } from './auth/guards/auth.guard';
import { Layouts } from './layout/enums/Layouts.enum';

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    data: {
      layout: Layouts.Minimal,
    },
  },
  {
    path: 'login',
    loadChildren: () =>
      import('./pages/login/login.module').then((m) => m.LoginModule),
    data: {
      layout: Layouts.Minimal,
    },
  },
  {
    path: 'register',
    loadChildren: () =>
      import('./pages/register/register.module').then((m) => m.RegisterModule),
    data: {
      layout: Layouts.Minimal,
    },
  },
  {
    path: 'profile',
    loadChildren: () =>
      import('./pages/profile/profile.module').then((m) => m.ProfileModule),
    data: {
      layout: Layouts.Main,
    },
    canActivate: [AuthGuard],
  },
  {
    path: 'articles',
    loadChildren: () =>
      import('./pages/articles/articles.module').then((m) => m.ArticlesModule),
    data: {
      layout: Layouts.Main,
    },
  },
  {
    path: 'themes',
    loadChildren: () =>
      import('./pages/themes/themes.module').then((m) => m.ThemesModule),
    data: {
      layout: Layouts.Main,
    },
  },

  {
    path: '**',
    redirectTo: '',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
