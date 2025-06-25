interface FileSystemItem {
    int getSize();                  
    String getName();
    void ls(int indent);            
    void openAll(int indent);      
    FileSystemItem cd(String name); 
    boolean isFolder();
}