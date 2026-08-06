package com.book.book.controllers;

import com.book.book.dtos.BookDto;
import com.book.book.services.BookService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController {

    @Inject
    private BookService bookService;

    @GET
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GET
    @Path("/{id}")
    public Response getBookById(@PathParam("id") Long id) {
        return bookService.getBookById(id)
                .map(book -> Response.ok(book).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createBook(BookDto book) {
        BookDto createdBook = bookService.createBook(book);
        return Response.status(Response.Status.CREATED).entity(createdBook).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") Long id, BookDto bookDetails) {
        try {
            BookDto updatedBook = bookService.updateBook(id, bookDetails);
            return Response.ok(updatedBook).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") Long id) {
        bookService.deleteBook(id);
        return Response.noContent().build();
    }
}
