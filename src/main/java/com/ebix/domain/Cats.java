package com.ebix.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cats")
public class Cats implements Serializable {

	public Cats() {

	}

	public Cats(Integer id, String name, Set<Projects> projects) {
		this.id = id;
		this.name = name;
		this.cats = projects;
	}

	public Cats(String name, Set<Projects> projects) {
		this.name = name;
		this.cats = projects;
	}

	@GeneratedValue(strategy = IDENTITY)
	@Id
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "name")
	private String name;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cats")
	private Set<Projects> cats = new HashSet<Projects>(0);

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

	public Set<Projects> getCats() {
		return cats;
	}

	public void setCats(Set<Projects> cats) {
		this.cats = cats;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Cats))
			return false;
		Cats castOther = (Cats) other;

		return (this.getId() == castOther.getId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());

		return result;
	}

	private static final long serialVersionUID = 20090202L;
}
