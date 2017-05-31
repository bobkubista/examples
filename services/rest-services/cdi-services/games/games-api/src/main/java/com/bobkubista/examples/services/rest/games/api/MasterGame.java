package com.bobkubista.examples.services.rest.games.api;

import java.util.Locale;
import java.util.Map;

public class MasterGame {

	private Long id;
	private String title;
	private Map<Locale, String> localTitle;
	private Map<Locale, String> description;
	private WindowsGame desktopGame;
	private WebGame webGame;
	private XboxGame xboxGame;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Map<Locale, String> getDescription() {
		return description;
	}

	public void setDescription(Map<Locale, String> description) {
		this.description = description;
	}

	public WindowsGame getDesktopGame() {
		return desktopGame;
	}

	public void setDesktopGame(WindowsGame desktopGame) {
		this.desktopGame = desktopGame;
	}

	public WebGame getWebGame() {
		return webGame;
	}

	public void setWebGame(WebGame webGame) {
		this.webGame = webGame;
	}

	public XboxGame getXboxGame() {
		return xboxGame;
	}

	public void setXboxGame(XboxGame xboxGame) {
		this.xboxGame = xboxGame;
	}

	public Map<Locale, String> getLocalTitle() {
		return localTitle;
	}

	public void setLocalTitle(Map<Locale, String> localTitle) {
		this.localTitle = localTitle;
	}

}
