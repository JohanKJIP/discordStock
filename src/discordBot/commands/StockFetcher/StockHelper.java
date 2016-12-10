package discordBot.commands.StockFetcher;

/**
 * Created by JohanvonHacht on 07/12/16.
 */
import java.util.regex.Pattern;

public class StockHelper {

    public StockHelper() {

    }

    public double handleDouble(String x) {
        Double y;
        if (Pattern.matches("N/A", x)) {
            y = 0.00;
        } else {
            y = Double.parseDouble(x);
        }
        return y;
    }

    public int handleInt(String x) {
        int y;
        if (Pattern.matches("N/A", x)) {
            y = 0;
        } else {
            y = Integer.parseInt(x);
        }
        return y;
    }

}
