package transport;

public abstract class PublicTransport extends Transport {
    protected int route;    // Маршрут

    public int getRoute() {
        return route;
    }

    public void setRoute(int route) {
        this.route = route;
    }
}
