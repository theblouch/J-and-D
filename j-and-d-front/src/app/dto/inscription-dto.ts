export class InscriptionDto {

    constructor(
        private _id: number,
        private _character: any,
        private _characterName: String,
        private _session: any
    ) { }

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

    public get characterName(): String {
        return this._characterName;
    }
    public set characterName(value: String) {
        this._characterName = value;
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
            characterName: this.characterName,
            session: this.session
        };
    }
}
