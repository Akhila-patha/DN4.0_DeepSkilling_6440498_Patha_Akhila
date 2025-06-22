public class Main {
    public static void main(String[] args) {
        StockMarket market = new StockMarket();

        Observer mobileApp = new MobileApp("StockTracker");
        Observer webApp = new WebApp("StockWatcher");

        market.registerObserver(mobileApp);
        market.registerObserver(webApp);

        market.setStockPrice("AAPL", 186.10);
        market.setStockPrice("GOOGL", 2745.30);

        // Unregister one observer
        market.removeObserver(webApp);

        market.setStockPrice("MSFT", 351.90);
    }
}