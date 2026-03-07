import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { ShelfService } from '../../services/shelf.service';
import { BooksService } from '../../services/books.service';
import { GenresService } from '../../services/genres.service';
import { CurrentUserService } from '../../state/current-user.service';
import type { Book, Genre, Disponibilidade, UserShelf, User } from '../../models';

@Component({
  standalone: true,
  selector: 'app-my-shelf-page',
  imports: [CommonModule, FormsModule],
  templateUrl: './my-shelf.page.html',
  styleUrl: './my-shelf.page.css'
})
export class MyShelfPageComponent implements OnInit {
  private readonly shelfService = inject(ShelfService);
  private readonly booksService = inject(BooksService);
  private readonly genresService = inject(GenresService);
  private readonly currentUserService = inject(CurrentUserService);

  entries = signal<UserShelf[]>([]);
  genres = signal<Genre[]>([]);

  isLoading = signal(true);
  isSaving = signal(false);
  errorMessage = signal<string | null>(null);

  addTitulo = '';
  addAutor = '';
  addGenreId: number | null = null;
  addDisponibilidade: Disponibilidade = 'colecao';
  addEstadoConservacao = '';

  ngOnInit(): void {
    const user = this.currentUserService.ensureUserOrRedirect();
    if (!user) {
      this.isLoading.set(false);
      return;
    }

    this.loadData(user);
  }

  private loadData(user: User): void {
    this.isLoading.set(true);
    this.errorMessage.set(null);

    this.genresService.listGenres().subscribe({
      next: (genres) => this.genres.set(genres),
      error: () => {
        this.errorMessage.set('Não foi possível carregar os gêneros.');
      }
    });

    this.shelfService.listShelf().subscribe({
      next: (allEntries) => {
        const mine = allEntries.filter((e) => e.usuario.id === user.id);
        this.entries.set(mine);
        this.isLoading.set(false);
      },
      error: () => {
        this.errorMessage.set('Não foi possível carregar sua estante.');
        this.isLoading.set(false);
      }
    });
  }

  addToShelf(): void {
    const user = this.currentUserService.ensureUserOrRedirect();
    if (
      !user ||
      !this.addTitulo ||
      !this.addAutor ||
      !this.addGenreId ||
      !this.addEstadoConservacao ||
      this.isSaving()
    ) {
      return;
    }

    this.isSaving.set(true);
    this.errorMessage.set(null);

    this.booksService
      .createBook({
        titulo: this.addTitulo,
        autor: this.addAutor,
        genero: { id: this.addGenreId }
      })
      .subscribe({
        next: (book: Book) => {
          this.shelfService
            .createShelfEntry({
              usuario: { id: user.id },
              livro: { id: book.id },
              disponibilidade: this.addDisponibilidade,
              estadoConservacao: this.addEstadoConservacao
            })
            .subscribe({
              next: (entry: UserShelf) => {
                // Recarrega a estante completa para garantir que os relacionamentos
                // (como livro.gênero) venham totalmente populados do backend.
                this.loadData(user);
                this.resetAddForm();
                this.isSaving.set(false);
              },
              error: () => {
                this.errorMessage.set('Não foi possível adicionar o livro à estante.');
                this.isSaving.set(false);
              }
            });
        },
        error: () => {
          this.errorMessage.set('Não foi possível criar o livro.');
          this.isSaving.set(false);
        }
      });
  }

  private resetAddForm(): void {
    this.addTitulo = '';
    this.addAutor = '';
    this.addGenreId = null;
    this.addDisponibilidade = 'colecao';
    this.addEstadoConservacao = '';
  }

  setDisponibilidade(entry: UserShelf, disponibilidade: Disponibilidade): void {
    if (this.isSaving() || entry.disponibilidade === disponibilidade) {
      return;
    }

    const updated: UserShelf = { ...entry, disponibilidade };
    this.saveEntry(updated);
  }

  saveEstadoConservacao(entry: UserShelf): void {
    if (this.isSaving()) {
      return;
    }

    const updated: UserShelf = { ...entry };
    this.saveEntry(updated);
  }

  private saveEntry(entry: UserShelf): void {
    this.isSaving.set(true);
    this.errorMessage.set(null);

    this.shelfService.updateShelfEntry(entry.id, entry).subscribe({
      next: (saved) => {
        this.entries.update((list) => list.map((e) => (e.id === saved.id ? saved : e)));
        this.isSaving.set(false);
      },
      error: () => {
        this.errorMessage.set('Não foi possível salvar as alterações.');
        this.isSaving.set(false);
      }
    });
  }

  removeEntry(entry: UserShelf): void {
    if (this.isSaving()) {
      return;
    }

    this.isSaving.set(true);
    this.errorMessage.set(null);

    this.shelfService.deleteShelfEntry(entry.id).subscribe({
      next: () => {
        this.entries.update((list) => list.filter((e) => e.id !== entry.id));
        this.isSaving.set(false);
      },
      error: () => {
        this.errorMessage.set('Não foi possível remover o livro da estante.');
        this.isSaving.set(false);
      }
    });
  }
}

