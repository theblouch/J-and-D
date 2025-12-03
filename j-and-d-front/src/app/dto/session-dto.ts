export class SessionDto {

    constructor(
        private _id: number,
        public _gmLogin: String,
        public _npcIds?: number[],
        public _inscriptionIds?: number[],            // NPCDto[]
    ) { }

    // ----- GETTERS & SETTERS -----

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    public get inscriptionIds(): number[] | undefined {
        return this._inscriptionIds;
    }
    public set inscriptionIds(value: number[]) {
        this._inscriptionIds = value;
    }

    public get gmLogin(): String {
        return this._gmLogin;
    }
    public set gmLogin(value: String) {
        this._gmLogin = value;
    }

    public get npcIds(): number[] | undefined {
        return this._npcIds;
    }
    public set npcIds(value: number[]) {
        this._npcIds = value;
    }

    // ----- JSON SERIALIZATION -----

    public toJson(): any {
        return {
            id: this.id,
            inscriptionIds: this.inscriptionIds,
            gmLogin: this.gmLogin,
            npcIds: this.npcIds
        };
    }
}