import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MallFloorTest{
    private val motorCycleSlotSizeInMall = 100
    private val mallFloor = MallFloor(motorCycleSlotSize = motorCycleSlotSizeInMall)

    @AfterEach
    fun tearDown() {
        mallFloor.clearData()
    }

    @Test
    fun generateNewTicketForMallFloor() {

        val motorCycleTicket1 = mallFloor.parkVehicle(vehicle = Vehicle.Motorcycle)
        val motorCycleTicket2 = mallFloor.parkVehicle(vehicle = Vehicle.Motorcycle)
        assertEquals(1, motorCycleTicket1!!.spot)
        assertEquals(2, motorCycleTicket2!!.spot)
        assertEquals(1, motorCycleTicket1.id)
        assertEquals(2, motorCycleTicket2.id)
    }

    @Test
    fun testForRemovingMotorCycleFromParking() {
        val motorCycleTicket1 = mallFloor.parkVehicle(vehicle = Vehicle.Motorcycle)
        val motorCycleTicket2 = mallFloor.parkVehicle(vehicle = Vehicle.Motorcycle)
        val unparkmotorCycle1 = mallFloor.unParkVehicle(ticketNumber = motorCycleTicket1!!.id)
        val motorCycleTicket3 = mallFloor.parkVehicle(vehicle = Vehicle.Motorcycle)
        assertEquals(unparkmotorCycle1.receiptNumber,motorCycleTicket1.id)
        println(unparkmotorCycle1.fees)
        println(unparkmotorCycle1.exitDateTime)
        assertEquals(1, motorCycleTicket3!!.spot)
        assertEquals(3, motorCycleTicket3.id)
    }
}