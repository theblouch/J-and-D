import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { ItemDto } from '../dto/item-dto';

@Injectable({
  providedIn: 'root',
})
export class ItemService {
  private apiUrl: string = '/item'; // endpoint backend pour les items
  private refresh$: Subject<void> = new Subject<void>();

  constructor(private http: HttpClient) {}

  // Récupérer tous les items
  public findAll(): Observable<ItemDto[]> {
    return this.refresh$.pipe(
      startWith(null),
      switchMap(() => {
        return this.http.get<ItemDto[]>(this.apiUrl);
      })
    );
  }

  // Forcer un refresh
  public refresh(): void {
    this.refresh$.next();
  }

  // Récupérer un item par ID
  public findById(id: number): Observable<ItemDto> {
    return this.http.get<ItemDto>(`${this.apiUrl}/${id}`);
  }

  // Créer ou mettre à jour un item
  public save(itemDto: ItemDto): void {
    const payload = itemDto.toJson();

    if (!itemDto.id || itemDto.id === 0) {
      // Création
      this.http.post<ItemDto>(this.apiUrl, payload).subscribe(() => this.refresh());
      return;
    }

    // Mise à jour
    this.http.put<ItemDto>(`${this.apiUrl}/${itemDto.id}`, payload).subscribe(() => this.refresh());
  }

  // Supprimer un item par ID
  public deleteById(id: number): void {
    this.http.delete<void>(`${this.apiUrl}/${id}`).subscribe(() => this.refresh());
  }
}
