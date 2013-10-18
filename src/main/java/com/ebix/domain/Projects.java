package com.ebix.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Projects implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 23233L;

	public Projects() {

	}

	@GeneratedValue(strategy = IDENTITY)
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "name")
	private String name;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cat_id")
	private Cats cats;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cats getCats() {
		return cats;
	}

	public void setCats(Cats cats) {
		this.cats = cats;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Projects))
			return false;
		Projects projectsOther = (Projects) other;

		return (this.getId() == projectsOther.getId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());

		return result;
	}

}
