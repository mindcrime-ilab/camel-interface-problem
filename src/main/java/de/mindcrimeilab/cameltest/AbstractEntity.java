package de.mindcrimeilab.cameltest;

import de.mindcrimeilab.cameltest.Identifiable; 

public abstract class AbstractEntity {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}