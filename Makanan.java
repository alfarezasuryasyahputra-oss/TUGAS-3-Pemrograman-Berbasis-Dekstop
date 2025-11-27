public class Makanan extends MenuItem {
    private final String jenis;

    public Makanan(String nama, double harga, String jenis) {
        super(nama, harga, "Makanan");
        this.jenis = jenis;
    }

    @Override
    public void tampilMenu() {
        System.out.println("[Makanan] " + getNama() + " (" + jenis + ") - Rp" + getHarga());
    }
}
