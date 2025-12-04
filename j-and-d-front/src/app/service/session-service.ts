import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap, tap } from 'rxjs';
import { SessionDto } from '../dto/session-dto';

@Injectable({
  providedIn: 'root',
})
export class SessionService {
  private apiUrl: string = '/session';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<SessionDto[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => this.http.get<SessionDto[]>(this.apiUrl))
    );
  }

  public refresh() {
    this.refresh$.next(); // Permet de notifier les changements
  }

  public findById(id: number): Observable<SessionDto> {
    return this.http.get<SessionDto>(`${this.apiUrl}/${id}`);
  }

  public save(sessionDto: SessionDto): Observable<SessionDto> {
    const payload = sessionDto.toJson();

    if (!sessionDto.id) {
      return this.http.post<SessionDto>(this.apiUrl, payload).pipe(
        tap(() => this.refresh())
      );
    }

    return this.http.put<SessionDto>(`${this.apiUrl}/${sessionDto.id}`, payload).pipe(
      tap(() => this.refresh())
    );
  }

  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      tap(() => this.refresh())
    );
  }
}
