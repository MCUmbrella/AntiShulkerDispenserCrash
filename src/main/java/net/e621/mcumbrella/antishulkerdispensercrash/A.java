package net.e621.mcumbrella.antishulkerdispensercrash;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.block.tileentity.carrier.Dispenser;
import org.spongepowered.api.block.tileentity.*;
import org.spongepowered.api.block.tileentity.carrier.ShulkerBox;
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
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.HashSet;

@Plugin(
        id = "asdc",
        name = "AntiShulkerDispenserCrash",
        version = "1.0",
        url = "https://github.com/MCUmbrella/AntiShulkerDispenserCrash/",
        authors = {
                "MCUmbrella"
        },
        description = "Block possible shulker box dispenser crashes"
)
public class A {

    HashSet<ItemType> blkList=new HashSet<ItemType>();

    @Inject
    private Logger l;
    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        blkList.add(ItemTypes.BLACK_SHULKER_BOX);
        blkList.add(ItemTypes.BLUE_SHULKER_BOX);
        blkList.add(ItemTypes.BROWN_SHULKER_BOX);
        blkList.add(ItemTypes.CYAN_SHULKER_BOX);
        blkList.add(ItemTypes.GRAY_SHULKER_BOX);
        blkList.add(ItemTypes.GREEN_SHULKER_BOX);
        blkList.add(ItemTypes.LIGHT_BLUE_SHULKER_BOX);
        blkList.add(ItemTypes.LIME_SHULKER_BOX);
        blkList.add(ItemTypes.MAGENTA_SHULKER_BOX);
        blkList.add(ItemTypes.ORANGE_SHULKER_BOX);
        blkList.add(ItemTypes.PINK_SHULKER_BOX);
        blkList.add(ItemTypes.PURPLE_SHULKER_BOX);
        blkList.add(ItemTypes.RED_SHULKER_BOX);
        blkList.add(ItemTypes.SILVER_SHULKER_BOX);
        blkList.add(ItemTypes.WHITE_SHULKER_BOX);
        blkList.add(ItemTypes.YELLOW_SHULKER_BOX);
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
            //e.setCancelled(true);
            //Dispenser d=(Dispenser)e.getTargetBlock().getLocation().get().getTileEntity().get();
            Inventory i=(Inventory)e.getTargetBlock().getLocation().get().getTileEntity().get();
            for(ItemType b:blkList){if(i.contains(b)){e.setCancelled(true);l.warn("Blocked possible shulker box dispenser crash at: X="+e.getTargetBlock().getPosition().getX()+", Y="+e.getTargetBlock().getPosition().getY()+", Z="+e.getTargetBlock().getPosition().getZ());break;}}
            //l.warn("Blocked possible shulker box dispenser crash at: X="+e.getTargetBlock().getPosition().getX()+", Y="+e.getTargetBlock().getPosition().getY()+", Z="+e.getTargetBlock().getPosition().getZ());
        }
    }
}
