package com.thedionisio.resources;

import com.thedionisio.model.ctrl.TicketCtrl;
import com.thedionisio.model.dto.Person;
import com.thedionisio.model.dto.Ticket;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jonathan on 6/12/17.
 */
@RestController
@RequestMapping("ticket")
public class TicketResource {

    private TicketCtrl ticketCtrl = new TicketCtrl();

    @RequestMapping(method = RequestMethod.GET)
    public Object get()  {return ticketCtrl.find();}

    @RequestMapping(value = "/{id:.+}", method = RequestMethod.GET)
    public Object getOne(@PathVariable Object id)  {
        return ticketCtrl.findOne(id);
    }


    @RequestMapping(value = "by-cpf/{cpf:.+}", method = RequestMethod.GET)
    public Object getByCpf(@PathVariable String cpf)  {
        return ticketCtrl.findByCpf(cpf);
    }

    @RequestMapping(value = "by-email/{email:.+}", method = RequestMethod.GET)
    public Object getByEmail(@PathVariable String email)  {
        return ticketCtrl.findByEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Object create(@RequestBody Ticket ticket)  {
        return this.ticketCtrl.create(ticket);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody Ticket ticket) {
        return this.ticketCtrl.update(ticket);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Object remove(@RequestBody Ticket ticket) {

        return this.ticketCtrl.removeOne(ticket);

    }



}

