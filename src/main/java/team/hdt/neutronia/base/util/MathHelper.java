package team.hdt.neutronia.base.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.Random;

public class MathHelper {
    private static final float[] SIN_TABLE = Util.func_200696_a(new float[65536], (p_203445_0_) -> {
        for (int lvt_1_1_ = 0; lvt_1_1_ < p_203445_0_.length; ++lvt_1_1_) {
            p_203445_0_[lvt_1_1_] = (float) Math.sin((double) lvt_1_1_ * 3.141592653589793D * 2.0D / 65536.0D);
        }

    });
    private static final int[] MULTIPLY_DE_BRUIJN_BIT_POSITION = new int[]{0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9};
    private static final double FRAC_BIAS = Double.longBitsToDouble(4805340802404319232L);
    private static final double[] ASINE_TAB = new double[257];
    private static final double[] COS_TAB = new double[257];

    static {
        for (int lvt_0_1_ = 0; lvt_0_1_ < 257; ++lvt_0_1_) {
            double lvt_1_1_ = (double) lvt_0_1_ / 256.0D;
            double lvt_3_1_ = Math.asin(lvt_1_1_);
            COS_TAB[lvt_0_1_] = Math.cos(lvt_3_1_);
            ASINE_TAB[lvt_0_1_] = lvt_3_1_;
        }

    }

    public static float sin(float p_sin_0_) {
        return SIN_TABLE[(int) (p_sin_0_ * 10430.378F)];
    }

    public static float cos(float p_cos_0_) {
        return SIN_TABLE[(int) (p_cos_0_ * 10430.378F + 16384.0F)];
    }

    public static float sqrt(float p_sqrt_0_) {
        return (float) Math.sqrt((double) p_sqrt_0_);
    }

    public static float sqrt(double p_sqrt_0_) {
        return (float) Math.sqrt(p_sqrt_0_);
    }

    public static int floor(float p_floor_0_) {
        int lvt_1_1_ = (int) p_floor_0_;
        return p_floor_0_ < (float) lvt_1_1_ ? lvt_1_1_ - 1 : lvt_1_1_;
    }

    public static int floor(double p_floor_0_) {
        int lvt_2_1_ = (int) p_floor_0_;
        return p_floor_0_ < (double) lvt_2_1_ ? lvt_2_1_ - 1 : lvt_2_1_;
    }

    public static float abs(float p_abs_0_) {
        return p_abs_0_ >= 0.0F ? p_abs_0_ : -p_abs_0_;
    }

    public static int abs(int p_abs_0_) {
        return p_abs_0_ >= 0 ? p_abs_0_ : -p_abs_0_;
    }

    static int ceil(float p_ceil_0_) {
        int lvt_1_1_ = (int) p_ceil_0_;
        return p_ceil_0_ > (float) lvt_1_1_ ? lvt_1_1_ + 1 : lvt_1_1_;
    }

    public static int clamp(int p_clamp_0_, int p_clamp_1_, int p_clamp_2_) {
        if (p_clamp_0_ < p_clamp_1_) {
            return p_clamp_1_;
        } else {
            return p_clamp_0_ > p_clamp_2_ ? p_clamp_2_ : p_clamp_0_;
        }
    }

    public static float clamp(float p_clamp_0_, float p_clamp_1_, float p_clamp_2_) {
        if (p_clamp_0_ < p_clamp_1_) {
            return p_clamp_1_;
        } else {
            return p_clamp_0_ > p_clamp_2_ ? p_clamp_2_ : p_clamp_0_;
        }
    }

    public static double clamp(double p_clamp_0_, double p_clamp_2_, double p_clamp_4_) {
        if (p_clamp_0_ < p_clamp_2_) {
            return p_clamp_2_;
        } else {
            return p_clamp_0_ > p_clamp_4_ ? p_clamp_4_ : p_clamp_0_;
        }
    }

    public static int getInt(Random p_getInt_0_, int p_getInt_1_, int p_getInt_2_) {
        return p_getInt_1_ >= p_getInt_2_ ? p_getInt_1_ : p_getInt_0_.nextInt(p_getInt_2_ - p_getInt_1_ + 1) + p_getInt_1_;
    }

    public static float nextFloat(Random p_nextFloat_0_, float p_nextFloat_1_, float p_nextFloat_2_) {
        return p_nextFloat_1_ >= p_nextFloat_2_ ? p_nextFloat_1_ : p_nextFloat_0_.nextFloat() * (p_nextFloat_2_ - p_nextFloat_1_) + p_nextFloat_1_;
    }

    public static double nextDouble(Random p_nextDouble_0_, double p_nextDouble_1_, double p_nextDouble_3_) {
        return p_nextDouble_1_ >= p_nextDouble_3_ ? p_nextDouble_1_ : p_nextDouble_0_.nextDouble() * (p_nextDouble_3_ - p_nextDouble_1_) + p_nextDouble_1_;
    }

    public static float wrapDegrees(float p_wrapDegrees_0_) {
        p_wrapDegrees_0_ %= 360.0F;
        if (p_wrapDegrees_0_ >= 180.0F) {
            p_wrapDegrees_0_ -= 360.0F;
        }

        if (p_wrapDegrees_0_ < -180.0F) {
            p_wrapDegrees_0_ += 360.0F;
        }

        return p_wrapDegrees_0_;
    }

    public static float func_203300_b(float p_203300_0_, float p_203300_1_, float p_203300_2_) {
        p_203300_2_ = abs(p_203300_2_);
        return p_203300_0_ < p_203300_1_ ? clamp(p_203300_0_ + p_203300_2_, p_203300_0_, p_203300_1_) : clamp(p_203300_0_ - p_203300_2_, p_203300_1_, p_203300_0_);
    }

    public static int getInt(String p_getInt_0_, int p_getInt_1_) {
        return NumberUtils.toInt(p_getInt_0_, p_getInt_1_);
    }

    public static int getInt(String p_getInt_0_, int p_getInt_1_, int p_getInt_2_) {
        return Math.max(p_getInt_2_, getInt(p_getInt_0_, p_getInt_1_));
    }

    private static int smallestEncompassingPowerOfTwo(int p_smallestEncompassingPowerOfTwo_0_) {
        int lvt_1_1_ = p_smallestEncompassingPowerOfTwo_0_ - 1;
        lvt_1_1_ |= lvt_1_1_ >> 1;
        lvt_1_1_ |= lvt_1_1_ >> 2;
        lvt_1_1_ |= lvt_1_1_ >> 4;
        lvt_1_1_ |= lvt_1_1_ >> 8;
        lvt_1_1_ |= lvt_1_1_ >> 16;
        return lvt_1_1_ + 1;
    }

    private static boolean isPowerOfTwo(int p_isPowerOfTwo_0_) {
        return p_isPowerOfTwo_0_ != 0 && (p_isPowerOfTwo_0_ & p_isPowerOfTwo_0_ - 1) == 0;
    }

    private static int log2DeBruijn(int p_log2DeBruijn_0_) {
        p_log2DeBruijn_0_ = isPowerOfTwo(p_log2DeBruijn_0_) ? p_log2DeBruijn_0_ : smallestEncompassingPowerOfTwo(p_log2DeBruijn_0_);
        return MULTIPLY_DE_BRUIJN_BIT_POSITION[(int) ((long) p_log2DeBruijn_0_ * 125613361L >> 27) & 31];
    }

    public static int log2(int p_log2_0_) {
        return log2DeBruijn(p_log2_0_) - (isPowerOfTwo(p_log2_0_) ? 0 : 1);
    }

    public static double atan2(double p_atan2_0_, double p_atan2_2_) {
        double lvt_4_1_ = p_atan2_2_ * p_atan2_2_ + p_atan2_0_ * p_atan2_0_;
        if (Double.isNaN(lvt_4_1_)) {
            return 0.0D / 0.0;
        } else {
            boolean lvt_6_1_ = p_atan2_0_ < 0.0D;
            if (lvt_6_1_) {
                p_atan2_0_ = -p_atan2_0_;
            }

            boolean lvt_7_1_ = p_atan2_2_ < 0.0D;
            if (lvt_7_1_) {
                p_atan2_2_ = -p_atan2_2_;
            }

            boolean lvt_8_1_ = p_atan2_0_ > p_atan2_2_;
            double lvt_9_2_;
            if (lvt_8_1_) {
                lvt_9_2_ = p_atan2_2_;
                p_atan2_2_ = p_atan2_0_;
                p_atan2_0_ = lvt_9_2_;
            }

            lvt_9_2_ = fastInvSqrt(lvt_4_1_);
            p_atan2_2_ *= lvt_9_2_;
            p_atan2_0_ *= lvt_9_2_;
            double lvt_11_1_ = FRAC_BIAS + p_atan2_0_;
            int lvt_13_1_ = (int) Double.doubleToRawLongBits(lvt_11_1_);
            double lvt_14_1_ = ASINE_TAB[lvt_13_1_];
            double lvt_16_1_ = COS_TAB[lvt_13_1_];
            double lvt_18_1_ = lvt_11_1_ - FRAC_BIAS;
            double lvt_20_1_ = p_atan2_0_ * lvt_16_1_ - p_atan2_2_ * lvt_18_1_;
            double lvt_22_1_ = (6.0D + lvt_20_1_ * lvt_20_1_) * lvt_20_1_ * 0.16666666666666666D;
            double lvt_24_1_ = lvt_14_1_ + lvt_22_1_;
            if (lvt_8_1_) {
                lvt_24_1_ = 1.5707963267948966D - lvt_24_1_;
            }

            if (lvt_7_1_) {
                lvt_24_1_ = 3.141592653589793D - lvt_24_1_;
            }

            if (lvt_6_1_) {
                lvt_24_1_ = -lvt_24_1_;
            }

            return lvt_24_1_;
        }
    }

    private static double fastInvSqrt(double p_fastInvSqrt_0_) {
        double lvt_2_1_ = 0.5D * p_fastInvSqrt_0_;
        long lvt_4_1_ = Double.doubleToRawLongBits(p_fastInvSqrt_0_);
        lvt_4_1_ = 6910469410427058090L - (lvt_4_1_ >> 1);
        p_fastInvSqrt_0_ = Double.longBitsToDouble(lvt_4_1_);
        p_fastInvSqrt_0_ *= 1.5D - lvt_2_1_ * p_fastInvSqrt_0_ * p_fastInvSqrt_0_;
        return p_fastInvSqrt_0_;
    }

}