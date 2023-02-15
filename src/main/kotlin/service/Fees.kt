package service

import SlotLocation
import Vehicle
import models.Ticket
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import kotlin.math.ceil


class Fees(private val feeStructure: FeeStructure = FeeStructure()) {
    private var fees: Double = 0.0


    fun calculate(exitDateTime: LocalDateTime, ticket: Ticket, slotLocation: SlotLocation):Double {
        val noOfHours: Double = ceil(ticket.entryTime.until(exitDateTime, ChronoUnit.MILLIS).toDouble())
        if (slotLocation == SlotLocation.Mall) {
            if (noOfHours < 4) {
                fees += noOfHours * feeStructure.getMallBreakdown()[Vehicle.Motorcycle]?.get(0)?.toDouble()!!
            } else if (noOfHours > 4 && noOfHours < 12) {
                fees += 4 * feeStructure.getMallBreakdown()[Vehicle.Motorcycle]?.get(0)?.toDouble()!!
                fees += (12 - noOfHours) * feeStructure.getMallBreakdown()[Vehicle.Motorcycle]?.get(1)?.toDouble()!!
            } else {
                fees += 4 * feeStructure.getMallBreakdown()[Vehicle.Motorcycle]?.get(0)?.toDouble()!!
                fees += (8) * feeStructure.getMallBreakdown()[Vehicle.Motorcycle]?.get(1)?.toDouble()!!
                fees += (noOfHours - 12) * feeStructure.getMallBreakdown()[Vehicle.Motorcycle]?.get(2)?.toDouble()!!
            }
        } else fees = 0.0
        return fees
    }
}
