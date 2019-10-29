package elec5619.psychologyservice.domain;

import lombok.Getter;

@Getter
public class PersonalityType {

	private String key;

	public static class Builder {

		PersonalityDimentionType[] dimension = new PersonalityDimentionType[4];

		static public Builder getInstance() {
			return new Builder();
		}

		public PersonalityType build() throws Exception {
			for (int x = 0; x < 4; x++) {// check any dimension ius null
				if (dimension[x] == null)
					throw new Exception("All dimention of type must be defined");
			}
			PersonalityType obj = new PersonalityType();
			obj.key = getKey();
			return obj;
		}

		public void setDimention(PersonalityDimentionType dim) {
			switch (dim) {
			case E:
			case I:
				dimension[0] = dim;
				break;
			case S:
			case N:
				dimension[1] = dim;
				break;
			case T:
			case F:
				dimension[2] = dim;
				break;
			case J:
			case P:
				dimension[3] = dim;
				break;

			}
		}

		public String getKey() {
			StringBuilder sb = new StringBuilder();
			for (int x = 0; x < 4; x++) {
				sb.append(dimension[x]);
			}
			return sb.toString().trim().toUpperCase();
		}
	}

	public static void main(String[] args) {
		PersonalityType.Builder.getInstance();
	}
}
