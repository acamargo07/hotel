package com.hotel.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID",updatable = false, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room_id;

    @Column(name = "date_from")
    private LocalDate date_from;

    @Column(name = "date_to")
    private LocalDate date_to;


    public Booking() {

    }

    public Booking(User user, Room room_id, LocalDate date_from, LocalDate date_to) {
        this.user = user;
        this.room_id = room_id;
        this.date_from = date_from;
        this.date_to = date_to;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Room room_id) {
        this.room_id = room_id;
    }

    public LocalDate getDate_from() {
        return date_from;
    }

    public void setDate_from(LocalDate date_from) {
        this.date_from = date_from;
    }

    public LocalDate getDate_to() {
        return date_to;
    }

    public void setDate_to(LocalDate date_to) {
        this.date_to = date_to;
    }


    @Override
    public String toString() {
        return String.format("BookingJournal [id = '%d', user = '%d', room_id = '%d', date_from = '%s', date_to = '%s']",
                id, user.getId(), room_id.getId(), date_from, date_to);
    }
}
