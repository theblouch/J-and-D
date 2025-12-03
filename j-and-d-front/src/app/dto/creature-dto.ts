import { ItemDto } from "./item-dto";
import { StatsDto } from "./stats-dto";
import { RoleDto } from "./role-dto";
import { StateEnum } from "../enum/State.enum";

export class CreatureDto {

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
        private _armor: ItemDto | null,
        private _weapon: ItemDto,
        private _itemWorn: ItemDto[],
        private _inventory: ItemDto[],
        private _stats: StatsDto,
        private _role: RoleDto,
        private _state: StateEnum[],
        private _tauntedBy: CreatureDto | null
    ) {}

    // ---------- GETTERS / SETTERS ----------

    public get id(): number { return this._id; }
    public set id(v: number) { this._id = v; }

    public get name(): string { return this._name; }
    public set name(v: string) { this._name = v; }

    public get level(): number { return this._level; }
    public set level(v: number) { this._level = v; }

    public get hp(): number { return this._hp; }
    public set hp(v: number) { this._hp = v; }

    public get mp(): number { return this._mp; }
    public set mp(v: number) { this._mp = v; }

    public get speed(): number { return this._speed; }
    public set speed(v: number) { this._speed = v; }

    public get alive(): boolean { return this._alive; }
    public set alive(v: boolean) { this._alive = v; }

    public get armorClass(): number { return this._armorClass; }
    public set armorClass(v: number) { this._armorClass = v; }

    public get initiative(): number { return this._initiative; }
    public set initiative(v: number) { this._initiative = v; }

    public get armor(): ItemDto | null { return this._armor; }
    public set armor(v: ItemDto | null) { this._armor = v; }

    public get weapon(): ItemDto { return this._weapon; }
    public set weapon(v: ItemDto) { this._weapon = v; }

    public get itemWorn(): ItemDto[] { return this._itemWorn; }
    public set itemWorn(v: ItemDto[]) { this._itemWorn = v; }

    public get inventory(): ItemDto[] { return this._inventory; }
    public set inventory(v: ItemDto[]) { this._inventory = v; }

    public get stats(): StatsDto { return this._stats; }
    public set stats(v: StatsDto) { this._stats = v; }

    public get role(): RoleDto { return this._role; }
    public set role(v: RoleDto) { this._role = v; }

    public get state(): StateEnum[] { return this._state; }
    public set state(v: StateEnum[]) { this._state = v; }

    public get tauntedBy(): CreatureDto | null { return this._tauntedBy; }
    public set tauntedBy(v: CreatureDto | null) { this._tauntedBy = v; }

    // ---------- JSON ----------

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
            armor: this.armor ? this.armor.toJson() : null,
            weapon: this.weapon?.toJson(),
            itemWorn: this.itemWorn.map(i => i.toJson()),
            inventory: this.inventory.map(i => i.toJson()),
            stats: this.stats.toJson(),
            role: this.role.toJson(),
            state: this.state,
            tauntedBy: this.tauntedBy ? this.tauntedBy.toJson() : null
        };
    }
}
