package pub.avalonframework.wechat.official.account.core.api.config;

/**
 * @author baichao
 */
public class CustomMenuConfiguration {

    private String menuCreateUrl;

    public String getMenuCreateUrl() {
        return menuCreateUrl;
    }

    public void setMenuCreateUrl(String menuCreateUrl) {
        this.menuCreateUrl = menuCreateUrl;
    }

    public String getAutoAssembleMenuCreateUrlWithAccessToken(String accessToken) {
        StringBuilder sb = new StringBuilder(menuCreateUrl);
        if (!menuCreateUrl.contains("?")) {
            sb.append("?");
        }
        if (!menuCreateUrl.contains("access_token=") && accessToken != null && !accessToken.isEmpty()) {
            sb.append("&access_token=").append(accessToken);
        }
        return sb.toString();
    }
}