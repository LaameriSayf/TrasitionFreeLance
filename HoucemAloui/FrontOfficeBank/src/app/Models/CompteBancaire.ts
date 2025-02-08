import { User } from "./User";

export interface CompteBancaire {
    idCompte: number;
    userId: number | null; // Permettre null
    typecompte: string;
    solde: number;
    user?: User; // Optionnel
  }