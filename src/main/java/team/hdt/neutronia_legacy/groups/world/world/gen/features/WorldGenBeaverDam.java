package team.hdt.neutronia_legacy.groups.world.world.gen.features;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import team.hdt.neutronia_legacy.entity.EntityBeaver;
import team.hdt.neutronia_legacy.groups.world.features.overworld.WorldDecorationFeatures;
import team.hdt.neutronia_legacy.groups.world.util.GeneratorUtils;

import java.util.Random;

public class WorldGenBeaverDam {
    private final static IBlockState AIR = Blocks.AIR.getDefaultState();
    private final static IBlockState WATER = Blocks.WATER.getDefaultState();
    private final IBlockState blockyMain;
    private final IBlockState blockySecond;
    private final boolean doMobSpawn;
    private final int SOLID_BLOCK = 1;
    private final int AIR_BLOCK = 2;
    private final int OFFSET = 12;
    private final int MUD_CHANCE = 20; //percent chance of dam block being mud (aka blockySecond)

    public WorldGenBeaverDam(IBlockState blocky, IBlockState blocky2, boolean doMobSpawn) {
        this.blockyMain = blocky;
        this.blockySecond = blocky2;
        this.doMobSpawn = doMobSpawn;
    }

    public boolean generate(World worldIn, Random rand, BlockPos start, BlockPos end, WorldDecorationFeatures.Direction dir) {

        //get the length of dam about to generate
        double a = Math.abs(start.getX() - end.getX());
        double b = Math.abs(start.getZ() - end.getZ());
        double damLength = Math.sqrt(a * a + b * b);

        //no shorties
        if (damLength < 5.0)
            return false;

        short[][][] damMap = genDamMap(dir, rand);
        BlockPos center = new BlockPos((start.getX() + end.getX()) / 2, (start.getY() + end.getY()) / 2, (start.getZ() + end.getZ()) / 2);
        int riverDepth = worldIn.getSeaLevel() + 1 - GeneratorUtils.getOceanSurfaceHeight(worldIn, center.getX(), center.getZ());
        int damHeight = riverDepth + rand.nextInt(2) + 3;

        //make sure the dam hight doesn't go out of bounds of array
        damHeight = damHeight > 11 ? 11 : damHeight;

        //make sure damn height doesn't build higher than sealevel +2
        damHeight = damHeight + center.getY() - riverDepth > worldIn.getSeaLevel() + 2 ? worldIn.getSeaLevel() + 2 - (center.getY() - riverDepth) : damHeight;

        //do one pass of the mock game of life to smooth out rough edges
        damMap[0] = cellStep(damMap);

        //make 3 more layers above base
        for (int n = 1; n <= damHeight; n++) {
            damMap[n] = layerStep(damMap, rand, n);
        }

        //set interior blocks to air
        damMap = hollowDam(damMap, damHeight);

        //build that dam
        for (int x = 0; x < 25; x++) {
            for (int z = 0; z < 25; z++) {
                for (int y = 0; y <= damHeight; y++) {
                    //dont think we need this check here anymore
                    if ((center.getY() + y - riverDepth) < worldIn.getSeaLevel() + 2) {
                        //only replace air and water blocks
                        //if(isReplaceable(worldIn, center.add(x-OFFSET, y-riverDepth, z-OFFSET))) {
                        if (true) {
                            if (damMap[y][x][z] == SOLID_BLOCK) {
                                if (rand.nextInt(100) > MUD_CHANCE)
                                    worldIn.setBlockState(center.add(x - OFFSET, y - riverDepth, z - OFFSET), blockyMain);
                                else
                                    worldIn.setBlockState(center.add(x - OFFSET, y - riverDepth, z - OFFSET), blockySecond);
                            } else if (damMap[y][x][z] == AIR_BLOCK) {
                                worldIn.setBlockState(center.add(x - OFFSET, y - riverDepth, z - OFFSET), AIR);
                            }
                        }
                    }
                }
            }
        }

        if (doMobSpawn) {
            EntityBeaver mrBeaver;
            //spawn 3-5 beavers inside
            int numBeavers = 3 + rand.nextInt(3);
            for (int n = 0; n < numBeavers; n++) {
                mrBeaver = new EntityBeaver(worldIn);
                mrBeaver.setLocationAndAngles((double) ((float) (center.getX() + rand.nextInt(2) + 0.5)), (double) center.getY() + 1 - riverDepth, (double) ((float) (center.getZ() + rand.nextInt(2) + 0.5)), rand.nextFloat() * 360.0F, 0.0F);
                worldIn.spawnEntity(mrBeaver);
            }

            //50% chance to add a beaver on top of the dam
            if (rand.nextBoolean()) {
                mrBeaver = new EntityBeaver(worldIn);
                mrBeaver.setLocationAndAngles((double) ((float) center.getX() + rand.nextInt(2) + 0.5F), (double) 1 + damHeight + center.getY() - riverDepth, (double) ((float) center.getZ() + rand.nextInt(2) + 0.5F), rand.nextFloat() * 360.0F, 0.0F);
                worldIn.spawnEntity(mrBeaver);
            }
        }
        return true;
    }

    private boolean isReplaceable(World world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        return state == AIR || state == WATER;
    }

    //makes the base map with '1' values in a rectangle with rough edges facing the direction of dir
    private short[][][] genDamMap(WorldDecorationFeatures.Direction dir, Random rand) {
        short[][][] damMap = new short[12][25][25];

        //draw a normal rectangle if dam goes east to west
        if (dir == WorldDecorationFeatures.Direction.E) {
            for (int z = 9; z < 16; z++) {
                for (int x = 5; x < 20; x++) {
                    if ((z <= 10 || z >= 14) && rand.nextInt(100) < 50)
                        continue;

                    damMap[0][x][z] = SOLID_BLOCK;
                }
            }
        }
        //draw a diagonal rectangle if dam runs north-east or south-east
        else {
            for (int z = 8; z < 17; z++) {
                for (int x = 5; x < 20; x++) {
                    if ((z <= 9 || z >= 15) && rand.nextInt(100) < 50)
                        continue;

                    if (dir == WorldDecorationFeatures.Direction.NE)
                        damMap[0][x][z - x + OFFSET] = SOLID_BLOCK;
                    else if (dir == WorldDecorationFeatures.Direction.SE)
                        damMap[0][x][z + x - OFFSET] = SOLID_BLOCK;
                }
            }
        }

        //try to add some more randomness to the shape. not sure how well this works
        //add random circle blobs to rectangle shape above
        for (int bNum = 0; bNum < 3; bNum++) {
            int blobXOffset = 9;
            int blobZOffset = 10;
            int addBlobX = rand.nextInt(9) + blobXOffset;
            int addBlobZ = rand.nextInt(6) + blobZOffset;
            int blobSize = rand.nextInt(3) + 2;
            for (int xdif = -blobSize; xdif <= blobSize; xdif++) {
                for (int zdif = -blobSize; zdif <= blobSize; zdif++) {
                    if (xdif * xdif + zdif * zdif <= blobSize * blobSize) {
                        if (rand.nextInt(100) > 20) {
                            int zPosNE = addBlobZ + zdif - (addBlobX + xdif) + OFFSET;
                            int zPosSE = addBlobZ + zdif + (addBlobX + xdif) - OFFSET;
                            if (zPosNE >= 0 && zPosSE >= 0 && zPosNE < 25 && zPosSE < 25) {
                                if (dir == WorldDecorationFeatures.Direction.E)
                                    damMap[0][addBlobX + xdif][addBlobZ + zdif] = SOLID_BLOCK;
                                else if (dir == WorldDecorationFeatures.Direction.NE)
                                    damMap[0][addBlobX + xdif][addBlobZ + zdif - (addBlobX + xdif) + OFFSET] = SOLID_BLOCK;
                                else if (dir == WorldDecorationFeatures.Direction.SE)
                                    damMap[0][addBlobX + xdif][addBlobZ + zdif + (addBlobX + xdif) - OFFSET] = SOLID_BLOCK;
                            }
                        }
                    }
                }
            }
        }
        return damMap;
    }

    //reads dam array. if position is surrounded by 4 or more solid blocks ('1' values) then it either says solid or turns solid.
    private short[][] cellStep(short[][][] inMap) {
        short[][] outMap = new short[25][25];
        for (int x = 0; x < 25; x++) {
            for (int z = 0; z < 25; z++) {
                if (countFriends(inMap, x, 0, z) >= 4)
                    outMap[x][z] = SOLID_BLOCK;
            }
        }

        return outMap;
    }

    //counts the number of '1' values in surrounding 8 positions in array on same y level
    private int countFriends(short[][][] map, int x, int y, int z) {
        int friends = 0;
        for (int xdif = -1; xdif <= 1; xdif++) {
            for (int zdif = -1; zdif <= 1; zdif++) {
                //dont count the origin cell
                if (!(xdif == 0 && zdif == 0)) {
                    int x2 = x + xdif;
                    int z2 = z + zdif;
                    //make sure we dont go out of bounds
                    if (x2 > 0 && z2 > 0 && x2 < 25 && z2 < 25) {
                        if (map[y][x2][z2] == SOLID_BLOCK)
                            friends++;
                    }
                }
            }
        }
        return friends;
    }

    //generates a new layer based on blocks below current layer
    private short[][] layerStep(short[][][] map, Random rand, int yLevel) {
        short[][] outMap = new short[25][25];
        for (int x = 0; x < 25; x++) {
            for (int z = 0; z < 25; z++) {
                //only check blocks who are above solid blocks from layer below
                if (map[yLevel - 1][x][z] == SOLID_BLOCK) {
                    int friends = countFriends(map, x, yLevel - 1, z);
                    if (friends > 5) {
                        outMap[x][z] = SOLID_BLOCK;
                    }
                    //blocks with exactly 5 friends only have a 50% chance of staying solid to add some variation to final shape
                    else if (friends == 5 && rand.nextInt(100) > 50) {
                        outMap[x][z] = SOLID_BLOCK;
                    }
                }
            }
        }

        return outMap;
    }

    //turn blocks to '2' values (air) if they have solid blocks on all 6 faces
    private short[][][] hollowDam(short[][][] inMap, int height) {
        short[][][] outMap = new short[height][25][25];
        for (int x = 0; x < 25; x++) {
            for (int z = 0; z < 25; z++) {
                for (int y = 0; y < height; y++) {
                    if (x > 0 && y > 0 && z > 0 &&
                            x < 24 && z < 24 && y < height - 1 &&
                            inMap[y][x][z] == SOLID_BLOCK &&
                            inMap[y - 1][x][z] == SOLID_BLOCK &&
                            inMap[y + 1][x][z] == SOLID_BLOCK &&
                            inMap[y][x - 1][z] == SOLID_BLOCK &&
                            inMap[y][x + 1][z] == SOLID_BLOCK &&
                            inMap[y][x][z - 1] == SOLID_BLOCK &&
                            inMap[y][x][z + 1] == SOLID_BLOCK) {
                        outMap[y][x][z] = AIR_BLOCK;
                    } else {
                        outMap[y][x][z] = inMap[y][x][z];
                    }

                }
            }
        }
        return outMap;
    }
}