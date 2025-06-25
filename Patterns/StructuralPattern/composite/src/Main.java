import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Build file system
        Folder root = new Folder("root");
        root.add(new File("file1.txt", 1));
        root.add(new File("file2.txt", 1));

        Folder docs = new Folder("docs");
        docs.add(new File("resume.pdf", 1));
        docs.add(new File("notes.txt", 1));
        root.add(docs);

        Folder images = new Folder("images");
        images.add(new File("photo.jpg", 1));
        root.add(images);

        root.ls(0);
        System.out.println();

        docs.ls(0);
        System.out.println();


        root.openAll(0);

        FileSystemItem cwd = root.cd("docs");
        if (cwd != null) {
            cwd.ls(0);
        } else {
            System.out.println("\nCould not cd into docs\n");
        }

        System.out.println(root.getSize());
    }
}