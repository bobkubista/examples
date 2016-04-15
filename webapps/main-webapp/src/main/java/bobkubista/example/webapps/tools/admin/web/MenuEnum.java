package bobkubista.example.webapps.tools.admin.web;

public enum MenuEnum {
	APP1_MENU(ToolService.getUrl("app1") + "/loadMenu");

	private final String menuUrl;

	MenuEnum(final String url) {
		this.menuUrl = url;
	}

	public boolean equalsName(final String url) {
		return url == null ? false : this.menuUrl.equals(url);
	}

	@Override
	public String toString() {
		return this.menuUrl;
	}

}
