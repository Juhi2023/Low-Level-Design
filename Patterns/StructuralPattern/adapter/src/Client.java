class Client{
    public void getReport(IReport adapter, String rawdata){
        System.out.println(adapter.getJSONData(rawdata));
    }
}