
public class DataService {
    private ExternalAPI externalAPI;

    public DataService(ExternalAPI externalAPI) {
        this.externalAPI = externalAPI;
    }

    public String getProcessedData() {
        String data = externalAPI.fetchData();
        return "Processed: " + data;
    }
}
