import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { GMDto } from '../dto/gm-dto';

@Injectable({
  providedIn: 'root',
})
export class GMService {
  private apiUrl: string = '/user';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<GMDto[]> {
    return this.refresh$.pipe(
      startWith(null),

      switchMap(() => {
        return this.http.get<GMDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next(); // Permet d'envoyer des nouvelles infos
  }

  public findById(id: number): Observable<GMDto> {
    return this.http.get<GMDto>(`${this.apiUrl}/${id}`);
  }

  public save(gmDto: GMDto): void {
    const payload = gmDto.toJson();

    if (!gmDto.id) {
      this.http.post<GMDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }

    this.http.put<GMDto>(`${this.apiUrl}/${gmDto.id}`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${this.apiUrl}/${id}`).subscribe(() => this.refresh());
  }
}

