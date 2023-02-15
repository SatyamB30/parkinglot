package service

import SlotLocation
import Vehicle
import models.Receipt
import models.Ticket
import java.time.LocalDateTime


class Generate() {
    private var ticketNumber = 1
    fun generateTicket(spotNumber: Int, vehicle: Vehicle): Ticket {
        val entryDateTime = LocalDateTime.now()
        return Ticket(ticketNumber++, spotNumber, entryDateTime, vehicle)
    }

    fun generateReceipt(ticket: Ticket, slotLocation: SlotLocation): Receipt {
        val exitDateTime = LocalDateTime.now()
        val fees = Fees()
        val finalFees = fees.calculate(exitDateTime, ticket, slotLocation).toInt()
        return Receipt(ticket.id, ticket.entryTime, exitDateTime, finalFees)
    }

}