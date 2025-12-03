export class SubscribeRequestDto {
  constructor(private _login: string, private _password: string, private _role: string) {}

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

  public get role(): string {
    return this._role;
  }

  public set role(value: string) {
    this._role = value;
  }

  public toJson(): any {
    return {
      login: this.login,
      password: this.password,
      role: this.role,
    };
  }
}
