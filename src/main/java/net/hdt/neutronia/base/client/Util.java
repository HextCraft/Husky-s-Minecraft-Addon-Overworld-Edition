//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package net.hdt.neutronia.base.client;

import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Consumer;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Pattern RESERVED_WINDOWS_NAMES = Pattern.compile(".*\\.|(?:CON|PRN|AUX|NUL|COM1|COM2|COM3|COM4|COM5|COM6|COM7|COM8|COM9|LPT1|LPT2|LPT3|LPT4|LPT5|LPT6|LPT7|LPT8|LPT9)(?:\\..*)?", 2);
    public static LongSupplier nanoTimeSupplier = System::nanoTime;

    public static <K, V> Collector<Entry<? extends K, ? extends V>, ?, Map<K, V>> toMapCollector() {
        return Collectors.toMap(Entry::getKey, Entry::getValue);
    }

    public static String makeTranslationKey(String p_makeTranslationKey_0_, @Nullable ResourceLocation p_makeTranslationKey_1_) {
        return p_makeTranslationKey_1_ == null ? p_makeTranslationKey_0_ + ".unregistered_sadface" : p_makeTranslationKey_0_ + '.' + p_makeTranslationKey_1_.getNamespace() + '.' + p_makeTranslationKey_1_.getPath().replace('/', '.');
    }

    public static long milliTime() {
        return nanoTime() / 1000000L;
    }

    public static long nanoTime() {
        return nanoTimeSupplier.getAsLong();
    }

    public static long millisecondsSinceEpoch() {
        return Instant.now().toEpochMilli();
    }

    public static Stream<String> getJvmFlags() {
        RuntimeMXBean lvt_0_1_ = ManagementFactory.getRuntimeMXBean();
        return lvt_0_1_.getInputArguments().stream().filter((p_211566_0_) -> {
            return p_211566_0_.startsWith("-X");
        });
    }

    public static boolean func_209537_a(Path p_209537_0_) {
        Path lvt_1_1_ = p_209537_0_.normalize();
        return lvt_1_1_.equals(p_209537_0_);
    }

    public static boolean isPathValidForWindows(Path p_isPathValidForWindows_0_) {
        Iterator var1 = p_isPathValidForWindows_0_.iterator();

        Path lvt_2_1_;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            lvt_2_1_ = (Path) var1.next();
        } while (!RESERVED_WINDOWS_NAMES.matcher(lvt_2_1_.toString()).matches());

        return false;
    }

    public static Path func_209535_a(Path p_209535_0_, String p_209535_1_, String p_209535_2_) {
        String lvt_3_1_ = p_209535_1_ + p_209535_2_;
        Path lvt_4_1_ = Paths.get(lvt_3_1_);
        if (lvt_4_1_.endsWith(p_209535_2_)) {
            throw new InvalidPathException(lvt_3_1_, "empty resource name");
        } else {
            return p_209535_0_.resolve(lvt_4_1_);
        }
    }

    @Nullable
    public static <V> V runTask(FutureTask<V> p_runTask_0_, Logger p_runTask_1_) {
        try {
            p_runTask_0_.run();
            return p_runTask_0_.get();
        } catch (ExecutionException var3) {
            p_runTask_1_.fatal("Error executing task", var3);
        } catch (InterruptedException var4) {
            p_runTask_1_.fatal("Error executing task", var4);
        }

        return null;
    }

    public static <T> T getLastElement(List<T> p_getLastElement_0_) {
        return p_getLastElement_0_.get(p_getLastElement_0_.size() - 1);
    }

    public static <T> T func_195647_a(Iterable<T> p_195647_0_, @Nullable T p_195647_1_) {
        Iterator<T> lvt_2_1_ = p_195647_0_.iterator();
        T lvt_3_1_ = lvt_2_1_.next();
        if (p_195647_1_ != null) {
            Object lvt_4_1_ = lvt_3_1_;

            while (lvt_4_1_ != p_195647_1_) {
                if (lvt_2_1_.hasNext()) {
                    lvt_4_1_ = lvt_2_1_.next();
                }
            }

            if (lvt_2_1_.hasNext()) {
                return lvt_2_1_.next();
            }
        }

        return lvt_3_1_;
    }

    public static <T> T get(Supplier<T> p_get_0_) {
        return p_get_0_.get();
    }

    public static <T> T acceptAndReturn(T p_acceptAndReturn_0_, Consumer<T> p_acceptAndReturn_1_) {
        p_acceptAndReturn_1_.accept(p_acceptAndReturn_0_);
        return p_acceptAndReturn_0_;
    }

}