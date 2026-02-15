package Constants;

public enum Model {

	NEXUS_2_BLUE(1), GALAXY(2);
	
	
	int code;
	Model(int code) { // By default enum constructors are private
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
}
