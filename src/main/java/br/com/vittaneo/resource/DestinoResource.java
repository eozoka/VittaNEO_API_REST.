package br.com.vittaneo.resource;

import br.com.vittaneo.dao.DestinoDAO;
import br.com.vittaneo.entities.Destino;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/destinos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DestinoResource {

    private DestinoDAO dao = new DestinoDAO();

    @GET
    public Response listar() {
        try {
            List<Destino> lista = dao.listar();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            Destino d = dao.buscarPorId(id);
            if (d == null) return Response.status(404).entity("Destino não encontrado").build();
            return Response.ok(d).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response inserir(Destino destino) {
        try {
            dao.inserir(destino);
            return Response.status(201).entity("Destino criado com sucesso").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Destino destino) {
        try {
            destino.setId(id);
            dao.atualizar(destino);
            return Response.ok("Destino atualizado com sucesso").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            dao.deletar(id);
            return Response.ok("Destino deletado com sucesso").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}