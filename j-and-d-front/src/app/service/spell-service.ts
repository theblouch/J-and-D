import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { SpellDto } from '../dto/spell-dto';

@Injectable({
  providedIn: 'root',
})
export class SpellService {
  private apiUrl: string = '/spell'; // endpoint backend pour les sorts
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) {}

  public findAll(): Observable<SpellDto[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => this.http.get<SpellDto[]>(this.apiUrl))
    );
  }

  public refresh(): void {
    this.refresh$.next();
  }

  public findById(id: number): Observable<SpellDto> {
    return this.http.get<SpellDto>(`${this.apiUrl}/${id}`);
  }

  public save(spellDto: SpellDto): void {
    const payload = spellDto.toJson();

    if (!spellDto.id || spellDto.id === 0) {
      // Création
      this.http.post<SpellDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }

    // Mise à jour
    this.http
      .put<SpellDto>(`${this.apiUrl}/${spellDto.id}`, payload)
      .subscribe(() => this.refresh());
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${this.apiUrl}/${id}`).subscribe(() => this.refresh());
  }
}
