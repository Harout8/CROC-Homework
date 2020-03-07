/**
 * @author Khachatryan Haroutyun
 *
 * Task 2:
 * Написать метод, форматирующий и выводящий на экран заданный размер в байтах в человекочитаемом виде.
 * Человекочитаемый вид: {целая часть < 1024}.{дробная часть, макс. 1 знак} {единица измерения}
 */


public class ByteSizeFormat {

    public static void main(String[] args) {

        printBytes(0.5);    // b
        printBytes(23);     // B
        printBytes(1024);   // KB

        printBytes(1048576);    // MB
        printBytes(1073741824); // GB

        printBytes(53692044905543.0);   // TB
        printBytes(1125899906842624.0); // PB

        printBytes(1152921504606846976.0);      // EB
        printBytes(1180591620717411303424.0);   // ZB

        printBytes(1208e22);    // YB
    }

    static void printBytes(double byteSize) {
        if (byteSize < 1) {
            System.out.println("Bit:        " + String.format("%.1f", byteSize * 8) + " b");
        } else if (byteSize < 1024) {
            System.out.println("Byte:       " + String.format("%.1f", byteSize) + " B");
        } else if (byteSize < Math.pow(1024, 2)) {
            System.out.println("Kilobyte:   " + String.format("%.1f", byteSize / 1024) + " KB");
        } else if (byteSize < Math.pow(1024, 3)) {
            System.out.println("Megabyte:   " + String.format("%.1f", byteSize / Math.pow(1024, 2)) + " MB");
        } else if (byteSize < Math.pow(1024, 4)) {
            System.out.println("Gigabyte:   " + String.format("%.1f", byteSize / Math.pow(1024, 3)) + " GB");
        } else if (byteSize < Math.pow(1024, 5)) {
            System.out.println("Terrabyte:  " + String.format("%.1f", byteSize / Math.pow(1024, 4)) + " TB");
        } else if (byteSize < Math.pow(1024, 6)) {
            System.out.println("Petabyte:   " + String.format("%.1f", byteSize / Math.pow(1024, 5)) + " PB");
        } else if (byteSize < Math.pow(1024, 7)) {
            System.out.println("Exabyte:    " + String.format("%.1f", byteSize / Math.pow(1024, 6)) + " EB");
        } else if (byteSize < Math.pow(1024, 8)) {
            System.out.println("Zettabyte:  " + String.format("%.1f", byteSize / Math.pow(1024, 7)) + " ZB");
        } else if (byteSize < Math.pow(1024, 9)) {
            System.out.println("Yottabyte:  " + String.format("%.1f", byteSize / Math.pow(1024, 8)) + " YB");
        }
    }
}