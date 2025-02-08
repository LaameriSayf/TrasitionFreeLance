import { Component, OnInit } from '@angular/core';
import { CompteBancaire } from '../Models/CompteBancaire';
import { CompteBancaireService } from '../service/compte-bancaire.service';
import { UserService } from '../service/User.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-comptebancaire',
  templateUrl: './comptebancaire.component.html',
  styleUrls: ['./comptebancaire.component.css']
})
export class ComptebancaireComponent implements OnInit {
  comptes: CompteBancaire[] = [];
  users: any[] = [];
  afficherFormulaire: boolean = false;
  compteEdit: CompteBancaire | null = null;
  nouveauCompte: CompteBancaire = this.initCompte();

  constructor(
    private compteBancaireService: CompteBancaireService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.loadComptes();
    this.loadUsers();
  }

  private initCompte(): CompteBancaire {
    return { idCompte: 0, userId: null, typecompte: '', solde: 0 };
  }

  loadComptes(): void {
    this.compteBancaireService.getAllComptes().subscribe(
      data => {
        this.comptes = data;
      },
      error => {
        console.error("Erreur lors du chargement des comptes", error);
      }
    );
  }

  loadUsers(){
    this.userService.getAllUsers().subscribe(
      data => {
        this.users = data;
      },
      error => {
        console.error("Erreur lors du chargement des utilisateurs", error);
      }
    );
  }

  toggleForm(): void {
    this.afficherFormulaire = !this.afficherFormulaire;
    if (!this.afficherFormulaire) {
      this.resetForm();
    }
  }

  ajouterCompte(): void {
    if (!this.nouveauCompte.userId) {
      alert("Veuillez sélectionner un utilisateur.");
      return;
    }
  
    if (this.compteEdit) {
      this.compteBancaireService.updateCompte(this.nouveauCompte).subscribe(
        () => {
          this.loadComptes();
          this.resetForm();
        },
        (error: HttpErrorResponse) => {
          console.error("Erreur lors de la mise à jour du compte", error);
          alert(`Erreur lors de la mise à jour du compte: ${error.message}`);
        }
      );
    } else {
      this.compteBancaireService.addCompte(this.nouveauCompte).subscribe(
        () => {
          this.loadComptes();
          this.resetForm();
        },
        (error: HttpErrorResponse) => {
          console.error("Erreur lors de l'ajout du compte", error);
          alert(`Erreur lors de l'ajout du compte: ${error.message}`);
        }
      );
    }
  }

  supprimerCompte(id: number): void {
    if (!id) {
      console.error("ID du compte bancaire non défini");
      return;
    }
    if (confirm("Êtes-vous sûr de vouloir supprimer ce compte ?")) {
      this.compteBancaireService.supprimerCompte(id).subscribe(
        () => {
          this.loadComptes();
        },
        (error: HttpErrorResponse) => {
          console.error("Erreur lors de la suppression du compte", error);
          alert(`Erreur lors de la suppression du compte: ${error.message}`);
        }
      );
    }
  }

  editCompte(compte: CompteBancaire): void {
    this.compteEdit = { ...compte };
    this.nouveauCompte = { ...compte, userId:compte.user ? compte.user.id : null };
    this.afficherFormulaire = true;
  }

  resetForm(): void {
    this.nouveauCompte = this.initCompte();
    this.afficherFormulaire = false;
    this.compteEdit = null;
  }
}