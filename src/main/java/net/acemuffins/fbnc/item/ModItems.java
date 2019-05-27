package net.acemuffins.fbnc.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
    public static ItemBase shieldBasic = new ItemBase("shield_basic").setCreativeTab(CreativeTabs.MATERIALS);
    public static ItemBase shieldNeutron = new ItemBase("shield_neutron").setCreativeTab(CreativeTabs.MATERIALS);
    public static ItemBase shieldCollector = new ItemBase("shield_collector").setCreativeTab(CreativeTabs.MATERIALS);
    public static ItemBase lapisInfused = new ItemBase("lapis_infused").setCreativeTab(CreativeTabs.MATERIALS);

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                shieldBasic,
                shieldNeutron,
                shieldCollector,
                lapisInfused
        );
    }

    public static void registerModels() {
        shieldBasic.registerItemModel();
        shieldNeutron.registerItemModel();
        shieldCollector.registerItemModel();
        lapisInfused.registerItemModel();
    }

}