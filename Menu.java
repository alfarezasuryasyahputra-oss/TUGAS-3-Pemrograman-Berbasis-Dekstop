import java.io.*;
import java.util.ArrayList;

public class Menu {
    private final ArrayList<MenuItem> daftarMenu = new ArrayList<>();

    public void tambahItem(MenuItem item) {
        daftarMenu.add(item);
    }

    public void tampilkanMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu masih kosong!");
            return;
        }
        System.out.println("=== DAFTAR MENU ===");
        for (MenuItem item : daftarMenu) {
            item.tampilMenu();
        }
    }

    public MenuItem cariItem(String nama) throws Exception {
        for (MenuItem item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        throw new Exception("Item tidak ditemukan!");
    }

    public void simpanKeFile(String file) throws Exception {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (MenuItem item : daftarMenu) {
                switch (item) {
                    case Makanan m -> bw.write("Makanan;" + m.getNama() + ";" + m.getHarga() + ";"
                            + "Makanan");
                    case Minuman mn -> bw.write("Minuman;" + mn.getNama() + ";" + mn.getHarga() + ";"
                            + "Minuman");
                    case Diskon d -> bw.write("Diskon;" + d.getNama() + ";" + d.getPersenDiskon());
                    default -> {
                    }
                }
                bw.newLine();
            }
        }
    }

    public void loadDariFile(String file) throws Exception {
        daftarMenu.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");
                String jenis = p[0];
                
                switch (jenis) {
                    case "Makanan" ->
                        tambahItem(new Makanan(p[1], Double.parseDouble(p[2]), p[3]));
                    case "Minuman" ->
                        tambahItem(new Minuman(p[1], Double.parseDouble(p[2]), p[3]));
                    case "Diskon" ->
                        tambahItem(new Diskon(p[1], Double.parseDouble(p[2])));
                }
            }
        }
    }
}