import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Clock brlClock = new BRLClock();

        System.out.print("Digite a hora (0-23): ");
        int hour = scanner.nextInt();
        brlClock.setHour(hour);

        System.out.print("Digite o minuto (0-59): ");
        int minute = scanner.nextInt();
        brlClock.setMinute(minute);

        System.out.print("Digite o segundo (0-59): ");
        int second = scanner.nextInt();
        brlClock.setSecond(second);

        scanner.close();

        System.out.println("\n------------------------------------");

        System.out.println("Horário no formato BRL (24h): " + brlClock.getTime());

        System.out.println("Horário no formato US (AM/PM): " + new USClock().convert(brlClock).getTime());
        System.out.println("------------------------------------");
    }

}