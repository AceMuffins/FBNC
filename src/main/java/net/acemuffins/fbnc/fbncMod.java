package net.acemuffins.fbnc;

import it.unimi.dsi.fastutil.bytes.AbstractByte2ObjectSortedMap;
import net.acemuffins.fbnc.block.FBNCGui;
import net.acemuffins.fbnc.block.FBNCRecipeHandler;
import net.acemuffins.fbnc.block.GuiHandler;
import net.acemuffins.fbnc.block.ModBlocks;
import net.acemuffins.fbnc.item.ModItems;
import net.acemuffins.fbnc.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;
import scala.Array;

import java.util.*;

@Mod(modid = fbncMod.modId, name = fbncMod.name, version = fbncMod.version, acceptedMinecraftVersions = "[1.12.2]", dependencies = "required-after:nuclearcraft;required-after:avaritia;")

public class fbncMod {
    @SidedProxy(serverSide = "net.acemuffins.fbnc.proxy.CommonProxy", clientSide = "net.acemuffins.fbnc.proxy.ClientProxy")
    public static CommonProxy proxy;
    public static final String modId = "fbnc";
    public static final String name = "Fission Based Neutron Collector";
    public static final String version = "1.0.0";

    @Mod.Instance(modId)
    public static fbncMod instance;

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }
        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is loading!");
        NetworkRegistry.INSTANCE.registerGuiHandler(fbncMod.instance, new GuiHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        final Item californium = ForgeRegistries.ITEMS.getValue(new ResourceLocation("nuclearcraft:californium"));
        final Item neutronPile = ForgeRegistries.ITEMS.getValue(new ResourceLocation("avaritia:resource"));
        ItemStack neutrons = new ItemStack(neutronPile, 3, 2);
        ItemStack cf0 = new ItemStack(californium, 1, 0);
        ItemStack cf4 = new ItemStack(californium, 1, 4);
        ItemStack cf8 = new ItemStack(californium, 1, 8);
        ItemStack cf12 = new ItemStack(californium, 1, 12);
        FBNCRecipeHandler.addFBNCRecipe(cf0, neutrons);
        FBNCRecipeHandler.addFBNCRecipe(cf4, neutrons);
        FBNCRecipeHandler.addFBNCRecipe(cf8, neutrons);
        FBNCRecipeHandler.addFBNCRecipe(cf12, neutrons);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


}
