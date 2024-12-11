package andersen.dev.tickets.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import andersen.dev.tickets.aspect.annotation.EnableDML;
import andersen.dev.tickets.exeption.DMLDisabledException;
import lombok.RequiredArgsConstructor;

/**
 * Technically it must work, but Aspect is not called during runtime.
 */
@Aspect
@Component
@RequiredArgsConstructor
@Order(1)
public class DmlEnableAspect {
	@Autowired
	private final Environment environment;

	@Pointcut("@annotation(enableDml)")
	public void enableDmlAnnotationPointcut(EnableDML enableDml) {
	}

	@Before("enableDmlAnnotationPointcut(enableDml)")
	public void checkDmlPermission(EnableDML enableDml) {
		boolean dmlEnabled = Boolean.parseBoolean(environment.getProperty("dml.enabled", "true"));
		if (!dmlEnabled) {
			throw new DMLDisabledException();
		}
	}

}
