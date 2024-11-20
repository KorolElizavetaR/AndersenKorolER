package andersen.mvn.entity;

import com.github.ricksbrown.cowsay.Cowsay;

public class TalkingAnimal {
	public static String talkingCow() {
		return Cowsay.say(new String[] {"-f", "Cow", "HelloWorld"});
	}
}
