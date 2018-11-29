class Date212Exception extends RuntimeException {
	public Date212Exception(String Date) {
		super("This is not a valid date: " + Date);
	}
}