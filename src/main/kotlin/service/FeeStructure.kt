package service

import Vehicle

class FeeStructure {
    private val mallFeesBreakdown: MutableMap<Vehicle, List<Int>> = mutableMapOf()
    private val airportFeesBreakdown: MutableMap<Vehicle, List<Int>> = mutableMapOf()
    private val stadiumFeesBreakdown: MutableMap<Vehicle, List<Int>> = mutableMapOf()

    private fun setMallBreakdown(
        motoList: List<Int> = listOf(10),
        carList: List<Int> = listOf(20),
        busList: List<Int> = listOf(50)
    ) {
        mallFeesBreakdown[Vehicle.Motorcycle] = motoList
        mallFeesBreakdown[Vehicle.Car] = carList
        mallFeesBreakdown[Vehicle.Bus] = busList
    }

    private fun setStadiumBreakdown(motoList: List<Int> = listOf(30, 60, 100), carList: List<Int> = listOf(60, 120, 200)) {
        stadiumFeesBreakdown[Vehicle.Motorcycle] = motoList
        stadiumFeesBreakdown[Vehicle.Car] = carList
    }

    private fun setAirportBreakdown(motoList: List<Int> = listOf(0, 40, 60, 80), carList: List<Int> = listOf(60, 80, 100)) {
        airportFeesBreakdown[Vehicle.Motorcycle] = motoList
        airportFeesBreakdown[Vehicle.Car] = carList
    }

    init {
        setMallBreakdown()
        setStadiumBreakdown()
        setAirportBreakdown()
    }

    fun getMallBreakdown(): MutableMap<Vehicle, List<Int>> {
        return mallFeesBreakdown
    }

    fun getStadiumBreakdown(): MutableMap<Vehicle, List<Int>> {
        return stadiumFeesBreakdown
    }

    fun getAirportBreakdown(): MutableMap<Vehicle, List<Int>> {
        return airportFeesBreakdown
    }



}