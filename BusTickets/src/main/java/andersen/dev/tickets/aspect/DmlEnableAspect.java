package andersen.dev.tickets.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import andersen.dev.tickets.exeption.DMLDisabledException;
import lombok.RequiredArgsConstructor;

/**
 * Technically it must work, but Aspect is not called during runtime.
 */
@Aspect
@Component
@RequiredArgsConstructor
public class DmlEnableAspect {
	@Value("${dml.enabled:true}")
	private boolean dmlEnabled;

	@Before(value = "@annotation(EnableDML)")
	public void checkDmlPermission() {
		System.out.println("dmlEnabled = " + dmlEnabled);
		if (!dmlEnabled) {
			throw new DMLDisabledException();
		}
	}

}
