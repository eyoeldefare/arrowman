package game_entities;

//Here we are creating enum to keep our code clean and easy.
// The Actions has 4 props with constant variables.
// We are able to call these constants throught the get method "value()" when we
// want
public enum Actions {

	APPEARING(0), WALKING(1), ATTACKING(2), DYING(3), NONE(4);
	private final int action;

	Actions(int action) {
		this.action = action;
	}

	int value() {
		return this.action;
	}

}
