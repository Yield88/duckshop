package tk.kirlian.SignTraderWithDucks.permissions;

import org.bukkit.entity.Player;

import tk.kirlian.util.provider.Provider;
import tk.kirlian.util.provider.ProviderManager;
import tk.kirlian.util.provider.PriorityProviderManager;

/**
 * Provides a consistent interface to various ways of deciding permissions.
 */
public abstract class PermissionsProvider implements Provider {
    private static PriorityProviderManager<PermissionsProvider> manager;

    /**
     * Get the {@link PriorityProviderManager} for this class.
     */
    public static PriorityProviderManager<PermissionsProvider> getManager() {
        if(manager == null) {
            manager = new PriorityProviderManager<PermissionsProvider>();
            /* vvv Add new providers below vvv */
            manager.register(OpsOnlyPermissionsProvider.getInstance());
            /* ^^^ Add new providers above ^^^ */
        }
        return manager;
    }

    /**
     * Decide whether a player has permission to do something.
     */
    abstract public boolean playerHasPermission(Player player, String permission);
}
