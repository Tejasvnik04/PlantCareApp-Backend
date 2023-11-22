package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="remainderTable")
public class Remainders {

	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    Integer id;

	    @Column
	    String reminder;

	    

	   
	    public Integer getId() {
	        return id;
	    }

	    public void setId(Integer id) {
	        this.id = id;
	    }

	    public String getReminder() {
	        return reminder;
	    }

	    public void setReminder(String reminder) {
	        this.reminder = reminder;
	    }


	
}
