import { Component, OnInit, inject, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { BooksService, type AvailableBook, type AvailableBookOwner } from '../../services/books.service';
import { GenresService } from '../../services/genres.service';
import { ShelfService } from '../../services/shelf.service';
import { CurrentUserService } from '../../state/current-user.service';
import { InterestsService } from '../../services/interests.service';
import type { Book, Genre, UserShelf, User } from '../../models';

@Component({
  standalone: true,
  selector: 'app-book-search-page',
  imports: [CommonModule, FormsModule],
  templateUrl: './book-search.page.html',
  styleUrl: './book-search.page.css'
})
export class BookSearchPageComponent implements OnInit {
  private readonly booksService = inject(BooksService);
  private readonly genresService = inject(GenresService);
  private readonly shelfService = inject(ShelfService);
  private readonly currentUserService = inject(CurrentUserService);
  private readonly interestsService = inject(InterestsService);

  titulo = '';
  autor = '';
  genreId: number | null = null;

  genres = signal<Genre[]>([]);
  results = signal<AvailableBook[]>([]);

  isLoading = signal(false);
  errorMessage = signal<string | null>(null);
  successMessage = signal<string | null>(null);

  ngOnInit(): void {
    this.genresService.listGenres().subscribe({
      next: (genres) => this.genres.set(genres)
    });

  }

  search(): void {
    this.isLoading.set(true);
    this.errorMessage.set(null);
    this.successMessage.set(null);

    const filters: { titulo?: string; autor?: string; genre?: number } = {};
    if (this.titulo) {
      filters.titulo = this.titulo;
    }
    if (this.autor) {
      filters.autor = this.autor;
    }
    if (this.genreId != null) {
      filters.genre = this.genreId;
    }

    const currentUser = this.currentUserService.currentUser();

    this.booksService.listAvailableBooks(filters, currentUser?.id).subscribe({
      next: (books: AvailableBook[]) => {
        this.results.set(books);
        this.isLoading.set(false);
      },
      error: () => {
        this.errorMessage.set('Não foi possível buscar livros.');
        this.isLoading.set(false);
      }
    });
  }

  addToShelf(book: AvailableBook): void {
    const user = this.currentUserService.ensureUserOrRedirect();
    if (!user) {
      return;
    }

    this.errorMessage.set(null);
    this.successMessage.set(null);

    this.shelfService
      .createShelfEntry({
        usuario: { id: user.id },
        livro: { id: book.bookId },
        disponibilidade: 'para_troca',
        estadoConservacao: 'Bom estado'
      })
      .subscribe({
        next: () => {
          this.successMessage.set('Livro adicionado à sua estante para troca.');
        },
        error: () => {
          this.errorMessage.set('Não foi possível adicionar o livro à sua estante.');
        }
      });
  }

  expressInterest(book: AvailableBook, owner: AvailableBookOwner): void {
    const user = this.currentUserService.ensureUserOrRedirect();
    if (!user) {
      return;
    }

    if (owner.ownerId === user.id) {
      return;
    }

    this.errorMessage.set(null);
    this.successMessage.set(null);

    this.interestsService.createInterest(owner.shelfId, user.id).subscribe({
      next: () => {
        this.successMessage.set('Interesse registrado para este livro.');
      },
      error: (err) => {
        if (err?.status === 400) {
          this.errorMessage.set('Não foi possível registrar interesse para este livro.');
        } else {
          this.errorMessage.set('Ocorreu um erro ao registrar interesse.');
        }
      }
    });
  }
}

