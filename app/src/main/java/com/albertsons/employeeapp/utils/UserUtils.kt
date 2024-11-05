package com.albertsons.employeeapp.utils

import com.albertsons.employeeapp.data.responses.Dob
import com.albertsons.employeeapp.data.responses.EmployeesResponse
import com.albertsons.employeeapp.data.responses.Location
import com.albertsons.employeeapp.data.responses.Name
import com.albertsons.employeeapp.data.responses.Picture
import com.albertsons.employeeapp.data.responses.Street
import com.albertsons.employeeapp.data.responses.User

object UserUtils {
 
    fun createSampleUserList(): EmployeesResponse {
        val user1 = User(
            gender = "female",
            name = Name(title = "Mademoiselle", first = "Henriette", last = "Lucas"),
            location = Location(
                street = Street(number = 6633, name = "Rue Bossuet"),
                city = "Langnau im Emmental",
                state = "Valais",
                country = "Switzerland"
            ),
            email = "henriette.lucas@example.com",
            dob = Dob(date = "1991-10-13T01:20:47.452Z", age = 33),
            phone = "076 003 05 99",
            picture = Picture(
                large = "https://randomuser.me/api/portraits/women/14.jpg",
                medium = "https://randomuser.me/api/portraits/med/women/14.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/women/14.jpg"
            ),
            city = "hitech City",
            state = "Telangana",
            country = "India",
            postcode = "500081"
        )
 
        val user2 = User(
            gender = "male",
            name = Name(title = "Mr", first = "Draško", last = "Vidić"),
            location = Location(
                street = Street(number = 9512, name = "Pivljanska"),
                city = "Svrljig",
                state = "Jablanica",
                country = "Serbia"
            ),
            email = "drasko.vidic@example.com",
            dob = Dob(date = "1988-12-09T03:16:42.544Z", age = 35),
            phone = "014-8083-329",
            picture = Picture(
                large = "https://randomuser.me/api/portraits/men/22.jpg",
                medium = "https://randomuser.me/api/portraits/med/men/22.jpg",
                thumbnail = "https://randomuser.me/api/portraits/thumb/men/22.jpg"
            ),
            city = "Kondapur",
            state = "Telangana",
            country = "India",
            postcode = "500084"
        )

        return EmployeesResponse(listOf(user1, user2))
    }
 
}