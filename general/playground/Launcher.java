import Base
import Base
import Base

public class Launcher<T extends Base> {
	T innerObject;
	public Launcher(T object) {
		this.innerObject = object;
	}

	public void printInfo() {
		innerObject.getInfo();
	}

	public static void main(String[] args) {
		Launcher<Child_1> lc1 = new Launcher<Child_1>(new Child_1());
		Launcher<Child_2> lc2 = new Launcher<Child_2>(new Child_2());

	}
}
