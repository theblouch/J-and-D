import { CreatureDto } from "./creature-dto";
import { RaceEnum } from "../enum/Race.enum";
import { InscriptionDto } from "./inscription-dto";

export class CharacterDto extends CreatureDto {

    constructor(
        id: number,
        name: string,
        level: number,
        hp: number,
        mp: number,
        speed: number,
        alive: boolean,
        armorClass: number,
        initiative: number,
        armor: any,
        weapon: any,
        itemWorn: any[],
        inventory: any[],
        stats: any,
        role: any,
        state: any[],
        tauntedBy: any,
        // ----- Character-specific -----
        private _race: RaceEnum,
        private _inscriptions: InscriptionDto[]
    ) {
        super(
            id, name, level, hp, mp, speed, alive,
            armorClass, initiative, armor, weapon,
            itemWorn, inventory, stats, role, state, tauntedBy
        );
    }

    // ----- GETTERS / SETTERS -----

    public get race(): RaceEnum { return this._race; }
    public set race(v: RaceEnum) { this._race = v; }

    public get inscriptions(): InscriptionDto[] { return this._inscriptions; }
    public set inscriptions(v: InscriptionDto[]) { this._inscriptions = v; }

    // ----- JSON -----

    public override toJson(): any {
        const base = super.toJson();
        return {
            ...base,
            race: this.race,
            inscriptions: this.inscriptions.map(i => i.toJson())
        };
    }
}
