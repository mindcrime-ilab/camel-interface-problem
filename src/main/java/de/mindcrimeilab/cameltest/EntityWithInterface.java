package de.mindcrimeilab.cameltest;

import de.mindcrimeilab.cameltest.Identifiable; 
import de.mindcrimeilab.cameltest.AbstractEntity;

public class EntityWithInterface extends AbstractEntityWithInterface implements Identifiable<Long> {
	private String text;
	
    public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}