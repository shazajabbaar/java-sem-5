import java.applet.Applet;
import java.awt.Graphics;

public class AppletLifeCycle extends Applet {

    public void init() {
        System.out.println("init() method executed");
    }

    public void start() {
        System.out.println("start() method executed");
    }

    public void paint(Graphics g) {
        g.drawString("Applet Life Cycle", 50, 50);
        System.out.println("paint() method executed");
    }

    public void stop() {
        System.out.println("stop() method executed");
    }

    public void destroy() {
        System.out.println("destroy() method executed");
    }
}

/* also this applt will work only when jdk is installed
HTML FILE:

<html>
<body>
<applet code="AppletLifeCycle.class" width="400" height="200">
</aplet>
</body>
</html>

Life Cycle:
init() -> start() -> paint() -> stop() -> destroy()
*/
