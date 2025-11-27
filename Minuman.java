public class Minuman extends MenuItem {
    private final String jenis;

    public Minuman(String nama, double harga, String jenis) {
        super(nama, harga, "Minuman");
        this.jenis = jenis;
    }

    @Override
    public void tampilMenu() {
        System.out.println("[Minuman] " + getNama() + " (" + jenis + ") - Rp" + getHarga());
    }
}