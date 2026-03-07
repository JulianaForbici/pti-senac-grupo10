import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { API_BASE_URL } from '../api.config';

@Injectable({
  providedIn: 'root'
})
export class InterestsService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = API_BASE_URL;

  createInterest(shelfId: number, solicitanteId: number): Observable<unknown> {
    return this.http.post(`${this.baseUrl}/interests`, { shelfId, solicitanteId });
  }
}

