package net.e621.mcumbrella.antishulkerdispensercrash;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.Inventory;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.event.block.*;
import org.spongepowered.api.block.*;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.util.Direction;

@Plugin(
        id = "asdc",
        name = "AntiShulkerDispenserCrash",
        version = "1.2",
        url = "https://github.com/MCUmbrella/AntiShulkerDispenserCrash/",
        authors = {
                "MCUmbrella"
        },
        description = "Block possible shulker box dispenser crashes"
)
public class A {

    static final ItemType[] blkList ={
            ItemTypes.BLACK_SHULKER_BOX,
            ItemTypes.BLUE_SHULKER_BOX,
            ItemTypes.BROWN_SHULKER_BOX,
            ItemTypes.CYAN_SHULKER_BOX,
            ItemTypes.GRAY_SHULKER_BOX,
            ItemTypes.GREEN_SHULKER_BOX,
            ItemTypes.LIGHT_BLUE_SHULKER_BOX,
            ItemTypes.LIME_SHULKER_BOX,
            ItemTypes.MAGENTA_SHULKER_BOX,
            ItemTypes.ORANGE_SHULKER_BOX,
            ItemTypes.PINK_SHULKER_BOX,
            ItemTypes.PURPLE_SHULKER_BOX,
            ItemTypes.RED_SHULKER_BOX,
            ItemTypes.SILVER_SHULKER_BOX,
            ItemTypes.WHITE_SHULKER_BOX,
            ItemTypes.YELLOW_SHULKER_BOX
    };

    @Inject
    private Logger l;
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        l.info("owo");
    }
    @Listener
    public void onSDC(TickBlockEvent e)
    {
        if(e.getTargetBlock().getState().getType()==BlockTypes.DISPENSER &&(
                (e.getTargetBlock().getPosition().getY()==255&&e.getTargetBlock().getExtendedState().get(Keys.DIRECTION).get()==Direction.UP)||
                (e.getTargetBlock().getPosition().getY()==0&&e.getTargetBlock().getExtendedState().get(Keys.DIRECTION).get()==Direction.DOWN))
        )
        {
            Inventory i=(Inventory)e.getTargetBlock().getLocation().get().getTileEntity().get();
            for(ItemType b: blkList){if(i.contains(b)){e.setCancelled(true);l.warn("Blocked possible shulker box dispenser crash at: X="+e.getTargetBlock().getPosition().getX()+", Y="+e.getTargetBlock().getPosition().getY()+", Z="+e.getTargetBlock().getPosition().getZ()+" in world: "+e.getTargetBlock().getWorldUniqueId());break;}} //TODO: transfer uuid to world name
        }
    }
}
