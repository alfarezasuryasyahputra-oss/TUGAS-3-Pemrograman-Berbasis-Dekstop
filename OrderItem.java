public class OrderItem {
    private final MenuItem item;
    private final int qty;

    public OrderItem(MenuItem item, int qty) {
        this.item = item;
        this.qty = qty;
    }

    public MenuItem getItem() { return item; }
    public int getQty() { return qty; }

    public double getSubtotal() {
        return item.getHarga() * qty;
    }
}