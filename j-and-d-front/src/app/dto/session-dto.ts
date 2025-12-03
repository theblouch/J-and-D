export class SessionDto {

    constructor(
        private _id: number,
        public _gmLogin: String,
        public _npcNames?: String[],
        public _inscriptionCharacters?: String[],
    ) { }

    // ----- GETTERS & SETTERS -----

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    public get inscriptionCharacters(): String[] | undefined {
        return this._inscriptionCharacters;
    }
    public set inscriptionCharacters(value: String[]) {
        this._inscriptionCharacters = value;
    }

    public get gmLogin(): String {
        return this._gmLogin;
    }
    public set gmLogin(value: String) {
        this._gmLogin = value;
    }

    public get npcNames(): String[] | undefined {
        return this._npcNames;
    }
    public set npcNames(value: String[]) {
        this._npcNames = value;
    }

    // ----- JSON SERIALIZATION -----

    public toJson(): any {
        return {
            id: this.id,
            inscriptionCharacters: this.inscriptionCharacters,
            gmLogin: this.gmLogin,
            npcNames: this.npcNames
        };
    }
}