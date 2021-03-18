public class Monomial implements Comparable<Monomial> {
	private double coefficient;
	private int power;
	private String strMonom;
	private char semn;
	
	Monomial(String strMonom){
		this.coefficient = 0;
		this.power = 0;
		this.strMonom = strMonom;
		this.semn = '+';
	}
	Monomial(Monomial m){
		this.coefficient = m.coefficient;
		this.power = m.power;
		this.strMonom = m.strMonom;
		this.semn = m.semn;
	}
	
	public double getCoefficient() {
		return this.coefficient;
	}
	public int getPower() {
		return this.power;
	}
	public String getStrMonom() {
		return this.strMonom;
	}
	public char getSemn() {
		return this.semn;
	}
	
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public void setSemn(char semn) {
		this.semn = semn;
	}
	
	@Override
	public int compareTo(Monomial compareMon) {
		return compareMon.getPower() - this.power;
	}
	
	public int addMonomial(Monomial m) {
		if(this.getPower() != m.getPower())
			return 0;
		else {
			if(this.getSemn() == '+' && m.getSemn() == '+') {
				this.coefficient += m.getCoefficient();
			}
			else if(this.getSemn() == '+' && m.getSemn() == '-') {
				this.coefficient += (-1) * m.getCoefficient();
			}
			else if(this.getSemn() == '-' && m.getSemn() == '+') {
				this.coefficient *= -1;
				this.coefficient += m.getCoefficient();
			}
			else {
				this.coefficient *= -1;
				this.coefficient += (-1) * m.getCoefficient();
			}
			if(this.coefficient < 0)
			{
				this.coefficient *= -1;
				this.semn = '-';
			}
			else
				this.semn = '+';
			return 1;
		}
	}
	
	public int subtractMonomial(Monomial m) {
		if(this.getPower() != m.getPower())
			return 0;
		else {
			if(this.getSemn() == '+' && m.getSemn() == '+')
				this.coefficient -= m.coefficient;
			else if(this.getSemn() == '+' && m.getSemn() == '-')
				this.coefficient += m.coefficient;
			else if(this.getSemn() == '-' && m.getSemn() == '+') {
				this.coefficient *= -1;
				this.coefficient -= m.coefficient;
			}
			else {
				this.coefficient *= -1;
				this.coefficient += m.coefficient;
			}
		}
		if(this.coefficient < 0) {
			this.coefficient *= -1;
			this.semn = '-';
		}
		else
			this.semn = '+';
		return 1;
	}
	
	public Monomial multiplyMonomial(Monomial m) {
		Monomial result = new Monomial("");
		result.coefficient = this.coefficient * m.getCoefficient();
		if((this.getSemn() == '-' && m.getSemn() == '-') || (this.getSemn() == '+' && m.getSemn() == '+'))
			result.setSemn('+');
		else
			result.setSemn('-');
		result.setPower(this.getPower() + m.getPower());
		this.strMonom += result.getSemn() + result.getCoefficient() + "x^" + result.getPower();
		return result;
	}
	
	public Monomial divideMonomial(Monomial m) {
		Monomial result = new Monomial("");
		if(m.getPower() <= this.getPower()) {
			result.setPower(this.getPower() - m.getPower());
			result.setCoefficient(this.getCoefficient() / m.getCoefficient());
			if(m.getSemn() != this.getSemn())
				result.setSemn('-');
		}
		else
			System.out.println("Divisor is greater than dividend");
		result.strMonom += result.getSemn() + " " +  result.getCoefficient() + "x^" + result.getPower();
		return result;
	}
	
	public void derivateMonomial() {
		int oldPower = this.getPower();
		this.coefficient *= oldPower;
		if(this.coefficient < 0) {
			this.coefficient *= -1;
			this.semn = '-';
		}
		this.power--;
	}
	
	public void integrateMonomial() {
		int oldPower = this.getPower();
		this.setPower(oldPower + 1);
		this.setCoefficient(this.getCoefficient() / this.getPower());
	}
}
