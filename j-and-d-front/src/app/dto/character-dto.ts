export class CharacterDto {

    constructor(
        private _id: number,
        private _name: string,
        private _level: number,
        private _hp: number,
        private _mp: number,
        private _speed: number,
        private _alive: boolean,
        private _armorClass: number,
        private _initiative: number,
        private _armor: any,             // ItemDto
        private _weapon: any,            // ItemDto
        private _itemWorn: any[],        // ItemDto[]
        private _inventory: any[],       // ItemDto[]
        private _stats: any,             // StatsDto
        private _role: any,              // RoleDto
        private _race: any,              // RaceDto
        private _state: any[],           // StateDto[]
        private _inscriptions: any[]     // InscriptionDto[]
    ) { }

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

    public get level(): number {
        return this._level;
    }
    public set level(value: number) {
        this._level = value;
    }

    public get hp(): number {
        return this._hp;
    }
    public set hp(value: number) {
        this._hp = value;
    }

    public get mp(): number {
        return this._mp;
    }
    public set mp(value: number) {
        this._mp = value;
    }

    public get speed(): number {
        return this._speed;
    }
    public set speed(value: number) {
        this._speed = value;
    }

    public get alive(): boolean {
        return this._alive;
    }
    public set alive(value: boolean) {
        this._alive = value;
    }

    public get armorClass(): number {
        return this._armorClass;
    }
    public set armorClass(value: number) {
        this._armorClass = value;
    }

    public get initiative(): number {
        return this._initiative;
    }
    public set initiative(value: number) {
        this._initiative = value;
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

    public get itemWorn(): any[] {
        return this._itemWorn;
    }
    public set itemWorn(value: any[]) {
        this._itemWorn = value;
    }

    public get inventory(): any[] {
        return this._inventory;
    }
    public set inventory(value: any[]) {
        this._inventory = value;
    }

    public get stats(): any {
        return this._stats;
    }
    public set stats(value: any) {
        this._stats = value;
    }

    public get role(): any {
        return this._role;
    }
    public set role(value: any) {
        this._role = value;
    }

    public get race(): any {
        return this._race;
    }
    public set race(value: any) {
        this._race = value;
    }

    public get state(): any[] {
        return this._state;
    }
    public set state(value: any[]) {
        this._state = value;
    }

    public get inscriptions(): any[] {
        return this._inscriptions;
    }
    public set inscriptions(value: any[]) {
        this._inscriptions = value;
    }

    // ----- JSON SERIALIZATION -----

    public toJson(): any {
        return {
            id: this.id,
            name: this.name,
            level: this.level,
            hp: this.hp,
            mp: this.mp,
            speed: this.speed,
            alive: this.alive,
            armorClass: this.armorClass,
            initiative: this.initiative,
            armor: this.armor,
            weapon: this.weapon,
            itemWorn: this.itemWorn,
            inventory: this.inventory,
            stats: this.stats,
            role: this.role,
            race: this.race,
            state: this.state,
            inscriptions: this.inscriptions
        };
    }
}
