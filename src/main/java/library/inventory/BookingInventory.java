package library.inventory;

import library.data.Booking;
import library.data.User;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookingInventory {

    public List<Booking> bookings = new ArrayList<>();

    public List<Booking> getBookings() {
        return bookings;
    }


    public void addBooking(Booking booking) {
        bookings.add(booking);

    }

    public void removeBook(Booking booking) {
        bookings.remove(booking);

    }

    public Booking getById(int bookingId) {
        return bookings.get(bookingId);
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