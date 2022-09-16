package Utils;

public class Utils {
    public static double toDouble(String str) {
        double ret = 0;
        String[] temp = str.split("/");
        if (temp.length == 1) {
            try {
                ret = Double.parseDouble(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                ret = Double.parseDouble(temp[0]) / Double.parseDouble(temp[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }
}
