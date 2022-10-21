package com.hotel.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.model.Room;
import com.hotel.model.User;
import com.hotel.service.BookingService;

@RestController
public class RoomController {

	@Autowired
	private BookingService bookingService;

	@RequestMapping("room/available")
	public String getAvailableRooms(@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
			@RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo) {
		if (bookingService.findBookingAvailable(dateFrom, dateTo)) {
			return "Availabel";
		}
		return "Not availabel";
	}

	@RequestMapping(value = "/room/book", method = RequestMethod.POST)
	public String bookRoom(@RequestParam("roomId") Room room, @RequestParam("userId") User user,
			@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
			@RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo) {

		try {
			Boolean valid = bookingService.validations(room, dateFrom, dateTo);
			if (valid) {
				bookingService.bookRoom(room, user, dateFrom, dateTo);
			}
		} catch (Exception e) {
			return e.getMessage();
		}

		return "Success";
	}
	
	@RequestMapping(value = "room/book/cancel", method = RequestMethod.POST)
	public String calcelBookRoom(@RequestParam("roomId") Room room, @RequestParam("userId") User user,
			@RequestParam("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
			@RequestParam("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo) {

		return bookingService.cancelBoodking(room, user, dateFrom, dateTo);
	}
	
	@RequestMapping(value = "room/book/update", method = RequestMethod.POST)
	public String updateBookRoom(@RequestParam("roomId") Room room, @RequestParam("userId") User user,
			@RequestParam("from - old") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom,
			@RequestParam("to - old") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo,
			@RequestParam("from - new") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFromNew,
			@RequestParam("to - new") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateToNew) {

		return bookingService.updateBoodking(room, user, dateFrom, dateTo, dateFromNew, dateToNew);
	}
}
