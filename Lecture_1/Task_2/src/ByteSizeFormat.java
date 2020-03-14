/**
 * @author Khachatryan Haroutyun
 *
 * Task 2:
 * Написать метод, форматирующий и выводящий на экран заданный размер в байтах в человекочитаемом виде.
 * Человекочитаемый вид: {целая часть < 1024}.{дробная часть, макс. 1 знак} {единица измерения}
 */


public class ByteSizeFormat {

    public static void main(String[] args) {

        printBytes(0.5);    // 4 b
        printBytes(23);     // 23 B
        printBytes(1024);   // 1 KB

        printBytes(1048576);    // 1 MB
        printBytes(1073741824); // 1 GB

        printBytes(53692044905543.0);   // 48.8 TB
        printBytes(1125899906842624.0); // 1 PB

        printBytes(1152921504606846976.0);      // 1 EB
        printBytes(1180591620717411303424.0);   // 1 ZB

        printBytes(1208e22);    // 10 YB

        printBytes(1208e25);    // More than maximal YB number
    }


    static String[] measures = {"b", "B", "KB", "MB", "GB", "TB", "PB", "EB", "ZB", "YB"};


    static void printBytes(double byteSize) {
        int i = 0;
        double size = byteSize;

        if (byteSize < 1) {
            size = byteSize * 8;
        } else if (byteSize < 1024) {
            i = 1;
        } else {
            i = 1;
            while (size >= 1024) {
                size /= 1024;
                i++;
            }
        }

        if (i < measures.length) {
            System.out.println(byteSize + " bytes = " + String.format("%.1f", size) + " " + measures[i]);
        }
        else {
            System.out.println("Большая разрядность не определена !");
        }
    }
}