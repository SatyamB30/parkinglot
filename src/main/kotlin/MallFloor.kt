import models.Receipt
import models.Ticket
import service.Generate

class MallFloor(
    private val generator: Generate = Generate(), private val motorCycleSlotSize: Int = 100,
    private val carSlotSize: Int = 80, private val busSlotSize: Int = 40
) {
    private val vehicleData: MutableMap<Int, Ticket> = mutableMapOf()
    private val motorCycleSpotAvailability = MutableList(motorCycleSlotSize) { true }
    private val carSpotAvailability = MutableList(carSlotSize) { true }
    private val busSpotAvailability = MutableList(busSlotSize) { true }

    fun parkVehicle(vehicle: Vehicle): Ticket? {
        when (vehicle) {
            Vehicle.Scooter, Vehicle.Motorcycle -> {
                for (spotNumberIndex in 0..motorCycleSlotSize) {
                    if (motorCycleSpotAvailability[spotNumberIndex]) {
                        val motorCycleTicket = generator.generateTicket(spotNumberIndex + 1, vehicle)
                        vehicleData[motorCycleTicket.id] = motorCycleTicket
                        makeSpotReserved(spotNumberIndex, Vehicle.Motorcycle)
                        return motorCycleTicket
                    }
                }
            }

            Vehicle.Car, Vehicle.SUV -> {
                for (spotNumberIndex in 0..carSlotSize) {
                    if (carSpotAvailability[spotNumberIndex]) {
                        val carTicket = generator.generateTicket(spotNumberIndex + 1, vehicle)
                        vehicleData[carTicket.id] = carTicket
                        makeSpotReserved(spotNumberIndex, vehicle)
                        return carTicket
                    }
                }
            }

            Vehicle.Truck, Vehicle.Bus -> {
                for (spotNumberIndex in 0..busSlotSize) {
                    if (busSpotAvailability[spotNumberIndex]) {
                        val busTicket = generator.generateTicket(spotNumberIndex + 1, vehicle)
                        vehicleData[busTicket.id] = busTicket
                        makeSpotReserved(spotNumberIndex, vehicle)
                        return busTicket
                    }
                }
            }
        }
        return null
    }

    fun unParkVehicle(ticketNumber: Int): Receipt {
        val parkingReceipt = generator.generateReceipt(vehicleData[ticketNumber]!!, SlotLocation.Mall)
        makeSpotEmpty(vehicleData[ticketNumber]!!.spot - 1, vehicleData[ticketNumber]!!.vehicle)
        vehicleData.remove(ticketNumber)
        return parkingReceipt
    }

    private fun makeSpotReserved(slotIndex: Int, vehicle: Vehicle) {
        when (vehicle) {
            Vehicle.Motorcycle, Vehicle.Scooter -> motorCycleSpotAvailability[slotIndex] = false
            Vehicle.Car, Vehicle.SUV -> carSpotAvailability[slotIndex] = false
            Vehicle.Bus, Vehicle.Truck -> busSpotAvailability[slotIndex] = false
        }
    }

    private fun makeSpotEmpty(slotIndex: Int, vehicle: Vehicle) {
        when (vehicle) {
            Vehicle.Motorcycle, Vehicle.Scooter -> motorCycleSpotAvailability[slotIndex] = true
            Vehicle.Car, Vehicle.SUV -> carSpotAvailability[slotIndex] = true
            Vehicle.Bus, Vehicle.Truck -> busSpotAvailability[slotIndex] = true
        }
    }

    fun clearData() {
        vehicleData.clear()
    }
}