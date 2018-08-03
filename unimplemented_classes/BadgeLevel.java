package ecourse.domain;

public enum BadgeLevel {

	BRONZE("Bronze"),
	SILVER("Silver"),
	GOLD("Gold");
	
	private final String text;

	private BadgeLevel(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}

}
