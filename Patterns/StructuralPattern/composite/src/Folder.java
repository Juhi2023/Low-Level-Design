import java.util.*;

class Folder implements FileSystemItem {
    private String name;
    private List<FileSystemItem> children;

    public Folder(String n) {
        name = n;
        children = new ArrayList<>();
    }

    public void add(FileSystemItem item) {
        children.add(item);
    }

    @Override
    public void ls(int indent) {
        String indentSpaces = " ".repeat(indent);
        for (FileSystemItem child : children) {
            if (child.isFolder()) {
                System.out.println(indentSpaces + "+ " + child.getName());
            } else {
                System.out.println(indentSpaces + child.getName());
            }
        }
    }

    @Override
    public void openAll(int indent) {
        String indentSpaces = " ".repeat(indent);
        System.out.println(indentSpaces + "+ " + name);
        for (FileSystemItem child : children) {
            child.openAll(indent + 4);
        }
    }

    @Override
    public int getSize() {
        int total = 0;
        for (FileSystemItem child : children) {
            total += child.getSize();
        }
        return total;
    }

    @Override
    public FileSystemItem cd(String target) {
        for (FileSystemItem child : children) {
            if (child.isFolder() && child.getName().equals(target)) {
                return child;
            }
        }
        // not found or not a folder
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isFolder() {
        return true;
    }
}
