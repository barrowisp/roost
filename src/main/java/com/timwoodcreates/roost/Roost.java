package com.timwoodcreates.roost;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.timwoodcreates.roost.proxy.ProxyCommon;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Roost.MODID, version = Roost.VERSION)
public class Roost {
	public static final String MODID = "roost";
	public static final String NAME = "Roost";
	public static final String VERSION = "@VERSION@";
	public static final CreativeTabs TAB = new RoostTab();
	public static final Logger LOGGER = LogManager.getLogger("Roost");

	@Instance(MODID)
	public static Roost INSTANCE;

	@SidedProxy(clientSide = "com.timwoodcreates.roost.proxy.ProxyClient", serverSide = "com.timwoodcreates.roost.proxy.ProxyCommon")
	public static ProxyCommon PROXY;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		FMLInterModComms.sendMessage("waila", "register", "com.timwoodcreates.roost.integration.waila.RoostWailaDataProvider.register");
		RoostConfig.sync();

		PROXY.preInit(e);
	}


	@EventHandler
	public static void loadComplete(FMLLoadCompleteEvent event) {
		Minecraft.getMinecraft().refreshResources();
	}
}
