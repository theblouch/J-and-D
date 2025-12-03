import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({ providedIn: 'root' })
export class RoleService {
    private api = 'http://localhost:8080/roles';

    constructor(private http: HttpClient) { }

    getAll() {
        return this.http.get<any[]>(this.api);
    }
}