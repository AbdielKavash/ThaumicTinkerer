/**
 * This class was created by <Vazkii>. It's distributed as part of the ThaumicTinkerer Mod.
 *
 * ThaumicTinkerer is Open Source and distributed under a Creative Commons Attribution-NonCommercial-ShareAlike 3.0
 * License (http://creativecommons.org/licenses/by-nc-sa/3.0/deed.en_GB)
 *
 * ThaumicTinkerer is a Derivative Work on Thaumcraft 4. Thaumcraft 4 (c) Azanor 2012
 * (http://www.minecraftforum.net/topic/1585216-)
 *
 * File Created @ [12 Sep 2013, 01:29:47 (GMT)]
 */
package thaumic.tinkerer.common.core.helper;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

import thaumcraft.api.aspects.AspectList;
import thaumcraft.codechicken.lib.vec.Vector3;
import thaumic.tinkerer.common.ThaumicTinkerer;

public final class MiscHelper {

    public static double pointDistanceSpace(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2) + Math.pow(z1 - z2, 2));
    }

    public static boolean breakBlockWithCheck(World w, int x, int y, int z, Block block, int meta, int flag,
            EntityPlayer player) {
        if (!MinecraftForge.EVENT_BUS.post(new BlockEvent.BreakEvent(x, y, z, w, w.getBlock(x, y, z), meta, player))) {
            w.setBlock(x, y, z, block, meta, flag);
            return true;
        }
        return false;
    }

    public static boolean breakBlockToAirWithCheck(World w, int x, int y, int z, EntityPlayer player) {
        return breakBlockWithCheck(w, x, y, z, Blocks.air, 0, 3, player);
    }

    public static boolean breakBlockWithCheck(World w, int x, int y, int z, Block block, EntityPlayer player) {
        return breakBlockWithCheck(w, x, y, z, block, 0, 0, player);
    }

    public static MinecraftServer server() {
        return MinecraftServer.getServer();
    }

    public static void setEntityMotionFromVector(Entity entity, Vector3 originalPosVector, float modifier) {
        Vector3 entityVector = Vector3.fromEntityCenter(entity);
        Vector3 finalVector = originalPosVector.copy().subtract(entityVector);

        if (finalVector.mag() > 1) finalVector.normalize();

        entity.motionX = finalVector.x * modifier;
        entity.motionY = finalVector.y * modifier;
        entity.motionZ = finalVector.z * modifier;
    }

    public static AspectList multiplyAspectList(AspectList list, double multiplier) {
        AspectList newList = list.copy();
        if (multiplier == 1) return newList;

        newList.aspects.replaceAll((a, v) -> (int) ((double) newList.aspects.get(a) * multiplier));

        return newList;
    }

    public static void printCurrentStackTrace(String message) {
        if (message != null) ThaumicTinkerer.log.info(message);

        for (StackTraceElement element : Thread.currentThread().getStackTrace()) ThaumicTinkerer.log.info(element);
    }
}
