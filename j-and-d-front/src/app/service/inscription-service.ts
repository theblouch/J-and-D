import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { InscriptionDto } from '../dto/inscription-dto';

@Injectable({
  providedIn: 'root',
})
export class InscriptionService {
  private apiUrl: string = '/inscription';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<InscriptionDto[]> {
    return this.refresh$.pipe(
      startWith(null),

      switchMap(() => {
        return this.http.get<InscriptionDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next(); // Permet d'envoyer des nouvelles infos
  }

  public findById(id: number): Observable<InscriptionDto> {
    return this.http.get<InscriptionDto>(`${this.apiUrl}/${id}`);
  }

  public save(inscriptionDto: InscriptionDto): void {
    const payload = inscriptionDto.toJson();

    if (!inscriptionDto.id) {
      this.http.post<InscriptionDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }

    this.http.put<InscriptionDto>(`${this.apiUrl}/${inscriptionDto.id}`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${this.apiUrl}/${id}`).subscribe(() => this.refresh());
  }
}

