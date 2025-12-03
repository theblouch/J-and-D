export class WarriorDto {

    constructor(
        private _id: number = 0,
        private _name: string = "Warrior",
        private _baseHp: number = 12,
        private _baseMp: number = 0,
        private _baseMs: number = 4.0,
        private _baseArmor: number = 12,
        private _baseIni: number = 10,
        private _armor: any = null,      // ItemDto
        private _weapon: any = null,     // ItemDto
        private _baseStats: any = {      // StatsDto
            wisdom: 10,
            strength: 16,
            constitution: 15,
            intelligence: 8,
            dexterity: 12,
            charisma: 10
        },
        private _spells: any[] = []      // SpellDto[]
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

    public get baseHp(): number {
        return this._baseHp;
    }
    public set baseHp(value: number) {
        this._baseHp = value;
    }

    public get baseMp(): number {
        return this._baseMp;
    }
    public set baseMp(value: number) {
        this._baseMp = value;
    }

    public get baseMs(): number {
        return this._baseMs;
    }
    public set baseMs(value: number) {
        this._baseMs = value;
    }

    public get baseArmor(): number {
        return this._baseArmor;
    }
    public set baseArmor(value: number) {
        this._baseArmor = value;
    }

    public get baseIni(): number {
        return this._baseIni;
    }
    public set baseIni(value: number) {
        this._baseIni = value;
    }

    public get armor(): any {
        return this._armor;
    }
    public set armor(value: any) {
        this._armor = value;
    }

    public get weapon(): any {
        return this._weapon;
    }
    public set weapon(value: any) {
        this._weapon = value;
    }

    public get baseStats(): any {
        return this._baseStats;
    }
    public set baseStats(value: any) {
        this._baseStats = value;
    }

    public get spells(): any[] {
        return this._spells;
    }
    public set spells(value: any[]) {
        this._spells = value;
    }

    // ----- JSON SERIALIZATION -----

    public toJson(): any {
        return {
            id: this.id,
            name: this.name,
            baseHp: this.baseHp,
            baseMp: this.baseMp,
            baseMs: this.baseMs,
            baseArmor: this.baseArmor,
            baseIni: this.baseIni,
            armor: this.armor,
            weapon: this.weapon,
            baseStats: this.baseStats,
            spells: this.spells
        };
    }
}
