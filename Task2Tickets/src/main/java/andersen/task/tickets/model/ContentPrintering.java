package andersen.task.tickets.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public interface ContentPrintering {
	default void printer() {
		System.out.println("Fields");
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				field.setAccessible(true);
				System.out.println(
						"\t" + field.getType().getSimpleName() + " " + field.getName() + " : " + field.get(this));
				field.setAccessible(false);
			} catch (IllegalAccessException ex) {
				System.out.println(ex.getLocalizedMessage());
			}
		}
		StringBuilder parameters = new StringBuilder();
		System.out.println("Methods: ");
		Method[] methods = this.getClass().getDeclaredMethods();
		for (Method method : methods) {
			parameters.delete(0, parameters.length());
			System.out.print("\t" + method.getReturnType().getSimpleName() + " " + method.getName());

			Class<?>[] parameterTypes = method.getParameterTypes();
			if (parameterTypes.length == 0) {
				System.out.println("( );");
				continue;
			}
			for (Class<?> params : parameterTypes) {
				parameters.append(params.getSimpleName() + ", ");
			}
			parameters.setLength(parameters.length() - 2);
			System.out.println("(" + parameters.toString() + ");");
		}
	}
}
