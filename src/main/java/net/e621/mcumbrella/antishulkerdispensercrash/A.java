package net.e621.mcumbrella.antishulkerdispensercrash;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.event.block.*;
import org.spongepowered.api.block.*;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.util.Direction;

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
            e.setCancelled(true);
            l.warn("Blocked possible shulker box dispenser crash at: X="+e.getTargetBlock().getPosition().getX()+", Y="+e.getTargetBlock().getPosition().getY()+", Z="+e.getTargetBlock().getPosition().getZ());
        }
    }
}
