package bin2vhdl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author jc
 */
class Bin2vhdlConvert {

    private byte[] data; // file content
    private String vhdl; // vhdl generated from file content

    public Bin2vhdlConvert(String filename) throws IOException {
        Path path;

        this.vhdl = "";
        path = Paths.get(filename);
        data = Files.readAllBytes(path);
        this.buildVhdl();
    }

    public String getVhdl() {
        return this.vhdl;
    }

    private void buildVhdl() {
        String row = "";
        int i;

        for (i = 0; i < this.data.length; i++) {
            row += String.format("%02x", data[i]).toUpperCase();
            if (row.length() == 64) {
                this.vhdl += "INIT_" + String.format("%02x", i / 32).toUpperCase() + " => X\"";
                row = this.invertRow(row);
                this.vhdl += row + "\",\n";
                row = "";
            }
        }
        if (row.length() > 0) {
            this.vhdl += "INIT_" + String.format("%02x", i / 32).toUpperCase() + " => X\"";
            for (i = row.length(); i < 64; i++) {
                row += "0";
            }
            row = this.invertRow(row);
            this.vhdl += row + "\",\n";
            row = "";
        }
        this.vhdl = this.vhdl.substring(0, this.vhdl.length() - 2);
    }

    private String invertRow(String row) {
        String result = "";
        int i = 0;
        while ((row.length() - 2 - i) >= 0) {
            result += row.substring(row.length() - 2 - i, row.length() - i);
            i += 2;
        }
        return result;
    }
}
