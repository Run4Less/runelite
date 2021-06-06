package net.runelite.client.plugins.bonerunner;

import net.runelite.client.ui.overlay.infobox.Counter;

import java.awt.*;
import java.awt.image.BufferedImage;

class HouseMembersIndicator extends Counter
{
	private final BoneRunnerPlugin plugin;

	HouseMembersIndicator(BufferedImage image, BoneRunnerPlugin plugin)
	{
		super(image, plugin, 0);
		this.plugin = plugin;
	}

	@Override
	public int getCount()
	{
		return plugin.getHousePlayerCount();
	}

	@Override
	public String getTooltip()
	{
		return plugin.getHousePlayerCount() + " players in house";
	}

	@Override
	public Color getTextColor()
	{
		return Color.WHITE;
	}
}