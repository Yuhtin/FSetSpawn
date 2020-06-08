package com.yuhtin.fsetspawn.utils.builders;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ItemBuilder {
    private ItemStack item;

    public ItemBuilder(ItemStack item) {
        this.item = item;
    }

    public ItemBuilder(Material type, int quantity, int data) {
        this(new ItemStack(type, quantity, (short) data));
    }

    public ItemBuilder(Material type) {
        this(new ItemStack(type));
    }

    public ItemBuilder(String name) {
        item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        SkullMeta itemMeta = (SkullMeta) item.getItemMeta();
        itemMeta.setOwner(name);
        item.setItemMeta(itemMeta);
    }

    public ItemBuilder change(Consumer<ItemMeta> consumer) {
        ItemMeta itemMeta = item.getItemMeta();
        consumer.accept(itemMeta);
        item.setItemMeta(itemMeta);
        return this;
    }

    public ItemBuilder setTag(String path, String value) {
        net.minecraft.server.v1_8_R3.ItemStack itemNBT = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = (itemNBT.hasTag() ? itemNBT.getTag() : new NBTTagCompound());
        tag.set(path, new NBTTagString(value));
        item = CraftItemStack.asBukkitCopy(itemNBT);
        return this;
    }

    public ItemBuilder glow() {
        return change(it -> {
            it.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
            it.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        });
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level, boolean ignoreRestrictions) {
        return change(it -> it.addEnchant(enchantment, level, ignoreRestrictions));
    }

    public ItemBuilder name(String name) {
        return change(it -> it.setDisplayName(name.replace("&", "§")));
    }

    public ItemBuilder lore(List<String> lore) {
        return change(it -> it.setLore(lore));
    }

    public ItemBuilder lore(String... lore) {
        return change(it -> it.setLore(Arrays.asList(lore)));
    }

    public ItemStack result() {
        return item;
    }
}