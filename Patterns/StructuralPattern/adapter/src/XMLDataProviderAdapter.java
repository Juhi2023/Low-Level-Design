class XMLDataProviderAdapter implements IReport{
    XMLDataProviderAdaptee x;

    public XMLDataProviderAdapter(){
        x = new XMLDataProviderAdaptee();
    }

    public String getJSONData(String data){
        return "Converted to JSON: " + x.getXMLData(data);
    }
}