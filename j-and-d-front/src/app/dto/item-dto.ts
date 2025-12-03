export class ItemDto {

    constructor(
        private _id: number,
        private _name: string,
        private _description: string,
        private _basedOnStrength: boolean,
        private _baseDamage: number[]   // int[]
    ) {}

    // ----- GETTERS & SETTERS -----

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }

    public get description(): string {
        return this._description;
    }
    public set description(value: string) {
        this._description = value;
    }

    public get basedOnStrength(): boolean {
        return this._basedOnStrength;
    }
    public set basedOnStrength(value: boolean) {
        this._basedOnStrength = value;
    }

    public get baseDamage(): number[] {
        return this._baseDamage;
    }
    public set baseDamage(value: number[]) {
        this._baseDamage = value;
    }

    // ----- JSON SERIALIZATION -----

    public toJson(): any {
        return {
            id: this.id,
            name: this.name,
            description: this.description,
            basedOnStrength: this.basedOnStrength,
            baseDamage: this.baseDamage
        };
    }
}