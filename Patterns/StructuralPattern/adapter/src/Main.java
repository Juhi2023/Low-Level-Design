class Main{
    public static void main(String args[]){
        Client c = new Client();
        XMLDataProviderAdapter adapter = new XMLDataProviderAdapter();
        c.getReport(adapter, "Hi, Juhi this side");
    }
}