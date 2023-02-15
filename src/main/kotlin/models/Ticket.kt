package models

import Vehicle
import java.time.LocalDateTime

data class Ticket(val id: Int, val spot: Int, val entryTime: LocalDateTime, val vehicle: Vehicle)
