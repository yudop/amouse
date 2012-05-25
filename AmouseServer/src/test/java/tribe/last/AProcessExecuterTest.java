package tribe.last;

import junit.framework.TestCase;

/**
 * Created by Tobias Ericsson
 */
public class AProcessExecuterTest extends TestCase {
    public void setUp() throws Exception {

    }

    public void tearDown() throws Exception {

    }

    public void testExecute() {


        AProcessExecuter a = new AProcessExecuter();
        String result; // = a.execute("groovy.exe", "-version");
        //System.out.println(result);
        //result = a.execute("C:\\home\\Program\\VLC\\vlc.exe");
        //System.out.println(result);
        result = a.execute("clever.exe", "play");
        System.out.println(result);
        result = a.execute("wizmo.exe");
        System.out.println(result);
        //result = a.execute("clever.exe", "pause");
        //System.out.println(result);

    }
}
