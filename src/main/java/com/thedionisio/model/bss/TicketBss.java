package com.thedionisio.model.bss;

import com.thedionisio.model.dto.Ticket;

import java.util.List;

/**
 * Created by jonathan on 6/12/17.
 */
public class TicketBss {
    public boolean existingValidation(Ticket ticket) {
        //valida vagas
        return true;
    }

    public Object treatResponse(List<Ticket> events) {
        //trata resposta
        return events;
    }
}
