package org.solver.client.common.comboboxitem;

public class ComboBoxItem {
	
	protected String key;
	protected String value;
	
	public ComboBoxItem(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(final String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return key;
	}

}
