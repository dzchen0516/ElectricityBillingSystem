package electricity.billing.system;

import javax.swing.*;

public class IntroductionScreen extends JFrame {

    IntroductionScreen() {
        RawIconConverter backgroundImg = new RawIconConverter(0,0,600, 400, "icon/splash/splash.jpg");
        add(backgroundImg.convertIcons());

        setSize(600, 400);
        setLocation(500, 200);
        setVisible(true);

        //hide the introduction screen after 5000 ms
        try {
            Thread.sleep(3000);
            setVisible(false);
            new LoginScreen();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new IntroductionScreen();
    }

}
