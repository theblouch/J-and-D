export class SessionDto {

    constructor(
        private _id: number,
        public _gmLogin: string,
        public _name: string,
        public _npcNames?: string[],
        public _inscriptionCharacters?: string[],
    ) { }

    // ----- GETTERS & SETTERS -----

    public get name(): string {
        return this._name
    }

    public set name(value: string) {
        this._name = value;
    }

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    public get inscriptionCharacters(): string[] | undefined {
        return this._inscriptionCharacters;
    }
    public set inscriptionCharacters(value: string[]) {
        this._inscriptionCharacters = value;
    }

    public get gmLogin(): string {
        return this._gmLogin;
    }
    public set gmLogin(value: string) {
        this._gmLogin = value;
    }

    public get npcNames(): string[] | undefined {
        return this._npcNames;
    }
    public set npcNames(value: string[]) {
        this._npcNames = value;
    }

    // ----- JSON SERIALIZATION -----

    public toJson(): any {
        return {
            id: this.id,
            inscriptionCharacters: this.inscriptionCharacters,
            gmLogin: this.gmLogin,
            npcNames: this.npcNames,
            name: this.name
        };
    }
}