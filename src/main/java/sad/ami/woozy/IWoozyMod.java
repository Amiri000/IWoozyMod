package sad.ami.woozy;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(IWoozyMod.MODID)
public class IWoozyMod {
    public static final String MODID = "woozy";
    private static final Logger LOGGER = LogUtils.getLogger();

    public IWoozyMod(IEventBus modEventBus, ModContainer modContainer) {

    }
}
