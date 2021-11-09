import java.util.*;
import java.util.regex.*;


public class Model {
	private Polynomial polynomial1;
	private Polynomial polynomial2;
	private ArrayList<Boolean> op;
	Pattern monomialPattern = Pattern.compile("[+-]?[^-+]+"); // get monomials
	Pattern coeffPattern = Pattern.compile("\\d*(?=(\\s*\\*)|(\\s*([a-z]|[A-Z])))");
	Pattern variablePattern = Pattern.compile("([A-Z]|[a-z])");
	Pattern lastCoeffPattern = Pattern.compile("\\d+");
	Pattern powerPattern = Pattern.compile("(?<=\\^\\s*)\\d+");
	
	public void setOperationList(ArrayList<Boolean> op) {
		this.op = op;
	}
	
	public ArrayList<Boolean> getOperationList() {
		return this.op;
	}
	
	public void setPolynomial1(String strPolynomial) {
		this.polynomial1 = new Polynomial(strPolynomial);
		Matcher monomialMatcher = monomialPattern.matcher(this.polynomial1.getStrPolynomial());
		while(monomialMatcher.find()) {
			this.polynomial1.getMonomialList().add(new Monomial(monomialMatcher.group()));
			Monomial currentMonomial = this.polynomial1.getMonomialList().get(this.polynomial1.getMonomialList().size()-1);
			if(currentMonomial.getStrMonom().charAt(0) == '-')	currentMonomial.setSemn('-');
			else	currentMonomial.setSemn('+');		
			Matcher coeffMatcher = coeffPattern.matcher(currentMonomial.getStrMonom());
			Matcher variableMatcher = variablePattern.matcher(currentMonomial.getStrMonom());
			Matcher powerMatcher = powerPattern.matcher(currentMonomial.getStrMonom());
			if(coeffMatcher.find()) {
				if(coeffMatcher.group().isEmpty())
					currentMonomial.setCoefficient(1.0);
				else
					currentMonomial.setCoefficient(Double.parseDouble(coeffMatcher.group()));
			}
			int powerSet = 0;
			if(powerMatcher.find()) {
				currentMonomial.setPower(Integer.parseInt(powerMatcher.group()));
				powerSet = 1;
			}
			if(variableMatcher.find()) {
				if(currentMonomial.getPower() == 0 && powerSet == 0)
					currentMonomial.setPower(1);
				this.polynomial1.setVariable(variableMatcher.group().charAt(0));
			}
		}
		if(this.polynomial1.getMonomialList().size() > 0) {
			Monomial currentMonomial = this.polynomial1.getMonomialList().get(this.polynomial1.getMonomialList().size() - 1);
			Matcher lastCoeffMatcher = lastCoeffPattern.matcher(currentMonomial.getStrMonom());
			if(lastCoeffMatcher.find())
				currentMonomial.setCoefficient(Double.parseDouble(lastCoeffMatcher.group()));
			this.polynomial1.sortDescendingPowerPolynomial();
		}
	}
	
	public void setPolynomial2(String strPolynomial) {
		this.polynomial2 = new Polynomial(strPolynomial);
		Matcher monomialMatcher = monomialPattern.matcher(this.polynomial2.getStrPolynomial());
		while(monomialMatcher.find()) {
			this.polynomial2.getMonomialList().add(new Monomial(monomialMatcher.group()));
			Monomial currentMonomial = this.polynomial2.getMonomialList().get(this.polynomial2.getMonomialList().size()-1);
			if(currentMonomial.getStrMonom().charAt(0) == '-')	currentMonomial.setSemn('-');
			else	currentMonomial.setSemn('+');
			Matcher coeffMatcher = coeffPattern.matcher(currentMonomial.getStrMonom());
			Matcher variableMatcher = variablePattern.matcher(currentMonomial.getStrMonom());
			Matcher powerMatcher = powerPattern.matcher(currentMonomial.getStrMonom());
			if(coeffMatcher.find()) {
				if(coeffMatcher.group().isEmpty())
					currentMonomial.setCoefficient(1.0);
				else
					currentMonomial.setCoefficient(Double.parseDouble(coeffMatcher.group()));
			}
			else
				currentMonomial.setCoefficient(1.0);
			int powerSet = 0;
			if(powerMatcher.find()) {
				currentMonomial.setPower(Integer.parseInt(powerMatcher.group()));
				powerSet = 1;
			}
			if(variableMatcher.find()) {
				if(currentMonomial.getPower() == 0 && powerSet == 0)
					currentMonomial.setPower(1);
				this.polynomial2.setVariable(variableMatcher.group().charAt(0));
			}
		}
		if(this.polynomial2.getMonomialList().size() > 0) {
			Monomial currentMonomial = this.polynomial2.getMonomialList().get(this.polynomial2.getMonomialList().size() - 1);
			Matcher lastCoeffMatcher = lastCoeffPattern.matcher(currentMonomial.getStrMonom());
			if(lastCoeffMatcher.find()) {
				currentMonomial.setCoefficient(Double.parseDouble(lastCoeffMatcher.group()));
			}
			this.polynomial2.sortDescendingPowerPolynomial();
		}
	}
	
	public Polynomial getPolynomial1() {
		return this.polynomial1;
	}
	public Polynomial getPolynomial2() {
		return this.polynomial2;
	}
	
	public String addPolynomials(Polynomial p1, Polynomial p2) {
		Polynomial result = p1.addPolynomial(p2);
		result.transformToString();
		return result.getStrPolynomial();
	}
	
	public String subtractPolynomials(Polynomial p1, Polynomial p2) {
		Polynomial result = p1.subtractPolynomial(p2);
		result.transformToString();
		return result.getStrPolynomial();
	}
	
	public String multiplyPolynomials(Polynomial p1, Polynomial p2) {
		Polynomial result = p1.multiplyPolynomial(p2);
		result.transformToString();
		return result.getStrPolynomial();
	}
	
	public String dividePolynomials(Polynomial p1, Polynomial p2) {
		ArrayList<Polynomial> quotientRemainder = p1.dividePolynomial(p1, p2);
		return quotientRemainder.get(0).getStrPolynomial() + " : " +   quotientRemainder.get(1).getStrPolynomial();
	}
	
	public String derviatePolynomial(Polynomial p) {
		Polynomial result = p.derivate();
		result.transformToString();
		return result.getStrPolynomial();
	}
	
	public String integratePolynomial(Polynomial p) {
		Polynomial result = p.integrate();
		result.transformIntegrateToString();
		return result.getStrPolynomial();
	}
	
}
