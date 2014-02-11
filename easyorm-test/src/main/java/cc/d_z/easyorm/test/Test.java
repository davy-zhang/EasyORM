package cc.d_z.easyorm.test;

import static org.junit.Assert.*;

/**
 * @author davy <br>
 *         2014年1月22日 上午2:55:16 <br>
 *         <B>The default encoding is UTF-8 </B><br>
 *         email: davy@d-z.cc<br>
 *         <a href="http://d-z.cc">d-z.cc</a><br>
 */
public class Test {

	/**
	 * @param
	 * 
	 */
	protected void fail() {
		Throwable t = new Throwable();
		StackTraceElement stack[] = t.getStackTrace();
		StackTraceElement refStack = stack[1];
		org.junit.Assert.fail("在" + refStack.getClassName() + "类的" + refStack.getMethodName() + "方法的" + refStack.getLineNumber() + "行中有错误，请注意查看");
	}
	
	protected void fail(Throwable t) {
		throw new AssertionError(t);
	}
}
