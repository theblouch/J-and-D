import { Injectable } from '@angular/core';
import { AuthRequestDto } from '../dto/auth-request-dto';
import { HttpClient } from '@angular/common/http';
import { AuthResponseDto } from '../dto/auth-response-dto';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private _token: string = '';

  constructor(private http: HttpClient) {
    this._token = sessionStorage.getItem('token') ?? '';
  }

  public get token(): string {
    return this._token;
  }

  public auth(authRequest: AuthRequestDto): Promise<void> {
    return new Promise((resolve, reject) => {
      this.http.post<AuthResponseDto>('/auth', authRequest.toJson()).subscribe({
        // next => si la réponse est OK
        next: (resp) => {
          if (resp.success == false) {
            reject();
            return;
          }

          this._token = resp.token;

          sessionStorage.setItem('token', this._token);

          resolve();
        },

        // error => si la réponse est KO (30X, 40X, 50X)
        error: (err) => reject(err),
      });
    });
  }

  public isLogged() {
    return !!this._token;
  }

  private parseJwt(token: string): any {
    try {
      const base64Url = token.split('.')[1]; // récupère la partie payload
      const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
      const jsonPayload = decodeURIComponent(
        atob(base64)
          .split('')
          .map(c => '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2))
          .join('')
      );
      return JSON.parse(jsonPayload);
    } catch (e) {
      return null;
    }
  }

  public getUserLogin(): string | null {
    const payload = this.parseJwt(this._token);
    if (!payload) return null;

    // adapte selon ton backend
    return payload.sub ?? payload.id ?? null;
  }


}
