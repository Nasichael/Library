package library.inventory;

import library.data.Booking;
import library.data.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class BookingInventory {

    List<Booking> bookings = new ArrayList<>();


    public void addBooking(Booking booking) {

        bookings.add(booking);
    }

    public Collection<Booking> findBookingsForUser(User user) {

        Collection<Booking> userCollection = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getUser().equals(user)) {
                userCollection.add(booking);
            }
        }
        return userCollection;
    }
}
