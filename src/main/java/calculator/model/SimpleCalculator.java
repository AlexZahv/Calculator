package calculator.model;


import calculator.controller.CalcContoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleCalculator implements Calculable{
    @Autowired
    private CalcContoller contoller;

    public CalcContoller getContoller() {
        return contoller;
    }

    public void setContoller(CalcContoller contoller) {
        this.contoller = contoller;
    }

    /**
     *
     * @param x1 first parameter of calculation
     * @param x2  second parameter of calculation
     * @return result of sum operation
     */
    public double calcSummary(String x1, String x2) {
        return Double.parseDouble(x1)+Double.parseDouble(x2);
    }

    /**
     *
     * @param x1 first parameter of calculation
     * @param x2  second parameter of calculation
     * @return result of substraction operation
     */
    public double calcSubstraction(String x1, String x2) {
        return Double.parseDouble(x1)-Double.parseDouble(x2);
    }
    /**
     *
     * @param x1 first parameter of calculation
     * @param x2  second parameter of calculation
     * @return result of division operation
     */
    public double calcDivision(String x1, String x2) {
        return Double.parseDouble(x1)/Double.parseDouble(x2);
    }
    /**
     *
     * @param x1 first parameter of calculation
     * @param x2  second parameter of calculation
     * @return result of multiplication operation
     */
    public double calcMultiplication(String x1, String x2) {
        return Double.parseDouble(x1)*Double.parseDouble(x2);
    }


}
