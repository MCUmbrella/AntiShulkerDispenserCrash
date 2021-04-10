package net.e621.mcumbrella.antishulkerdispensercrash;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
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

import java.io.*;
import java.util.Properties;

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
public class A
{
    boolean accurateDetection=true;
    boolean warnConsole=true;
    final static String cfgPath="."+File.separator+"config"+File.separator+"ASDC"+ File.separator+"config.properties";
    static final ItemType[] blkList=
    {
        ItemTypes.RED_SHULKER_BOX,
        ItemTypes.BLACK_SHULKER_BOX,
        ItemTypes.WHITE_SHULKER_BOX,
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
        ItemTypes.SILVER_SHULKER_BOX,
        ItemTypes.YELLOW_SHULKER_BOX
    };

    @Inject
    Logger l;

    public class CheckerAccurate
    {
        @Listener
        public void onSDC(TickBlockEvent e)
        {
            if(e.getTargetBlock().getState().getType()==BlockTypes.DISPENSER &&(
                    (e.getTargetBlock().getPosition().getY()==255&&e.getTargetBlock().getExtendedState().get(Keys.DIRECTION).get()==Direction.UP)||
                    (e.getTargetBlock().getPosition().getY()==0&&e.getTargetBlock().getExtendedState().get(Keys.DIRECTION).get()==Direction.DOWN))
            )
            {
                Inventory i=(Inventory)e.getTargetBlock().getLocation().get().getTileEntity().get();
                for(ItemType b: blkList)
                {
                    if(i.contains(b))
                    {
                        e.setCancelled(true);
                        if(warnConsole){l.warn("Blocked possible shulker box dispenser crash at: X="+e.getTargetBlock().getPosition().getX()+", Y="+e.getTargetBlock().getPosition().getY()+", Z="+e.getTargetBlock().getPosition().getZ()+" in world: "+e.getTargetBlock().getWorldUniqueId());}
                        break;
                    }
                }
            }
        }
    }
    public class Checker
    {
        @Listener
        public void onSDC(TickBlockEvent e)
        {
            if(e.getTargetBlock().getState().getType()==BlockTypes.DISPENSER &&(
                    (e.getTargetBlock().getPosition().getY()==255&&e.getTargetBlock().getExtendedState().get(Keys.DIRECTION).get()==Direction.UP)||
                    (e.getTargetBlock().getPosition().getY()==0&&e.getTargetBlock().getExtendedState().get(Keys.DIRECTION).get()==Direction.DOWN))
            )
            {
                e.setCancelled(true);
                if(warnConsole){l.warn("Blocked suspicious dispenser action at: X="+e.getTargetBlock().getPosition().getX()+", Y="+e.getTargetBlock().getPosition().getY()+", Z="+e.getTargetBlock().getPosition().getZ()+" in world: "+e.getTargetBlock().getWorldUniqueId());}
            }
        }
    }

    void loadCfg()
    {
        try
        {
            new File(new File(cfgPath).getParent()).mkdirs();
            File file=new File(cfgPath);
            if(!file.exists())
            {
                l.info("Creating default config");
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                bw.write(
                        "accurateDetection=true"+System.getProperty("line.separator")
                        +"warnConsole=true"+System.getProperty("line.separator")
                );
                bw.flush();
                bw.close();
            }
            BufferedReader cfg = new BufferedReader(new FileReader(file));
            Properties p = new Properties();
            p.load(cfg);
            accurateDetection=Boolean.parseBoolean(p.getProperty("accurateDetection"));
            warnConsole=Boolean.parseBoolean(p.getProperty("warnConsole"));

        }catch(Exception x)
        {
            l.warn("Can't load config: "+x.toString());
        }
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event)
    {
        loadCfg();
        if(accurateDetection)
        {
            Sponge.getEventManager().registerListeners(this, new CheckerAccurate());
            l.info("Using accurate detection");
        }
        else
        {
            Sponge.getEventManager().registerListeners(this, new Checker());
            l.info("Using fast detection");
        }

        l.info("Initialized");
    }
}
