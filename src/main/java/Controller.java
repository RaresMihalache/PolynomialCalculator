import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener{
	private View theView = new View();
	private Model theModel = new Model();
	
	Controller(View theView, Model theModel){
		this.theView = theView;
		this.theModel = theModel;
		this.theView.getResultDispatcher(this);
	}
	public void actionPerformed(ActionEvent pressed) {
		if(pressed.getSource() == this.theView.getResultBtn2()) {
			this.theModel.setOperationList(this.theView.getOperationList());
			this.theModel.getOperationList();
			this.theModel.setPolynomial1(this.theView.getFirstPol2());
			this.theModel.setPolynomial2(this.theView.getSecondPol2());
			if(this.theModel.getOperationList().get(0) == true)
				this.theView.setResult2(this.theModel.addPolynomials(this.theModel.getPolynomial1(), this.theModel.getPolynomial2()));
			else if(this.theModel.getOperationList().get(1) == true)
				this.theView.setResult2(this.theModel.subtractPolynomials(this.theModel.getPolynomial1(), this.theModel.getPolynomial2()));
			else if(this.theModel.getOperationList().get(2) == true)
				this.theView.setResult2(this.theModel.multiplyPolynomials(this.theModel.getPolynomial1(), this.theModel.getPolynomial2()));
			else if(this.theModel.getOperationList().get(3) == true)
				this.theView.setResult2(this.theModel.dividePolynomials(this.theModel.getPolynomial1(), this.theModel.getPolynomial2()));
		}
		else if(pressed.getSource() == this.theView.getResultBtn3()) {
			this.theModel.setOperationList(this.theView.getOperationList());
			this.theModel.getOperationList();
			this.theModel.setPolynomial1(this.theView.getFirstPol3());
			if(this.theModel.getOperationList().get(4) == true)
				this.theView.setResult3(this.theModel.derviatePolynomial(this.theModel.getPolynomial1()));
			else if(this.theModel.getOperationList().get(5) == true)
				this.theView.setResult3(this.theModel.integratePolynomial(this.theModel.getPolynomial1()));
		}
	}
}
