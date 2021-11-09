import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;


public class Polynomial {
	private ArrayList<Monomial> monomialList;
	private String strPolynomial;
	private char variable;
	
	Polynomial(Polynomial p){
		this.monomialList = p.monomialList;
		this.strPolynomial = p.strPolynomial;
		this.variable = p.variable;
	}
	
	Polynomial(String strPolynomial){
		this.monomialList = new ArrayList<Monomial>();
		this.strPolynomial = strPolynomial;
	}
	
	public void setVariable(char variable) {
		this.variable = variable;
	}
	
	public void setStrPolynomial(String strPolynomial) {
		this.strPolynomial = strPolynomial;
	}
	
	public ArrayList<Monomial> getMonomialList(){
		return this.monomialList;
	}
	public String getStrPolynomial() {
		return this.strPolynomial;
	}
	
	public char getVariable() {
		return this.variable;
	}
	
	public void updatePolynomial() {
		/*for(int i=0; i < this.getMonomialList().size(); i++) {
			if(this.getMonomialList().get(i).getCoefficient() == 0)
				this.getMonomialList().remove(i);
		}*/
		// alternativa la for-ul de sus:
		for(Iterator<Monomial> it = this.getMonomialList().iterator(); it.hasNext();) {
			Monomial m = it.next();
			if(m.getCoefficient() == 0)
				it.remove();
		}
	}
	
	public void transformToString() {
		this.strPolynomial = "";
		if(this.getVariable() == 0)
			this.setVariable('x');
		for(Monomial m : this.getMonomialList()) {
			this.strPolynomial += m.getSemn() + " " + (int)m.getCoefficient() + this.getVariable() + "^" + m.getPower() + " ";
		}
	}
	
	public void transformIntegrateToString() {
		this.strPolynomial = "";
		for(Monomial m : this.getMonomialList()) {
			this.strPolynomial += m.getSemn() + " " + m.getCoefficient() + this.getVariable() + "^" + m.getPower() + " ";
		}
	}
	
	public void sortDescendingPowerPolynomial() {
		Collections.sort(this.getMonomialList());
		this.transformToString();
	}
	
	public Polynomial addPolynomial(Polynomial p) {
		Polynomial result = new Polynomial(this);
		if(result.getVariable() == 0)
			result.setVariable(p.getVariable());
		for(Monomial m2 : p.getMonomialList()) {
			int found = 0;
			for(Monomial m1 : result.getMonomialList()) {
				if(m1.addMonomial(m2) != 0)
					found = 1;
			}
			if(found == 0)
				result.getMonomialList().add(m2);
		}
		Collections.sort(result.getMonomialList());
		result.updatePolynomial();
		return result;
	}
	
	public Polynomial subtractPolynomial(Polynomial p) {
		Polynomial result = new Polynomial(this);
		for(Monomial m2 : p.getMonomialList()) {
			int found = 0;
			for(Monomial m1 : result.getMonomialList()) {
				if(m1.subtractMonomial(m2) != 0)
					found = 1;
			}
			if(found == 0) {
				result.getMonomialList().add(m2);
				int len = result.getMonomialList().size()-1;
				if(result.getMonomialList().get(len).getSemn() == '-')
					result.getMonomialList().get(len).setSemn('+');
				else
					result.getMonomialList().get(len).setSemn('-');
			}
		}
		Collections.sort(result.getMonomialList());
		result.updatePolynomial();
		return result;
	}
	
	public Polynomial multiplyPolynomial(Polynomial p) {
		Polynomial result = new Polynomial("");
		for(Monomial m1 : this.getMonomialList()) {
			for(Monomial m2 : p.getMonomialList()) {
				int found = 0;
				int index_found = -1;
				for(Monomial m3: result.getMonomialList())
				{
					if(m1.multiplyMonomial(m2).getPower() == m3.getPower()) {
						found = 1;
						index_found = result.getMonomialList().indexOf(m3);
						break;
					}
				}
				if(found == 1) {
					result.getMonomialList().get(index_found).addMonomial(m1.multiplyMonomial(m2));
				}
				else if(found == 0)
					result.getMonomialList().add(m1.multiplyMonomial(m2));
			}
		}
		result.setVariable(this.getVariable());
		Collections.sort(result.getMonomialList());
		result.updatePolynomial();
		return result;
	}
	
	public ArrayList<Polynomial> dividePolynomial(Polynomial dividend, Polynomial divisor) {
		ArrayList<Polynomial> quotientRemainder = new ArrayList<Polynomial>();
		if(divisor.getMonomialList().size() != 0 && divisor.getMonomialList().get(0).getCoefficient() != 0) {
			Monomial t = new Monomial("");
			Polynomial q = new Polynomial("");
			q.setVariable(dividend.getVariable());
			Polynomial r = new Polynomial(dividend);
			while(r.getMonomialList().size() != 0 && r.getMonomialList().get(0).getPower() >= divisor.getMonomialList().get(0).getPower()) {
				t = r.getMonomialList().get(0).divideMonomial(divisor.getMonomialList().get(0));
				q.getMonomialList().add(t);
				q.transformIntegrateToString();
				Monomial tmp = new Monomial(q.getMonomialList().get(q.getMonomialList().size()-1));
				Polynomial ultimulTermenMonom = new Polynomial("");
				ultimulTermenMonom.getMonomialList().add(tmp);
				ultimulTermenMonom.transformToString();
				Polynomial resultMultiply = new Polynomial(ultimulTermenMonom.multiplyPolynomial(divisor));
				resultMultiply.transformToString();
				r = r.subtractPolynomial(ultimulTermenMonom.multiplyPolynomial(divisor));
				r.updatePolynomial();
				r.transformToString();	
			}
			quotientRemainder.add(q);
			quotientRemainder.add(r);
		}
		return quotientRemainder;
	}
	
	public Polynomial derivate() {
		Polynomial result = new Polynomial(this);
		for(Monomial m : result.getMonomialList()) {
			m.derivateMonomial();
		}
		result.updatePolynomial();
		return result;
	}
	
	public Polynomial integrate() {
		Polynomial result = new Polynomial(this);
		for(Monomial m : result.getMonomialList()) {
			m.integrateMonomial();
		}
		result.updatePolynomial();
		return result;
	}
}
