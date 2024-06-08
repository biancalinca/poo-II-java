package models;

public class Ticket {
    private double price;
    private Event event;

    public Ticket(Event event) {
        this.event = event;
        this.price = this.event.getTicketPrice();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "price=" + price +
                ", event=" + event +
                '}' + "\n";
    }
}
