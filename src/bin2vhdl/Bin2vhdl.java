package bin2vhdl;

/**
 *
 * @author jc
 */
public class Bin2vhdl {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new Exception("Please specify a file.");
        }
        Bin2vhdlConvert converter = new Bin2vhdlConvert(args[0]);
        System.out.println(converter.getVhdl());
    }
}
