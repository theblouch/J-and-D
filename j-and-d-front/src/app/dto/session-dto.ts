export class SessionDto {

    constructor(
        private _id: number,
        private _inscriptions: any[],   // InscriptionDto[]
        private _gm: any,               // GMDto
        private _npcs: any[]            // NPCDto[]
    ) {}

    // ----- GETTERS & SETTERS -----

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    public get inscriptions(): any[] {
        return this._inscriptions;
    }
    public set inscriptions(value: any[]) {
        this._inscriptions = value;
    }

    public get gm(): any {
        return this._gm;
    }
    public set gm(value: any) {
        this._gm = value;
    }

    public get npcs(): any[] {
        return this._npcs;
    }
    public set npcs(value: any[]) {
        this._npcs = value;
    }

    // ----- JSON SERIALIZATION -----

    public toJson(): any {
        return {
            id: this.id,
            inscriptions: this.inscriptions,
            gm: this.gm,
            npcs: this.npcs
        };
    }
}