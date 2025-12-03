import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class CharacterService {
    private api = 'http://localhost:8080/characters';

    constructor(private http: HttpClient) { }

    create(character: any) {
        return this.http.post<any>(this.api, character);
    }
}