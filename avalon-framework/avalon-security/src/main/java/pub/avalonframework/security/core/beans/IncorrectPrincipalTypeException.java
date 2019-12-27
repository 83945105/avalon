package pub.avalonframework.security.core.beans;

import pub.avalonframework.core.beans.AvalonException;

import java.lang.reflect.Method;

/**
 * @author baichao
 */
public final class IncorrectPrincipalTypeException extends AvalonException {

    private Class<?> incorrectPrincipalClass;

    public IncorrectPrincipalTypeException(Class<?> incorrectPrincipalClass, Class<?> exceptionClass, Method exceptionMethod) {
        super(exceptionClass, exceptionMethod, "Incorrect principal class: " + incorrectPrincipalClass.getName());
        this.incorrectPrincipalClass = incorrectPrincipalClass;
    }

    public IncorrectPrincipalTypeException(Class<?> incorrectPrincipalClass, Class<?> exceptionClass, Method exceptionMethod, Throwable cause) {
        super(exceptionClass, exceptionMethod, "Incorrect principal class: " + incorrectPrincipalClass.getName(), cause);
        this.incorrectPrincipalClass = incorrectPrincipalClass;
    }

    public Class<?> getIncorrectPrincipalClass() {
        return incorrectPrincipalClass;
    }
}