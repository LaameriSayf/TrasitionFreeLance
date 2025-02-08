import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CompteBancaire } from '../Models/CompteBancaire';

@Injectable({
  providedIn: 'root'
})
export class CompteBancaireService {
  private apiUrl = 'http://localhost:8089/api/auth/comptes';

  constructor(private http: HttpClient) {}

  getAllComptes(): Observable<CompteBancaire[]> {
    return this.http.get<CompteBancaire[]>(`${this.apiUrl}/getall`);
  }

  getCompteById(id: number): Observable<CompteBancaire> {
    return this.http.get<CompteBancaire>(`${this.apiUrl}/findById/${id}`);
  }

  addCompte(compte: CompteBancaire): Observable<CompteBancaire> {
    return this.http.post<CompteBancaire>(`${this.apiUrl}/ajouterCompte`, compte);
  }

  updateCompte(compte: CompteBancaire): Observable<CompteBancaire> {
    return this.http.put<CompteBancaire>(`${this.apiUrl}/update`, compte);
  }

  supprimerCompte(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${id}`);
  }
  
}
