package me.Rokaz.AutoPicker.lib.legacy;

public class SinceVersion {
    private Version version;

    public SinceVersion(Version v, String output) {
        this.version = v;
        this.output = output;
    }
    private String output;
    public Version getVersion() { return this.version; }


    public String getOutput() { return this.output; }
}
