import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { NPCDto } from '../dto/npc-dto';

@Injectable({
  providedIn: 'root',
})
export class NPCService {
  private apiUrl: string = '/npc';
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) { }

  public findAll(): Observable<NPCDto[]> {
    return this.refresh$.pipe(
      startWith(null),

      switchMap(() => {
        return this.http.get<NPCDto[]>(this.apiUrl);
      })
    );
  }

  public refresh() {
    this.refresh$.next(); // Permet d'envoyer des nouvelles infos
  }

  public findById(id: number): Observable<NPCDto> {
    return this.http.get<NPCDto>(`${this.apiUrl}/${id}`);
  }

  public save(npcDto: NPCDto): void {
    const payload = npcDto.toJson();

    if (!npcDto.id) {
      this.http.post<NPCDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }

    this.http.put<NPCDto>(`${this.apiUrl}/${npcDto.id}`, payload).subscribe(() => this.refresh());
  }

  public deleteById(id: number): void {
    this.http.delete<void>(`${this.apiUrl}/${id}`).subscribe(() => this.refresh());
  }
}

