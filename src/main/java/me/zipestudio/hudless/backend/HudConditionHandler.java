package me.zipestudio.hudless.backend;

import me.zipestudio.hudless.config.LeafyConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;

public class HudConditionHandler {
    
    private static int prevSlot;
    private static int prevHealth;
    private static int prevHunger;
    private static int prevArmor;
    private static int prevAir;
    private static int prevEffects;
    private static int prevMountHealth;
    private static float prevExperience;

    public static void tick() {
        Minecraft client = Minecraft.getInstance();
        Player player = client.player;
        LeafyConfig config = LeafyConfig.getInstance();

        if (player == null || player.isSpectator()) {
            return;
        }

        //? if >=1.21.5 {
        int currentSlot = player.getInventory().getSelectedSlot();
        //?} else {
        /*int currentSlot = player.getInventory().selected;
        *///?}

        if (currentSlot != prevSlot) {
            prevSlot = currentSlot;

            if (config.getHuds().isHideHotbar() && config.getTriggers().isTriggerHotbar()) {
                HudAnimationHandler.revealHud();
            }
        }

        int currentHealth = (int) player.getHealth();
        if (currentHealth != prevHealth) {
            prevHealth = currentHealth;

            if (config.getHuds().isHideStatusBar() && config.getTriggers().isTriggerHealth()) {
                HudAnimationHandler.revealHud();
            }
        }

        int currentHunger = player.getFoodData().getFoodLevel();
        if (currentHunger != prevHunger) {
            prevHunger = currentHunger;

            if (config.getHuds().isHideStatusBar() && config.getTriggers().isTriggerHunger()) {
                HudAnimationHandler.revealHud();
            }
        }

        int currentArmor = player.getArmorValue();
        if (currentArmor != prevArmor) {
            prevArmor = currentArmor;

            if (config.getHuds().isHideStatusBar() && config.getTriggers().isTriggerArmor()) {
                HudAnimationHandler.revealHud();
            }
        }

        int currentAir = player.getAirSupply();
        if (currentAir != prevAir) {
            prevAir = currentAir;

            if (config.getHuds().isHideStatusBar() && config.getTriggers().isTriggerAirBubbles()) {
                HudAnimationHandler.revealHud();
            }
        }

        int currentEffects = player.getActiveEffects().size();
        if (currentEffects != prevEffects) {
            prevEffects = currentEffects;

            if (config.getHuds().isHideEffects() && config.getTriggers().isTriggerEffects()) {
                HudAnimationHandler.revealHud();
            }
        }

        if (player.getVehicle() != null && player.getVehicle() instanceof net.minecraft.world.entity.LivingEntity mount) {

            int mountHealth = (int) mount.getHealth();
            if (mountHealth != prevMountHealth) {

                prevMountHealth = mountHealth;
                if (config.getHuds().isHideStatusBar() && config.getTriggers().isTriggerMountHealth()) {
                    HudAnimationHandler.revealHud();
                }

            }
        }

        float currentExperience = player.experienceProgress;
        if (currentExperience != prevExperience) {
            prevExperience = currentExperience;
            if (config.getHuds().isHideProgressBar() && config.getTriggers().isTriggerExperience()) {
                HudAnimationHandler.revealHud();
            }
        }

    }

}