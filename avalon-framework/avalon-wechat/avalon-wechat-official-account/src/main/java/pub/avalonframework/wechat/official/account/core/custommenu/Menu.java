package pub.avalonframework.wechat.official.account.core.custommenu;

/**
 * @author baichao
 */
public class Menu {

    private Button[] button;

    public Menu(Button[] button) {
        this.button = button;
    }

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}