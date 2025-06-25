class File implements FileSystemItem {
    private String name;
    private int size;

    public File(String n, int s) {
        name = n;
        size = s;
    }
    
    @Override
    public int getSize() {
        return size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void ls(int indent) {
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + name);
    }

    @Override
    public void openAll(int indent) {
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + name);
    }

    @Override
    public FileSystemItem cd(String name) {
        return null;
    }

    @Override
    public boolean isFolder() {
        return false;
    }
}
