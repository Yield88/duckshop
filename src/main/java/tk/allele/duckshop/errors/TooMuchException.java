package tk.allele.duckshop.errors;

import org.bukkit.entity.Player;
import tk.allele.duckshop.items.Item;

/**
 * Thrown to indicate a player does not have enough space to store an item.
 */
public class TooMuchException extends TradingException {
    public TooMuchException(Player player, Item item) {
        super(player, item);
    }
}
