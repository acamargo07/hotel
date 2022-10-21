package com.hotel.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "room")
public class Room implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7080253754354386317L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

//    @Column(name = "available")
//    private int available;

    public Room() {

    }

    public Room(long id, String number ) {
        this.id = id;
        this.number = number;
    }

    public Room(String number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

//    public int getAvailable() {
//        return available;
//    }
//
//    public void setAvailable(int available) {
//        this.available = available;
//    }

    @Override
    public String toString() {
        return String.format("Room [id = '%d', number = '%s']",
                id, number);
    }
}
