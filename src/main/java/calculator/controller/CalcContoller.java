package calculator.controller;


import calculator.model.Calculable;
import calculator.view.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class CalcContoller {
    @Autowired
    private View view;
    @Autowired
    private Calculable calculator;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Calculable getCalculator() {
        return calculator;
    }

    public void setCalculator(Calculable calculator) {
        this.calculator = calculator;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
        View view = (View) context.getBean("view");
    }

    /**
     *
     * @param x1 first argument in calculation
     * @param x2 second argument in calculation
     * @param operator  operator which is used in calculation
     * @return the result of calculation
     */
    public String makeCalculations(String x1, String x2, String operator) {
        double result = 0;
        switch (operator) {
            case "+":
                result = calculator.calcSummary(x1, x2);
                break;
            case "-":
                result = calculator.calcSubstraction(x1, x2);
                break;
            case "*":
                result = calculator.calcMultiplication(x1, x2);
                break;
            case "/":
                result = calculator.calcDivision(x1, x2);
                break;
        }
        return result + "";
    }
}
