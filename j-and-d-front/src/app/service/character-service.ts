import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { CharacterDto } from "../dto/character-dto";

@Injectable({ providedIn: 'root' })
export class CharacterService {
    private api = '/character';

    constructor(private http: HttpClient) { }

    create(character: any) {
        return this.http.post<any>(this.api, character);
    }

    update(character: CharacterDto): Observable<CharacterDto> {
        return this.http.put<CharacterDto>(`${this.api}/${character.id}`, character);
    }

    getAll(): Observable<any[]> {
        return this.http.get<any[]>(this.api);
    }

    get(value: number) {
        return this.http.get<any[]>(`${this.api}/${value}`);
    }
}