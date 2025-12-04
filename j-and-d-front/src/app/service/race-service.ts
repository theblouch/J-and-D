import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class RaceService {
    private api = '/race';

    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<any>(this.api);
    }
}