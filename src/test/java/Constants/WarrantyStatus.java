package Constants;

public enum WarrantyStatus {

	IN_WARRANTY(1), OUT_WARRANTY(2);

	int code;

	private WarrantyStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
