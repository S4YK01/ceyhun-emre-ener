import java.util.*;

// Film sınıfı
class Film {
    private String ad;
    private int sure;
    private String tur;

    public Film(String ad, int sure, String tur) {
        this.ad = ad;
        this.sure = sure;
        this.tur = tur;
    }

    public String getAd() {
        return ad;
    }

    @Override
    public String toString() {
        return ad + " (" + sure + " dk, " + tur + ")";
    }
}

// Müşteri sınıfı
class Musteri {
    private String ad;
    private String email;

    public Musteri(String ad, String email) {
        this.ad = ad;
        this.email = email;
    }

    public String getAd() {
        return ad;
    }

    @Override
    public String toString() {
        return ad + " - " + email;
    }
}

// Bilet sınıfı
class Bilet {
    private Musteri musteri;
    private Film film;

    public Bilet(Musteri musteri, Film film) {
        this.musteri = musteri;
        this.film = film;
    }

    @Override
    public String toString() {
        return musteri.getAd() + " için film: " + film.getAd();
    }
}

// Ana uygulama sınıfı (main burada)
public class SinemaApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Film> filmler = new ArrayList<>();
    private static final List<Musteri> musteriler = new ArrayList<>();
    private static final List<Bilet> biletler = new ArrayList<>();

    public static void main(String[] args) {
        int secim;
        do {
            menu();
            secim = secimAl();
            switch (secim) {
                case 1 -> filmEkle();
                case 2 -> musteriEkle();
                case 3 -> biletSatis();
                case 4 -> filmListele();
                case 5 -> musteriListele();
                case 6 -> biletListele();
                case 0 -> System.out.println("Çıkış yapılıyor...");
                default -> System.out.println("Geçersiz seçim.");
            }
        } while (secim != 0);
    }

    private static void menu() {
        System.out.println("\n=== SİNEMA BİLET SİSTEMİ ===");
        System.out.println("1. Film Ekle");
        System.out.println("2. Müşteri Ekle");
        System.out.println("3. Bilet Sat");
        System.out.println("4. Filmleri Listele");
        System.out.println("5. Müşterileri Listele");
        System.out.println("6. Biletleri Listele");
        System.out.println("0. Çıkış");
        System.out.print("Seçiminiz: ");
    }

    private static int secimAl() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    private static void filmEkle() {
        if (filmler.size() >= 10) {
            System.out.println("Maksimum 10 film eklenebilir.");
            return;
        }
        System.out.print("Film Adı: ");
        String ad = scanner.nextLine();
        System.out.print("Süre (dk): ");
        int sure = Integer.parseInt(scanner.nextLine());
        System.out.print("Tür: ");
        String tur = scanner.nextLine();

        filmler.add(new Film(ad, sure, tur));
        System.out.println("Film eklendi.");
    }

    private static void musteriEkle() {
        if (musteriler.size() >= 20) {
            System.out.println("Maksimum 20 müşteri eklenebilir.");
            return;
        }
        System.out.print("Müşteri Adı: ");
        String ad = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        musteriler.add(new Musteri(ad, email));
        System.out.println("Müşteri eklendi.");
    }

    private static void biletSatis() {
        if (filmler.isEmpty() || musteriler.isEmpty()) {
            System.out.println("Önce film ve müşteri eklemelisiniz.");
            return;
        }

        musteriListele();
        System.out.print("Müşteri No: ");
        int mIndex = Integer.parseInt(scanner.nextLine());

        filmListele();
        System.out.print("Film No: ");
        int fIndex = Integer.parseInt(scanner.nextLine());

        if (mIndex < 0 || mIndex >= musteriler.size() || fIndex < 0 || fIndex >= filmler.size()) {
            System.out.println("Geçersiz seçim.");
            return;
        }

        Bilet bilet = new Bilet(musteriler.get(mIndex), filmler.get(fIndex));
        biletler.add(bilet);
        System.out.println("Bilet başarıyla satıldı.");
    }

    private static void filmListele() {
        System.out.println("\n--- Filmler ---");
        if (filmler.isEmpty()) {
            System.out.println("Henüz film eklenmedi.");
            return;
        }
        for (int i = 0; i < filmler.size(); i++) {
            System.out.println(i + " - " + filmler.get(i));
        }
    }

    private static void musteriListele() {
        System.out.println("\n--- Müşteriler ---");
        if (musteriler.isEmpty()) {
            System.out.println("Henüz müşteri eklenmedi.");
            return;
        }
        for (int i = 0; i < musteriler.size(); i++) {
            System.out.println(i + " - " + musteriler.get(i));
        }
    }

    private static void biletListele() {
        System.out.println("\n--- Satılan Biletler ---");
        if (biletler.isEmpty()) {
            System.out.println("Henüz bilet satışı yapılmadı.");
            return;
        }
        for (Bilet b : biletler) {
            System.out.println(b);
        }
    }
}
