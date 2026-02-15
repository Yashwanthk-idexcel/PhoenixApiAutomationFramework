package Constants;

public enum ServiceLocation {

	SERVICE_LOCATION_A(1), SERVICE_LOCATION_B(1), SERVICE_LOCATION_C(1);
	// Currently B & C location are not working, select 1 only

	int code;

	private ServiceLocation(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}
