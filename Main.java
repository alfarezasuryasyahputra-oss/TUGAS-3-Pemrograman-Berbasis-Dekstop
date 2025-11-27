import java.util.Scanner;
import java.io.PrintWriter;

public class Main {
    static Scanner input = new Scanner(System.in);
    static Menu menu = new Menu();
    static Pesanan pesananTerakhir = null;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== MENU UTAMA ===");
            System.out.println("1. Tambah item menu");
            System.out.println("2. Tampilkan menu");
            System.out.println("3. Buat pesanan");
            System.out.println("4. Simpan menu ke file");
            System.out.println("5. Load menu dari file");
            System.out.println("6. Tampilkan struk terakhir");
            System.out.println("7. Keluar");
            System.out.print("Pilih: ");

            int p = input.nextInt();
            input.nextLine();

            switch (p) {
                case 1 -> tambahItemMenu();
                case 2 -> menu.tampilkanMenu();
                case 3 -> buatPesanan();
                case 4 -> simpanMenu();
                case 5 -> loadMenu();
                case 6 -> tampilStrukTerakhir();
                case 7 -> System.exit(0);
                default -> System.out.println("Pilihan salah!");
            }
        }
    }

    
    static void tambahItemMenu() {
        System.out.println("1. Makanan\n2. Minuman\n3. Diskon");
        System.out.print("Pilih jenis: ");
        int pil = input.nextInt();
        input.nextLine();

        System.out.print("Nama: ");
        String nama = input.nextLine();

        if (pil == 3) {
            System.out.print("Persen diskon: ");
            double d = input.nextDouble();
            menu.tambahItem(new Diskon(nama, d));
        } else {
            System.out.print("Harga: ");
            double harga = input.nextDouble();
            input.nextLine();

            if (pil == 1)
                menu.tambahItem(new Makanan(nama, harga, "Umum"));
            else
                menu.tambahItem(new Minuman(nama, harga, "Umum"));
        }

        System.out.println("Item berhasil ditambahkan!");
    }

    
    static void buatPesanan() {
        Pesanan pesanan = new Pesanan();

        System.out.println("=== MENU ===");
        menu.tampilkanMenu();

        while (true) {
            System.out.print("Masukkan nama item ('selesai'): ");
            String nama = input.nextLine();
            if (nama.equalsIgnoreCase("selesai")) break;

            try {
                MenuItem item = menu.cariItem(nama);

                if (item instanceof Diskon) {
                    pesanan.tambahPesanan(item, 1);
                } else {
                    System.out.print("Jumlah: ");
                    int qty = input.nextInt();
                    input.nextLine();
                    pesanan.tambahPesanan(item, qty);
                }

            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }

        pesanan.tampilStruk();
        simpanStruk(pesanan);

        pesananTerakhir = pesanan;
    }

    static void simpanMenu() {
        try {
            menu.simpanKeFile("menu.txt");
            System.out.println("Menu berhasil disimpan.");
        } catch (Exception e) {
            System.out.println("Gagal menyimpan!");
        }
    }

    static void loadMenu() {
        try {
            menu.loadDariFile("menu.txt");
            System.out.println("Menu berhasil dimuat.");
        } catch (Exception e) {
            System.out.println("Gagal memuat!");
        }
    }

    static void simpanStruk(Pesanan p) {
        try (PrintWriter pw = new PrintWriter("struk.txt")) {
            pw.println(p.getStruk());
        } catch (Exception e) {
            System.out.println("Gagal menyimpan struk.");
        }
    }

    static void tampilStrukTerakhir() {
        if (pesananTerakhir == null) {
            System.out.println("Belum ada pesanan!");
            return;
        }
        pesananTerakhir.tampilStruk();
    }
}