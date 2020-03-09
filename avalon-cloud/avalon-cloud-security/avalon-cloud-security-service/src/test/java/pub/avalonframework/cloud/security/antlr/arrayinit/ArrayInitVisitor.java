// Generated from H:/git_repertory/avalon/avalon-cloud/avalon-cloud-security/avalon-cloud-security-service/src/test/java/pub/avalonframework/cloud/security/antlr/arrayinit\ArrayInit.g4 by ANTLR 4.8
package pub.avalonframework.cloud.security.antlr.arrayinit;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ArrayInitParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ArrayInitVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ArrayInitParser#init}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInit(ArrayInitParser.InitContext ctx);
	/**
	 * Visit a parse tree produced by {@link ArrayInitParser#value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(ArrayInitParser.ValueContext ctx);
}