import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
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

      switchMap(() => {
        return this.http.get<SessionDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next(); // Permet d'envoyer des nouvelles infos
  }

  public findById(id: number): Observable<SessionDto> {
    return this.http.get<SessionDto>(`${this.apiUrl}/${id}`);
  }

  public save(sessionDto: SessionDto): void {
    const payload = sessionDto.toJson();

    if (!sessionDto.id) {
      this.http.post<SessionDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }

    this.http.put<SessionDto>(`${this.apiUrl}/${sessionDto.id}`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${this.apiUrl}/${id}`).subscribe(() => this.refresh());
  }
}

