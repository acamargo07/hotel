package com.hotel.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hotel.model.Booking;
import com.hotel.model.User;

public interface IBookingRepository extends JpaRepository<Booking, Long> {

	List<Booking> findByUser(User user);

	@Query(value = "select * from booking where user_id = :userId and room_id = :roomId and "
			+ "date_from  = to_date( :dateFrom, 'yyyy-mm-dd') and "
			+ "date_to  = to_date( :dateTo, 'yyyy-mm-dd')", nativeQuery = true)
	Booking findBook(Long roomId, Long userId, LocalDate dateFrom, LocalDate dateTo);

	@Query(value = "select * from BOOKING where" + " date_from between to_date( :dateFrom, 'yyyy-mm-dd') and"
			+ " to_date(:dateTo, 'yyyy-mm-dd') LIMIT 1", nativeQuery = true)
	Booking isAvailable(LocalDate dateFrom, LocalDate dateTo);
}
