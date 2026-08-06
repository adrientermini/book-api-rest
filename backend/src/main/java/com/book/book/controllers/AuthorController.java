package com.book.book.controllers;

import com.book.book.dtos.AuthorDto;
import com.book.book.services.AuthorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/authors")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorController {

    @Inject
    private AuthorService authorService;

    @GET
    public List<AuthorDto> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GET
    @Path("/{id}")
    public Response getAuthorById(@PathParam("id") Long id) {
        return authorService.getAuthorById(id)
                .map(author -> Response.ok(author).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createAuthor(AuthorDto author) {
        AuthorDto createdAuthor = authorService.createAuthor(author);
        return Response.status(Response.Status.CREATED).entity(createdAuthor).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateAuthor(@PathParam("id") Long id, AuthorDto authorDetails) {
        try {
            AuthorDto updatedAuthor = authorService.updateAuthor(id, authorDetails);
            return Response.ok(updatedAuthor).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") Long id) {
        authorService.deleteAuthor(id);
        return Response.noContent().build();
    }
}
