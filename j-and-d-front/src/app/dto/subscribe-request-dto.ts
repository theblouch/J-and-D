export class SubscribeRequestDto {
  constructor(private _login: string, private _password: string, private _gm: boolean) {}

  public get login(): string {
    return this._login;
  }

  public set login(value: string) {
    this._login = value;
  }

  public get password(): string {
    return this._password;
  }

  public set password(value: string) {
    this._password = value;
  }

  public get gm(): boolean {
    return this._gm;
  }

  public set gm(value: boolean) {
    this._gm = value;
  }

  public toJson(): any {
    return {
      login: this.login,
      password: this.password,
      gm: this.gm,
    };
  }
}
