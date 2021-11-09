import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class TestMessageUtil {
	Model m = new Model();
	String strPol1 = "x^2 - 1";
	String strPol2 = "x - 1";
	
	@Test
	public void testAddition() {
		m.setPolynomial1(strPol1);
		m.setPolynomial2(strPol2);
		Polynomial p1 = new Polynomial(m.getPolynomial1());
		Polynomial p2 = new Polynomial(m.getPolynomial2());
		String t = m.addPolynomials(p1, p2);
		assertEquals(t, "+ 1x^2 + 1x^1 - 2x^0 ");
	}
	
	@Test
	public void testSubtraction() {
		m.setPolynomial1(strPol1);
		m.setPolynomial2(strPol2);
		Polynomial p1 = new Polynomial(m.getPolynomial1());
		Polynomial p2 = new Polynomial(m.getPolynomial2());
		String t = m.subtractPolynomials(p1, p2);
		assertEquals("+ 1x^2 - 1x^1 ", t);
	}
	
	@Test
	public void testMultiplication() {
		m.setPolynomial1(strPol1);
		m.setPolynomial2(strPol2);
		Polynomial p1 = new Polynomial(m.getPolynomial1());
		Polynomial p2 = new Polynomial(m.getPolynomial2());
		String t = m.multiplyPolynomials(p1, p2);
		assertEquals("+ 1x^3 - 1x^2 - 1x^1 + 1x^0 ", t);
	}
	
	@Test
	public void testDivision() {
		m.setPolynomial1(strPol1);
		m.setPolynomial2(strPol2);
		Polynomial p1 = new Polynomial(m.getPolynomial1());
		Polynomial p2 = new Polynomial(m.getPolynomial2());
		String t = m.dividePolynomials(p1, p2);
		assertEquals("+ 1.0x^1 + 1.0x^0  : ", t);
	}
	
	@Test
	public void testDerivation() {
		m.setPolynomial1(strPol1);
		m.setPolynomial2(strPol2);
		Polynomial p1 = new Polynomial(m.getPolynomial1());
		String t = m.derviatePolynomial(p1);
		assertEquals("+ 2x^1 ", t);
	}
	
	@Test
	public void testIntegration() {
		m.setPolynomial1(strPol1);
		m.setPolynomial2(strPol2);
		Polynomial p1 = new Polynomial(m.getPolynomial1());
		String t = m.integratePolynomial(p1);
		assertEquals("+ 0.3333333333333333x^3 - 1.0x^1 ", t);
	}
	
}