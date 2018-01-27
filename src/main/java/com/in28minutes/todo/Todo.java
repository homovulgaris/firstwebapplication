package com.in28minutes.todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class Todo {

	private int id;
	private String user;
	
	@Size(min=6, message = "Enter atleast 6 character long text, please!")
	private String desc;
	@DateTimeFormat(pattern = "yyyy'-'MM'-'dd'T'HH':'mm")
	private LocalDateTime targetDate;
	@NotNull(message= "Done status can not be null!")
	private boolean isDone;

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd'-'MM'-'yyyy HH':'mm");
	
	/**
	 * @param id
	 * @param user
	 * @param desc
	 * @param targetDate
	 * @param isDone
	 */
	public Todo(int id, String user, String desc, LocalDateTime targetDate, boolean isDone) {
		this.id = id;
		this.user = user;
		
		
		this.desc = desc;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}

	public Todo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public LocalDateTime getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDateTime targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + id;
		result = prime * result + (isDone ? 1231 : 1237);
		result = prime * result + ((targetDate == null) ? 0 : targetDate.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Todo)) {
			return false;
		}
		Todo other = (Todo) obj;
		if (desc == null) {
			if (other.desc != null) {
				return false;
			}
		} else if (!desc.equals(other.desc)) {
			return false;
		}
		if (id != other.id) {
			return false;
		}
		if (isDone != other.isDone) {
			return false;
		}
		if (targetDate == null) {
			if (other.targetDate != null) {
				return false;
			}
		} else if (!targetDate.equals(other.targetDate)) {
			return false;
		}
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("Todo [id=%s, user=%s, desc=%s, targetDate=%s, isDone=%s]", id, user, desc, targetDate,
				isDone);
	}

	/**
	 * @param desc
	 */
	public Todo(String desc) {
		this.desc = desc;
	}
	
	public String showFormatedDateTime() {
		return getTargetDate() != null ? getTargetDate().format(formatter) : null;
	}
	

}
