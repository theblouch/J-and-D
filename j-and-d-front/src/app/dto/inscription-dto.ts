export class InscriptionDto {

    constructor(
        private _id: number,
        private _character: any,   // CharacterDto
        private _session: any      // SessionDto
    ) {}

    // ----- GETTERS & SETTERS -----

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    public get character(): any {
        return this._character;
    }
    public set character(value: any) {
        this._character = value;
    }

    public get session(): any {
        return this._session;
    }
    public set session(value: any) {
        this._session = value;
    }

    // ----- JSON SERIALIZATION -----

    public toJson(): any {
        return {
            id: this.id,
            character: this.character,
            session: this.session
        };
    }
}
