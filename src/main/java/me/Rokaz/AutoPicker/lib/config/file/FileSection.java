package me.Rokaz.AutoPicker.lib.config.file;

import java.io.File;

public class FileSection {
    private File parent;
    private String name;
    public FileSection(FileSection s, String name) {
        this.parent = s.getFile();
        this.name = name;
        create();
    }
    public FileSection(String name) {
        this.parent = new File("plugins");
        this.name = name;
        create();
    }
    private void create() {
        if (!getFile().exists()) {
            getFile().mkdir();
        }
    }

    public File getFile() { return new File(this.parent.getPath() + "//" + this.name); }


    public File getParent() { return this.parent; }


    public String getName() { return this.name; }
}
