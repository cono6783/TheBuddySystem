package cono6783.thebuddysystem.util;

import net.minecraft.core.BlockPos;

public class LocationFindUtil {


    /**
     * Gets the actual distance in blocks between two positions
     *
     * @param pos1 Blockpos 1
     * @param pos2 Blockpos 2
     * @return distance in blocks.
     */
    public static int getDistance(final BlockPos pos1, final BlockPos pos2)
    {
        final long xDiff = pos1.getX() - pos2.getX();
        final long yDiff = pos1.getY() - pos2.getY();
        final long zDiff = pos1.getZ() - pos2.getZ();

        return (int) Math.round(Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff));
    } // Yoinked from Minecolonies




}
