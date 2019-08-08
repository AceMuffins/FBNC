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
    public static final String version = "2.1.0";

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
        final Item californiumFuel = ForgeRegistries.ITEMS.getValue(new ResourceLocation("nuclearcraft:fuel_californium"));
        final Item californiumFuelDep = ForgeRegistries.ITEMS.getValue(new ResourceLocation("nuclearcraft:depleted_fuel_californium"));
        final Item air = ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft:air"));
        final Item neutronPile = ForgeRegistries.ITEMS.getValue(new ResourceLocation("avaritia:resource"));
        ItemStack neutrons = new ItemStack(neutronPile, 3, 2);
        ItemStack neutronsNug = new ItemStack(neutronPile, 3, 3);
        ItemStack cf0 = new ItemStack(californium, 1, 0);
        ItemStack cf8 = new ItemStack(californium, 1, 8);

        ItemStack airItem = new ItemStack(air, 1, 0);

        ItemStack cff0 = new ItemStack(californiumFuel, 1, 0);
        ItemStack cff4 = new ItemStack(californiumFuel, 1, 2);
        ItemStack cff8 = new ItemStack(californiumFuel, 1, 4);
        ItemStack cff12 = new ItemStack(californiumFuel, 1, 6);

        ItemStack cffd0 = new ItemStack(californiumFuelDep, 1, 0);
        ItemStack cffd4 = new ItemStack(californiumFuelDep, 1, 2);
        ItemStack cffd8 = new ItemStack(californiumFuelDep, 1, 4);
        ItemStack cffd12 = new ItemStack(californiumFuelDep, 1, 6);
        FBNCRecipeHandler.addFBNCRecipe(cf0, neutrons);
        FBNCRecipeHandler.addFBNCRecipe(cf8, neutrons);
        FBNCRecipeHandler.addFBNCRecipe(cff0, neutronsNug);
        FBNCRecipeHandler.addFBNCRecipe(cff4, neutronsNug);
        FBNCRecipeHandler.addFBNCRecipe(cff8, neutronsNug);
        FBNCRecipeHandler.addFBNCRecipe(cff12, neutronsNug);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }


}
