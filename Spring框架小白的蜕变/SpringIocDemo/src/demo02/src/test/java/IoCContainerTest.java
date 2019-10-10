import car.Audi;
import car.Buick;
import humen.Hunem;
import humen.LiSi;
import humen.ZhangSan;
import org.junit.Before;
import org.junit.Test;

public class IoCContainerTest {
    private IoCContainer ioCContainer = new IoCContainer();

    @Before
    public void before() {
        ioCContainer.setBean(Audi.class, "audi");
        ioCContainer.setBean(Buick.class, "buick");
        ioCContainer.setBean(ZhangSan.class, "zhangSan","audi");
        ioCContainer.setBean(LiSi.class, "liSi","buick");
    }

    @Test
    public void test() {
        Hunem zhangsan = (Hunem) ioCContainer.getBean("zhangSan");
        zhangsan.goHome();
        LiSi liSi = (LiSi) ioCContainer.getBean("liSi");
        liSi.goHome();
    }
}
