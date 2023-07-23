package io.github.happyhippo77.witchery2.world.trees;


import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.happyhippo77.witchery2.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class HawthornTrunkPlacer extends TrunkPlacer {
    static final byte[] otherCoordPairs = new byte[]{(byte)2, (byte)0, (byte)0, (byte)1, (byte)2, (byte)1};
    List<FoliagePlacer.TreeNode> nodes = new ArrayList<>();
    Random rand;
    World worldObj;
    int[] basePos;
    int height;
    int heightLimit;
    double branchDensity;
    double branchSlope;
    double scaleWidth;
    double leafDensity;
    int trunkSize;
    double heightAttenuation;
    int heightLimitLimit;
    int leafDistanceLimit;
    int[][] leafNodes;

    List<BlockPos> logPositions = new ArrayList<>();

    // Use the fillTrunkPlacerFields to create our codec
    public static final Codec<HawthornTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
            fillTrunkPlacerFields(instance).apply(instance, HawthornTrunkPlacer::new));

    public HawthornTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);

        this.rand = Random.create();
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return ModPlacers.HAWTHORN_TRUNK_PLACER;
    }

    float layerSize(int par1) {
        if((double)par1 < (double)this.heightLimit * 0.3D) {
            return -1.618F;
        } else {
            float f = (float)this.heightLimit / 2.0F;
            float f1 = (float)this.heightLimit / 2.0F - (float)par1;
            float f2;
            if(f1 == 0.0F) {
                f2 = f;
            } else if(Math.abs(f1) >= f) {
                f2 = 0.0F;
            } else {
                f2 = (float)Math.sqrt(Math.pow((double)Math.abs(f), 2.0D) - Math.pow((double)Math.abs(f1), 2.0D));
            }

            f2 *= 0.5F;
            return f2;
        }
    }

    void generateLeafNodeList() {
        this.height = (int)((double)this.heightLimit * this.heightAttenuation);
        if(this.height >= this.heightLimit) {
            this.height = this.heightLimit - 1;
        }

        int i = (int)(1.382D + Math.pow(this.leafDensity * (double)this.heightLimit / 13.0D, 2.0D));
        if(i < 1) {
            i = 1;
        }

        int[][] aint = new int[i * this.heightLimit][4];
        int j = this.basePos[1] + this.heightLimit - this.leafDistanceLimit;
        int k = 1;
        int l = this.basePos[1] + this.height;
        int i1 = j - this.basePos[1];
        aint[0][0] = this.basePos[0];
        aint[0][1] = j;
        aint[0][2] = this.basePos[2];
        aint[0][3] = l;
        --j;

        while(i1 >= 0) {
            int j1 = 0;
            float f = this.layerSize(i1);
            if(f < 0.0F) {
                --j;
                --i1;
            } else {
                for(double d0 = 0.5D; j1 < i; ++j1) {
                    double d1 = this.scaleWidth * (double)f * ((double)this.rand.nextFloat() + 0.328D);
                    double d2 = (double)this.rand.nextFloat() * 2.0D * 3.141592653589793D;
                    int k1 = MathHelper.floor(d1 * Math.sin(d2) + (double)this.basePos[0] + d0);
                    int l1 = MathHelper.floor(d1 * Math.cos(d2) + (double)this.basePos[2] + d0);
                    int[] aint1 = new int[]{k1, j, l1};
                    int[] aint2 = new int[]{k1, j + this.leafDistanceLimit, l1};
                    if(this.checkBlockLine(aint1, aint2) == -1) {
                        int[] aint3 = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]};
                        double d3 = Math.sqrt(Math.pow((double)Math.abs(this.basePos[0] - aint1[0]), 2.0D) + Math.pow((double)Math.abs(this.basePos[2] - aint1[2]), 2.0D));
                        double d4 = d3 * this.branchSlope;
                        if((double)aint1[1] - d4 > (double)l) {
                            aint3[1] = l;
                        } else {
                            aint3[1] = (int)((double)aint1[1] - d4);
                        }

                        if(this.checkBlockLine(aint3, aint1) == -1) {
                            aint[k][0] = k1;
                            aint[k][1] = j;
                            aint[k][2] = l1;
                            aint[k][3] = aint3[1];
                            ++k;
                        }
                    }
                }

                --j;
                --i1;
            }
        }

        this.leafNodes = new int[k][4];
        System.arraycopy(aint, 0, this.leafNodes, 0, k);
    }

    boolean leafNodeNeedsBase(int par1) {
        return (double)par1 >= (double)this.heightLimit * 0.2D;
    }

    void generateLeafNodeBases() {
        int i = 0;
        int j = this.leafNodes.length;

        for(int[] aint = new int[]{this.basePos[0], this.basePos[1], this.basePos[2]}; i < j; ++i) {
            int[] aint1 = this.leafNodes[i];
            int[] aint2 = new int[]{aint1[0], aint1[1], aint1[2]};
            aint[1] = aint1[3];
            int k = aint[1] - this.basePos[1];
            if(this.leafNodeNeedsBase(k)) {
                this.placeBlockLine(aint, aint2);
            }
        }

    }

    void placeBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger) {
        int[] aint2 = new int[]{0, 0, 0};
        byte b0 = 0;

        byte b1;
        for(b1 = 0; b0 < 3; ++b0) {
            aint2[b0] = par2ArrayOfInteger[b0] - par1ArrayOfInteger[b0];
            if(Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
                b1 = b0;
            }
        }

        if(aint2[b1] != 0) {
            byte b2 = otherCoordPairs[b1];
            byte b3 = otherCoordPairs[b1 + 3];
            byte b4;
            if(aint2[b1] > 0) {
                b4 = 1;
            } else {
                b4 = -1;
            }

            double d0 = (double)aint2[b2] / (double)aint2[b1];
            double d1 = (double)aint2[b3] / (double)aint2[b1];
            int[] aint3 = new int[]{0, 0, 0};
            int j = 0;

            for(int k = aint2[b1] + b4; j != k; j += b4) {
                aint3[b1] = MathHelper.floor((double)(par1ArrayOfInteger[b1] + j) + 0.5D);
                aint3[b2] = MathHelper.floor((double)par1ArrayOfInteger[b2] + (double)j * d0 + 0.5D);
                aint3[b3] = MathHelper.floor((double)par1ArrayOfInteger[b3] + (double)j * d1 + 0.5D);
                boolean b5 = false;
                int l = Math.abs(aint3[0] - par1ArrayOfInteger[0]);
                int i1 = Math.abs(aint3[2] - par1ArrayOfInteger[2]);
                int j1 = Math.max(l, i1);
                if(j1 > 0) {
                    if(l == j1) {
                        b5 = true;
                    } else if(i1 == j1) {
                        b5 = true;
                    }
                }

                logPositions.add(new BlockPos(aint3[0], aint3[1], aint3[2]));
            }
        }

    }

    void generateTrunk() {
        int i = this.basePos[0];
        int j = this.basePos[1];
        int k = this.basePos[1] + this.height;
        int l = this.basePos[2];
        int[] aint = new int[]{i, j, l};
        int[] aint1 = new int[]{i, k, l};
        this.placeBlockLine(aint, aint1);
        if(this.trunkSize == 2) {
            ++aint[0];
            ++aint1[0];
            this.placeBlockLine(aint, aint1);
            ++aint[2];
            ++aint1[2];
            this.placeBlockLine(aint, aint1);
            aint[0] += -1;
            aint1[0] += -1;
            this.placeBlockLine(aint, aint1);
        }

    }

    float leafSize(int par1) {
        return par1 >= 0 && par1 < this.leafDistanceLimit?(par1 != 0 && par1 != this.leafDistanceLimit - 1?3.0F:2.0F):-1.0F;
    }

    void generateLeafNode(int par1, int par2, int par3) {
        int l = par2;

        for(int i1 = par2 + this.leafDistanceLimit; l < i1; ++l) {
            float f = this.leafSize(l - par2);
            this.nodes.add(new FoliagePlacer.TreeNode(new BlockPos(par1, l, par3), (int) f, false));
        }
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config) {
        this.basePos = new int[]{0, 0, 0};
        this.branchDensity = 1.0D;
        this.branchSlope = 0.381D;
        this.scaleWidth = 1.2D;
        this.leafDensity = 1.0D;
        this.trunkSize = 1;
        this.heightLimitLimit = 10;
        this.leafDistanceLimit = 4;
        this.heightAttenuation = 0.618D;
        this.logPositions.clear();
        this.nodes.clear();
        this.heightLimit = 0;

        // Set the ground beneath the trunk to dirt
        setToDirt(world, replacer, random, startPos.down(), config);

        this.worldObj = (World) world;
        this.basePos[0] = startPos.getX();
        this.basePos[1] = startPos.getY();
        this.basePos[2] = startPos.getZ();
        if(this.heightLimit == 0) {
            this.heightLimit = 5 + this.rand.nextInt(this.heightLimitLimit);
        }
        this.generateLeafNodeList();
        this.generateTrunk();
        this.generateLeafNodeBases();

        for (BlockPos pos : this.logPositions) {
            this.getAndSetState(world, replacer, random, pos, config);
        }

        int i = 0;
        for(int j = this.leafNodes.length; i < j; ++i) {
            int k = this.leafNodes[i][0];
            int l = this.leafNodes[i][1];
            int i1 = this.leafNodes[i][2];
            this.generateLeafNode(k, l, i1);
        }

        return this.nodes;
    }

    int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger) {
        int[] aint2 = new int[]{0, 0, 0};
        byte b0 = 0;

        byte b1;
        for(b1 = 0; b0 < 3; ++b0) {
            aint2[b0] = par2ArrayOfInteger[b0] - par1ArrayOfInteger[b0];
            if(Math.abs(aint2[b0]) > Math.abs(aint2[b1])) {
                b1 = b0;
            }
        }

        if(aint2[b1] == 0) {
            return -1;
        } else {
            byte b2 = otherCoordPairs[b1];
            byte b3 = otherCoordPairs[b1 + 3];
            byte b4;
            if(aint2[b1] > 0) {
                b4 = 1;
            } else {
                b4 = -1;
            }

            double d0 = (double)aint2[b2] / (double)aint2[b1];
            double d1 = (double)aint2[b3] / (double)aint2[b1];
            int[] aint3 = new int[]{0, 0, 0};
            int i = 0;

            int j;
            for(j = aint2[b1] + b4; i != j; i += b4) {
                aint3[b1] = par1ArrayOfInteger[b1] + i;
                aint3[b2] = MathHelper.floor((double)par1ArrayOfInteger[b2] + (double)i * d0);
                aint3[b3] = MathHelper.floor((double)par1ArrayOfInteger[b3] + (double)i * d1);
                Block k = this.worldObj.getBlockState(new BlockPos(aint3[0], aint3[1], aint3[2])).getBlock();
                if(k != Blocks.AIR && k != ModBlocks.HAWTHORN_LEAVES) {
                    break;
                }
            }

            return i == j?-1:Math.abs(i);
        }
    }
}
