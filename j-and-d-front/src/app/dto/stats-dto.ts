export class StatsDto {

    constructor(
        private _wisdom: number,
        private _strength: number,
        private _constitution: number,
        private _intelligence: number,
        private _dexterity: number,
        private _charisma: number
    ) {}

    // ----- GETTERS & SETTERS -----

    public get wisdom(): number {
        return this._wisdom;
    }
    public set wisdom(value: number) {
        this._wisdom = value;
    }

    public get strength(): number {
        return this._strength;
    }
    public set strength(value: number) {
        this._strength = value;
    }

    public get constitution(): number {
        return this._constitution;
    }
    public set constitution(value: number) {
        this._constitution = value;
    }

    public get intelligence(): number {
        return this._intelligence;
    }
    public set intelligence(value: number) {
        this._intelligence = value;
    }

    public get dexterity(): number {
        return this._dexterity;
    }
    public set dexterity(value: number) {
        this._dexterity = value;
    }

    public get charisma(): number {
        return this._charisma;
    }
    public set charisma(value: number) {
        this._charisma = value;
    }

    // ----- JSON SERIALIZATION -----

    public toJson(): any {
        return {
            wisdom: this.wisdom,
            strength: this.strength,
            constitution: this.constitution,
            intelligence: this.intelligence,
            dexterity: this.dexterity,
            charisma: this.charisma
        };
    }
}