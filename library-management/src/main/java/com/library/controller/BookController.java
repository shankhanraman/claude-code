package com.library.controller;

import com.library.entity.Book;
import com.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "API endpoints for managing the library book collection")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books", description = "Retrieve a list of all books in the library")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of books")
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @Operation(summary = "Get book by ID", description = "Retrieve a single book by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Book found"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(
            @Parameter(description = "ID of the book to retrieve") @PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @Operation(summary = "Create a new book", description = "Add a new book to the library")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Book created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid book data provided")
    })
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(book));
    }

    @Operation(summary = "Update a book", description = "Update the details of an existing book")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Book updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid book data provided"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(
            @Parameter(description = "ID of the book to update") @PathVariable Long id,
            @Valid @RequestBody Book book) {
        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    @Operation(summary = "Delete a book", description = "Remove a book from the library by its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Book deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID of the book to delete") @PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Search books by title", description = "Find books whose title contains the given search term")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved matching books")
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchByTitle(
            @Parameter(description = "Title search term") @RequestParam String title) {
        return ResponseEntity.ok(bookService.searchByTitle(title));
    }

    @Operation(summary = "Search books by author", description = "Find books by a specific author name")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved matching books")
    @GetMapping("/author")
    public ResponseEntity<List<Book>> searchByAuthor(
            @Parameter(description = "Author name to search for") @RequestParam String name) {
        return ResponseEntity.ok(bookService.searchByAuthor(name));
    }

    @Operation(summary = "Get books by genre", description = "Retrieve all books belonging to a specific genre")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved books for the genre")
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<Book>> getByGenre(
            @Parameter(description = "Genre to filter books by") @PathVariable String genre) {
        return ResponseEntity.ok(bookService.getByGenre(genre));
    }

    @Operation(summary = "Get available books", description = "Retrieve all books that are currently available for borrowing")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved available books")
    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        return ResponseEntity.ok(bookService.getAvailableBooks());
    }
}
