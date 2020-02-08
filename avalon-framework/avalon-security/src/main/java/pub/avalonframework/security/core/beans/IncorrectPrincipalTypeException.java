package pub.avalonframework.security.core.beans;

import pub.avalonframework.core.beans.AvalonException;

/**
 * @author baichao
 */
public final class IncorrectPrincipalTypeException extends AvalonException {

    private Class<?> incorrectPrincipalClass;

    public IncorrectPrincipalTypeException(Class<?> incorrectPrincipalClass) {
        super("Incorrect principal class: " + incorrectPrincipalClass.getName());
        this.incorrectPrincipalClass = incorrectPrincipalClass;
    }

    public IncorrectPrincipalTypeException(Class<?> incorrectPrincipalClass, Throwable cause) {
        super("Incorrect principal class: " + incorrectPrincipalClass.getName(), cause);
        this.incorrectPrincipalClass = incorrectPrincipalClass;
    }

    public Class<?> getIncorrectPrincipalClass() {
        return incorrectPrincipalClass;
    }
}