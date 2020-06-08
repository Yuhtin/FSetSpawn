package com.yuhtin.fsetspawn.enums;

import lombok.AllArgsConstructor;
import org.bukkit.entity.EntityType;

import java.util.Arrays;

@AllArgsConstructor
public enum SpawnerType {

    // Official heads
    CREEPER("Creeper", "MHF_CREEPER", EntityType.CREEPER),
    SKELETON("Esqueleto", "MHF_SKELETON", EntityType.SKELETON),
    SPIDER("Araha", "MHF_SPIDER", EntityType.SPIDER),
    ZOMBIE("Zumbi", "MHF_ZOMBIE", EntityType.ZOMBIE),
    SLIME("Slime", "MHF_SLIME", EntityType.SLIME),
    GHAST("Ghast", "MHF_GHAST", EntityType.GHAST),
    PIG_ZOMBIE("Porco Zumbi", "MHF_PIGZOMBIE", EntityType.PIG_ZOMBIE),
    ENDERMAN("Enderman", "MHF_ENDERMAN", EntityType.ENDERMAN),
    CAVE_SPIDER("Aranha da Caverna", "MHF_CAVESPIDER", EntityType.CAVE_SPIDER),
    BLAZE("Blaze", "MHF_BLAZE", EntityType.BLAZE),
    MAGMA_CUBE("Cubo de Magma", "MHF_LAVASLIME", EntityType.MAGMA_CUBE),
    WITHER("Wither", "MHF_WSKELETON", EntityType.WITHER),
    PIG("Porco", "MHF_PIG", EntityType.PIG),
    SHEEP("Ovelha", "MHF_SHEEP", EntityType.SHEEP),
    COW("Vaca", "MHF_COW", EntityType.COW),
    CHICKEN("Galinha", "MHF_CHICKEN", EntityType.CHICKEN),
    SQUID("Lula", "MHF_SQUID", EntityType.SQUID),
    MUSHROOM_COW("Vaca Cogumelo", "MHF_MUSHROOMCOW", EntityType.MUSHROOM_COW),
    OCELOT("Jaguatirica", "MHF_OCELOT", EntityType.OCELOT),
    IRON_GOLEM("Golem de Ferro", "MHF_GOLEM", EntityType.IRON_GOLEM),
    VILLAGER("Villager", "MHF_VILLAGER", EntityType.VILLAGER),
    ENDERMITE("Endermite", "MHF_ENDERMITE", EntityType.ENDERMITE),
    RABBIT("Coelho", "MHF_RABBIT", EntityType.RABBIT),
    WITCH("Bruxa", "MHF_WITCH", EntityType.WITCH),
    WOLF("Lobo", "MHF_WOLF", EntityType.WOLF),
    BAT("Morcego", "MHF_BAT", EntityType.BAT),
    GUARDIAN("Guardião", "MHF_GUARDIAN", EntityType.GUARDIAN),
    ENDER_DRAGON("EnderDragon", "MHF_ENDERDRAGON", EntityType.ENDER_DRAGON),

    // I have not found official heads for the entities below
    SNOWMAN("Golem de Neve", "Snowman_7", EntityType.SNOWMAN),
    SILVERFISH("Traça", "MHF_COCONUTB", EntityType.SILVERFISH),
    HORSE("Cavalo", "dominodoggy", EntityType.HORSE);

    public String name, head;
    public EntityType type;

    public static EntityType getByName(String name) {
        return Arrays.stream(SpawnerType.values()).filter(types -> types.name.equalsIgnoreCase(name)).findFirst().get().type;
    }
}
