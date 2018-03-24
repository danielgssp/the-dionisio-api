package com.thedionisio.model.ctrl;

import com.thedionisio.dao.TicketRepository;
import com.thedionisio.model.bss.TicketBss;
import com.thedionisio.model.dto.Ticket;
import com.thedionisio.util.verification.Validation;
import org.bson.Document;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jonathan on 6/12/17.
 */
public class TicketCtrl {
    private TicketBss ticketBss = new TicketBss();
    private TicketRepository ticketRepository = new TicketRepository();
    private final String collection  ="ticket";

    public Object create(Ticket ticket){

        try
        {
            if(ticket.createValidation())
            {
                if (ticketBss.existingValidation(ticket))
                {
                    ResponseEntity responseEntity = ticketRepository.create(collection, ticket.treatCreate());
                    try                  {
                        if (Boolean.parseBoolean(responseEntity.getBody().toString()))
                        {
                            Object objectCrete = ticketRepository.findAfterCreate(ticket);

                            try

                            {
                                Ticket newTicket = (Ticket) objectCrete;
                                return Validation.resquest.REGISTRY_CREATE(newTicket);
                            }
                            catch (Exception e)
                            {
                                return  objectCrete;
                            }

                        }
                    }
                    catch (Exception e)
                    {
                        return responseEntity;
                    }

                }
                return Validation.resquest.REGISTRY_EXISTED(ticket.attributeIdentifier() + ticket._id);
            }
            return Validation.resquest.NOT_CONTAINS_FIELDS(ticket.isRequiredForCreate());
        }
        catch (Exception e)
        {
            return Validation.resquest.NOT_DATA_BASE();
        }

    }

    public Object find(){

        Object objectFind  = ticketRepository.find(collection,new Ticket(), new Document(), new Document(),0);
        try
        {
            List<Ticket> tickets = (List<Ticket>) objectFind;
            return ticketRepository.treatResponse(tickets);
        }
        catch (Exception e)
        {
            return objectFind;
        }

    }

    public Object findByCpf(String cpf){

        Object objectFind  = ticketRepository.findByCpfOrEmail(cpf,"cpf");
        try
        {
            List<Ticket> tickets = (List<Ticket>) objectFind;
            return ticketRepository.treatResponse(tickets);
        }
        catch (Exception e)
        {
            return objectFind;
        }

    }

    public Object findByEmail(String email){

        Object objectFind  = ticketRepository.findByCpfOrEmail(email,"email");
        try
        {
            List<Ticket> tickets = (List<Ticket>) objectFind;
            return ticketRepository.treatResponse(tickets);
        }
        catch (Exception e)
        {
            return objectFind;
        }

    }

    public Object findOne(Object id){

        Object objectFind  = ticketRepository.findOne(collection,id,new Ticket());
        try
        {
            List<Ticket> tickets = (List<Ticket>) objectFind;

            if (tickets.get(0).isActive)
            {
                return tickets.get(0).treatResponse();
            }
            return new ArrayList<Ticket>();
        }
        catch (Exception e)
        {
            return new ArrayList<Ticket>();
        }
    }

    public Object update(Ticket ticket){
        if (ticket.updateValidation())
        {
            Object objectUpdate = ticketRepository.update(ticket.treatUpdate(),ticket._id,collection);

           try
           {
               List<Ticket> ticketUpdate = (List<Ticket>) objectUpdate;
               return Validation.resquest.REGISTRY_UPDATE(ticketUpdate.get(0).treatResponse());
           }
           catch (Exception e)
           {
               return  objectUpdate;
           }

        }
        return Validation.resquest.CONTAINS_FIELDS_IMMUTABLE(ticket.isImmutable());
    }

    public Object removeOne(Ticket ticket){

        return  ticketRepository.removeOne(ticket._id,collection);
    }
}
