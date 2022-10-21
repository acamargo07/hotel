package com.hotel.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.model.Booking;
import com.hotel.model.Room;
import com.hotel.model.User;
import com.hotel.repository.IBookingRepository;

@Service
public class BookingService {

	@Autowired
	private IBookingRepository bookingRepository;

	public Room bookRoom(Room room, User user, LocalDate dateFrom, LocalDate dateTo) {
		Booking bookingEntry = new Booking(user, room, dateFrom, dateTo);
		return bookingRepository.save(bookingEntry).getRoom_id();
	}

	public Boolean findBookingAvailable(LocalDate dateFrom, LocalDate dateTo) {
		return isAvailable(dateFrom, dateTo);
	}

	public List<Booking> findBookingByUserDate(User user, LocalDate dateFrom) {
		return bookingRepository.findByUser(user);
	}

	private Boolean isAvailable(LocalDate dateFrom, LocalDate dateTo) {

		Booking book = bookingRepository.isAvailable(dateFrom, dateTo);
		if (book == null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public Boolean validations(Room room, LocalDate dateFrom, LocalDate dateTo) throws Exception {

		Boolean val = Boolean.TRUE;

		if (!isAvailable(dateFrom, dateTo)) {
			throw new Exception("Not available");
			// if data is <= a 3
		} else if (ChronoUnit.DAYS.between(dateFrom, dateTo) > 3) {
			throw new Exception("Is not permited period biggger than 3 days");
			// v
		} else if (ChronoUnit.DAYS.between(LocalDate.now(), dateFrom) > 30) {
			throw new Exception("Is not permited books before 30 days");
		}

		return val;
	}

	public String cancelBoodking(Room room, User user, LocalDate dateFrom, LocalDate dateTo) {

		Booking book = bookingRepository.findBook(room.getId(), user.getId(), dateFrom, dateTo);
		if (book != null) {
			bookingRepository.delete(book);
		} else {
			return "Register not found";
		}
		return "Booking canceled - Success";
	}
	
	public String updateBoodking(Room room, User user, LocalDate dateFrom, LocalDate dateTo, LocalDate dateFromNew, LocalDate dateToNew) {

		Booking book = bookingRepository.findBook(room.getId(), user.getId(), dateFrom, dateTo);
		if (book != null) {
			book.setDate_from(dateFromNew);
			book.setDate_to(dateToNew);
			bookingRepository.save(book);
		} else {
			return "Register not found";
		}
		return "Booking update - Success";
	}
}
