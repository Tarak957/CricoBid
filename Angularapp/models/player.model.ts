import { Team } from "./team.model"

export interface Player{
    id?:any
    name?:string
    age?:number
    category?:string
    biddingPrice?:number
    sold?:boolean
    team?:Team
}
