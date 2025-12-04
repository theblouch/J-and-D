import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({ providedIn: 'root' })
export class CharacterService {
    private api = '/character';

    constructor(private http: HttpClient) { }

    create(character: any) {
        return this.http.post<any>(this.api, character);
    }

    getAll(): Observable<any[]> {
        return this.http.get<any[]>(this.api);
    }
}