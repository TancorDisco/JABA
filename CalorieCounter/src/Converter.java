import java.text.DecimalFormat;
public class Converter {

    static String convertToKm(int step) {
        return (new DecimalFormat("#.##").format(step * 0.0075));//Math.floor(step * 0.0075) * 100 / 100;
    }

    static double convertToKcal(int step) {
        return Math.floor(step * 0.05) * 100 / 100;
    }
}