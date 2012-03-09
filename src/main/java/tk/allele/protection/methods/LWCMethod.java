package tk.allele.protection.methods;

import com.griefcraft.lwc.LWC;
import com.griefcraft.lwc.LWCPlugin;
import com.griefcraft.model.Protection;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import tk.allele.protection.ProtectionMethod;

import java.util.List;

/**
 * Support for LWC by Hidendra.
 *
 * @see <a href="http://forums.bukkit.org/threads/967/">LWC</a>
 */
public class LWCMethod implements ProtectionMethod {
    private LWC lwc;

    public LWCMethod(Plugin plugin) {
        Plugin lwcPlugin = plugin.getServer().getPluginManager().getPlugin("LWC");
        if (lwcPlugin != null && lwcPlugin instanceof LWCPlugin) {
            lwc = ((LWCPlugin) lwcPlugin).getLWC();
        }
    }

    @Override
    public boolean isEnabled() {
        return (lwc != null);
    }

    @Override
    public String toString() {
        return "LWC";
    }

    private boolean canAccessProtection(String playerName, Protection protection) {
        if (protection.getType() == Protection.Type.PUBLIC) {
            return true;
        }
        if (playerName.equalsIgnoreCase(protection.getOwner())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canAccess(String playerName, Block block) {
        // Use getProtectionSet() to grab both parts of a double chest
        List<Block> blocksProtected = lwc.getProtectionSet(block.getWorld(), block.getX(), block.getY(), block.getZ());
        for (Block chestBlock : blocksProtected) {
            Protection protection = lwc.findProtection(chestBlock);
            if (protection != null && !canAccessProtection(playerName, protection)) {
                return false;
            }
        }
        // If none of the blocks are protected, anyone can access it
        return true;
    }

    @Override
    public boolean canAccess(Player player, Block block) {
        return canAccess(player.getName(), block);
    }
}

