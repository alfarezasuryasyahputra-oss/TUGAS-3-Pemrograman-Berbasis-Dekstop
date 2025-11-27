import java.util.ArrayList;

public class Pesanan {
    private final ArrayList<OrderItem> items = new ArrayList<>();
    private Diskon diskon = null;

    public void tambahPesanan(MenuItem item, int qty) {
        if (item instanceof Diskon d) {
            this.diskon = d;
        } else {
            items.add(new OrderItem(item, qty));
        }
    }

    public double hitungTotal() {
        double total = 0;
        for (OrderItem oi : items) {
            total += oi.getSubtotal();
        }
        if (diskon != null) {
            total -= (total * diskon.getPersenDiskon() / 100);
        }
        return total;
    }

    public String getStruk() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== STRUK PEMBELIAN ===\n");
        for (OrderItem oi : items) {
            sb.append(oi.getItem().getNama())
              .append(" x ")
              .append(oi.getQty())
              .append(" = Rp")
              .append(oi.getSubtotal())
              .append("\n");
        }
        if (diskon != null) {
            sb.append("Diskon: ").append(diskon.getPersenDiskon()).append("%\n");
        }
        sb.append("------------------------\n");
        sb.append("TOTAL: Rp").append(hitungTotal()).append("\n");
        return sb.toString();
    }

    public void tampilStruk() {
        System.out.println(getStruk());
    }
}